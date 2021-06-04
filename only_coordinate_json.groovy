def annotations = getAnnotationObjects()
def rois = getAnnotationObjects().collect {it.getROI()}

boolean prettyPrint = true

def gson = GsonTools.getInstance(prettyPrint)

def path = "file_name.json"
def file = new File(path)







file.write(gson.toJson(rois))




print "Done!"