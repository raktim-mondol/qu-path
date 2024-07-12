import qupath.lib.images.servers.ImageServer
import qupath.lib.objects.TMACoreObject
import qupath.lib.regions.RegionRequest

def imageData = getCurrentImageData()
def server = imageData.getServer()
def hierarchy = imageData.getHierarchy()
def tmaGrid = hierarchy.getTMAGrid()

// Check if TMA grid exists
if (tmaGrid == null) {
    print "No TMA grid found!"
    return
}

// Get all TMA cores
def tmaCores = tmaGrid.getTMACoreList()

// Set export parameters
def outputDir = "C:/Users/rakti/Downloads/qupath/"
def imageExtension = ".png"
def downsample = 1.0 // Set to 1.0 for full resolution

// Optional: Specify core names to export (comment out if you want to export all cores)
def coresToExport = ["A-1", "B-2", "C-3"] // Replace with your desired core names

// Export each core
tmaCores.each { core ->
    def name = core.getName()
    
    // Skip cores not in the list if coresToExport is specified
    if (coresToExport && !(name in coresToExport)) {
        return
    }
    
    def region = RegionRequest.createInstance(server.getPath(), downsample, core.getROI())
    def outputPath = new File(outputDir, name + imageExtension).getAbsolutePath()
    
    // Export the image
    writeImageRegion(server, region, outputPath)
    print "Exported " + name + " to " + outputPath
}

print "Export complete!"
