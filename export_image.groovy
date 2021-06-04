// Write the full image (only possible if it isn't too large!)
def server = getCurrentServer()


// Write the full image downsampled by a factor of 10
def requestFull = RegionRequest.createInstance(server, 10)
writeImageRegion(server, requestFull, './full_downsampled.tif')

// Write the region of the image corresponding to the currently-selected object
def roi = getSelectedROI()
def requestROI = RegionRequest.createInstance(server.getPath(), 1, roi)
writeImageRegion(server, requestROI, './region.tif')

// Write the full image, displaying objects according to how they are currently shown in the viewer
def viewer = getCurrentViewer()

print "DONE"