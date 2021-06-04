def imageData = getCurrentImageData()

// Define output path (relative to project)
def outputDir = buildFilePath(PROJECT_BASE_DIR, 'export')
mkdirs(outputDir)
def name = GeneralTools.getNameWithoutExtension(imageData.getServer().getMetadata().getName())
def path = buildFilePath(outputDir, name + "-labels.png")

// Define how much to downsample during export (may be required for large images)
double downsample = 2

print "DONE"



// Write the full image (only possible if it isn't too large!)
def server = getCurrentServer()
//writeImage(server, './full.tif')

// Write the full image downsampled by a factor of 10
def requestFull = RegionRequest.createInstance(server, downsample)
writeImageRegion(server, requestFull, './export/'+name+".png")
print "DONE"