# QuPath Groovy Scripting Guide

This repository contains a guide on how to use Groovy code in QuPath for automation and customization purposes. QuPath offers a built-in script editor that allows you to write and run Groovy scripts to perform various tasks and extend the functionality of the software.

## Installation

1. Clone this repository to your local machine.
2. Follow the [official QuPath installation guide](https://qupath.readthedocs.io/en/0.2/docs/installation.html) to set up QuPath on your computer.

## Usage

### Running Groovy Scripts in QuPath

1. Open the QuPath application.
2. Load your images and annotations into QuPath.
3. Navigate to **Automate > Show script editor** in the menu bar to open the script editor.
4. Write or paste your Groovy script into the script editor. You can also load an existing script using the **File > Open...** option in the script editor.
5. Save your script (optional) using the **File > Save as...** option in the script editor.
6. Run your script by clicking the **Run** button in the script editor. You can also use the **Ctrl+R** (or **Cmd+R** on macOS) keyboard shortcut to run the script.

### Example Groovy Script

The following is an example Groovy script that prints the names of all the images currently open in QuPath:

```groovy
import qupath.lib.projects.Project
import qupath.lib.images.ImageData

Project project = qupath.getProject()
List<ImageData> imageDataList = project.getImageList()

imageDataList.each { imageData ->
    println "Image name: ${imageData.getServer().getMetadata().getName()}"
}
```
Copy and paste this script into the QuPath script editor and run it to see the output.

## Acknowledgements

- [Exporting images with QuPath](https://qupath.readthedocs.io/en/0.2/docs/advanced/exporting_images.html)
- [Exporting annotations with QuPath](https://qupath.readthedocs.io/en/0.2/docs/advanced/exporting_annotations.html)
- [Json Annotation: Sharing annotated QuPath files with other users](https://forum.image.sc/t/how-do-i-send-my-annotated-qupath-files-to-another-qupath-user-the-easiest-way/42979)


## Contributing

If you'd like to contribute to this project, please create a fork of this repository, make your changes, and submit a pull request.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Support

For any questions or issues, please use the [QuPath forum](https://forum.image.sc/tag/qupath) or open an issue in this repository.

