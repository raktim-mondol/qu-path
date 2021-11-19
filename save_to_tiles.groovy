// Get the current image (supports 'Run for project')

import qupath.lib.images.servers.ImageServer
import qupath.lib.objects.PathObject

import javax.imageio.ImageIO
import java.awt.Color
import java.awt.image.BufferedImage

def imageData = getCurrentImageData()
def server = imageData.getServer()

// Define output path (here, relative to project)
def name = GeneralTools.getNameWithoutExtension(imageData.getServer().getMetadata().getName())
def pathOutput = buildFilePath(PROJECT_BASE_DIR, 'tiles' )
def hierarchy_cores = getCurrentHierarchy().getRootObject().getChildObjects()
print("start")

for(cores in hierarchy_cores){
    def data = cores.getUniqueID();
      
    for(tile in cores.getChildObjects()){
  
        def roi = tile.getROI();
        
        if (roi == null) {
        print 'Warning! No ROI for object ' + pathObject + ' - cannot export corresponding region & mask'
        return
        }
        def region = RegionRequest.createInstance(server.getPath(), 1, roi)

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

        //def img = server.readBufferedImage(region)
        //for white images that have no relavent informaiton
        def subtype = cores.getMetadataValue("newmolsubtype");
        
        def mean_r = measurement(tile,"ROI: 2.00 µm per pixel: Red: Mean");
        def mean_g = measurement(tile,"ROI: 2.00 µm per pixel: Green: Mean");
        def mean_b = measurement(tile,"ROI: 2.00 µm per pixel: Blue: Mean");
        
        if (subtype != null && (mean_r >= 233  && mean_g >= 233  && mean_b >= 233) ){
            subtype = "white"
        }
        else if ( subtype == null){
            subtype = "other";
        }  
        
        def fileImage = new File(pathOutput,subtype + "/Array16/" + tile_name+'.png')
        def z = pathOutput + '/'+subtype + '/Array16/';
        mkdirs(z)
        ImageWriterTools.writeImageRegion(server, region, fileImage.getAbsolutePath())
        
    }
    print(cores);
}

print 'Done!'
