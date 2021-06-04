import qupath.lib.io.GsonTools


// Instantiate tools
def gson=GsonTools.getInstance(true);

// Prepare template
def type = new com.google.gson.reflect.TypeToken<List<qupath.lib.objects.PathObject>>() {}.getType();
def json_fp = promptForFile(null)

// Deserialize
deserializedAnnotations = gson.fromJson(json_fp.getText('UTF-8'), type);

// Add to image
addObjects(deserializedAnnotations);

// Resolve hierarchy
resolveHierarchy()

print "Done!"