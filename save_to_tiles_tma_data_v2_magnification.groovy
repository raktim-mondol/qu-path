import qupath.lib.images.servers.ImageServer
import qupath.lib.objects.PathObject
import qupath.lib.regions.RegionRequest
import qupath.lib.common.GeneralTools // Corrected import statement
import java.io.File
import qupath.lib.images.writers.ImageWriterTools

def imageData = getCurrentImageData()
def server = imageData.getServer()

// Define output path (here, relative to project)
def name = GeneralTools.getNameWithoutExtension(imageData.getServer().getMetadata().getName())
def pathOutput = buildFilePath(PROJECT_BASE_DIR, 'new_test')
def hierarchy_cores = getCurrentHierarchy().getRootObject().getChildObjects()
println("start")

for (cores in hierarchy_cores) {
    def data = cores.getUniqueID()

    for (tile in cores.getChildObjects()) {

        def roi = tile.getROI()

        if (roi == null) {
            println 'Warning! No ROI for object ' + pathObject + ' - cannot export corresponding region & mask'
            return
        }
        
        // Change the downsample factor here to adjust magnification
        double desiredDownsample = 2 // Example for half the original resolution or double the original downsample
        def region = RegionRequest.createInstance(server.getPath(), desiredDownsample, roi)

        String tile_name = String.format('%s_%s_%s_%s_(%d,%d,%d,%d)',
                "Array01",
                cores.getUniqueID(),
                cores.getName(),
                tile.getName(),
                region.getX(),
                region.getY(),
                region.getWidth(),
                region.getHeight()
        )

        def subtype = cores.getMetadataValue("newmolsubtype")

        def mean_r = measurement(tile, "ROI: 2.00 µm per pixel: Red: Mean")
        def mean_g = measurement(tile, "ROI: 2.00 µm per pixel: Green: Mean")
        def mean_b = measurement(tile, "ROI: 2.00 µm per pixel: Blue: Mean")

        if (subtype != null && (mean_r >= 233 && mean_g >= 233 && mean_b >= 233)) {
            subtype = "white"
        } else if (subtype == null) {
            subtype = "other"
        }

        def fileImage = new File(pathOutput + '/' + data + '/' + tile_name + '.png')
        def z = pathOutput + '/' + data + '/'

        new File(z).mkdirs()
        ImageWriterTools.writeImageRegion(server, region, fileImage.getAbsolutePath())
    }
    println(cores)
}

println 'Done!'
