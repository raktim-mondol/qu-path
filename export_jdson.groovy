def annotations = getAnnotationObjects()
boolean prettyPrint = true
def gson = GsonTools.getInstance(prettyPrint)

def path = "tumor_004.json"
def file = new File(path)
file.write(gson.toJson(annotations))

print "Export Done!"