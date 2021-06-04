// Write the full image (only possible if it isn't too large!)
def server = getCurrentServer()


// Write the full image downsampled by a factor of 10
def requestFull = RegionRequest.createInstance(server, 10)
writeImageRegion(server, requestFull, './full_downsampled.tif')
