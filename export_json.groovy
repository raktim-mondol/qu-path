def annotations = getAnnotationObjects()
boolean prettyPrint = true
def gson = GsonTools.getInstance(prettyPrint)

def path = "kfile.json"
def file = new File(path)
file.write(gson.toJson(annotations))

print "Done!"
