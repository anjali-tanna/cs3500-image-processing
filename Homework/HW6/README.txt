Astitva Asthana and Anjali Tanna HW06.
Created 6/11/21.
Last updated 6/19/21.

********From HW05:

colorTransformation package:

Starting with the Color Transformations, we have an interface IColorTransformations that all 
future transformations can implement. The abstract class AColorTransformation implements the interface and
has a 2D array to represent the matrix of the color transformation, and also has a method that applies the 
transformation to a given pixel. The Grayscale and Sepia transformations extend this class and have 
their respective values in the matrix. All color transformations in the future can extend the abstract
class and have their respective values in the matrix. 

filterTransformation package:

For filters, we have a similar design with an abstract class AFilter that again has a 2D array
to represent a matrix for the kernel of any filter. All filters in the future can extend this class
and use their kernel as a matrix. The Blur and Sharpen classes extend the abstract class with 
their kernels as the matrix.

Model Package:

We next have an interface Image to represent all future images. The abstract class AImage implements this interface
and is extended by all future image types. The interface contains methods to apply Sepia, Blur, Grayscale, and Sharpen,
which the PPM class which extends the abstract class also implements. This will make all future image types be able to apply 
the filters and transformations. The PPM image has a list of lists of pixels to represent each row of pixels in a list.

A Pixel has three fields, which represent the red, green, and blue values. Values will be clamped to 0 and 255 if they are lower 
or greater than the bounds.

A Color was an enum created that represents a few colors, with the corresponding Pixel with the rgb values matching the color. This
was created primarily for the checkerboard method which takes two colors as the tile colors on the board when being created.

The ImageUtil allows for images to be converted to a new AImage, with the type depending on the type of image provided. Currently,
the ImageUtil only reads ppm images.

The IWriter is an interface designed to provide a way to write images as a file that is then readable by an image editor. The PPM Writer
class implements this interface, and creates the methods from the interface, createNewFile and writeFile. createNewFile creates a new file based on
a filename given, while write file takes an AImage and writes all the information from the AImage into the format for the image onto the file 
so that it is readable by an image editor.

The main class had the sole purpose of running methods that could write files and create new files to produce the filtered and transformed pictures.

Supported functionality:
- Loading and saving PPM images.
- Applying a blur, sharpen, sepia, or grayscale to a PPM image.
- Programatically creating a checkerboard image, given the width of a tile in pixels, the number of tiles in a row, and two colors for the tiles.

All example pictures were blurred more than once to ensure that the filter was working.
The rest of the filters and transformations were only applied once to each image.


For the images we used as examples, we own both the images and are authorizing the usage of them for this project.

*********HW06

Changes introduced in HW06 editing code from HW05:

- The AImage constructor was changed to include the list of lists of pixels, since each image type will have a filename and that list.
  this makes it easier to add new image types since they will all have what is already included in the abstract class the
  specific image type will extend.
- The applyFilter and applyColorTransformation methods in the AImage class were made public. Beforehand they were private 
  since they were solely used to apply the filters and color transformations we have created on PPMs. Now, we have made them 
  public so that layered images can use two methods total to apply all future filters and color transformations. 
- The IWriter interface and PPMWriter were moved to the controller package, since they handle I/O, which should not be present in the model package.
- In the model package we added a FileType enum, which has each supported filetype, with each PileType tied to the ending of a filename for that filetype.
  For example, PNG is tied to ".png".
- The methods to apply all filters and transformations to PPMs were moved from the PPM class to the AImage class, so that all future images
  can utilize the same methods.
- Added an abstract class AWriter, so that all file writers can extend the class and use the same createNewFile method.
- Removed methods from the interface level for the Image, which allows LayeredImages to implement the interface while having their unique functionality 
  compared to the rest of the image type, which are singular files.

Controller Package:

The classes PNGWriter and JPEGWriter now extend AWriter, since they are all classes that handle the saving and exporting of singular images.
The class LayerWriter implements IWriter, since the methods that are required to export and save layers are different than those required
for singlular images.

The interface ImageController is created so that our implementation of a controller can implement the interface.
The SimpleImageController implements the ImageController interface, and contains our methods for handling scripts full of commands
to use the image editor. This class is our main controller class to run the editor, and contains multiple fields which allow methods
from the model to be run successfully.



Model Package:

The ImageUtil class contains methods to interpret PNG, JPEG, and layered images.
The LayeredImage class implements Image since it represents an editable format of image for our editor. This class contains one field, which is an 
ArrayList<Layer>. This list represents the layers present in the layered image. The constructor of the LayeredImage throws an error if 
each layer in the list of layers does not have the same dimensions. Each layer has a boolean to represent whether the layer is visible, 
the FileType of the layer, and the actual image that the layer represents. The constructor throws exceptions if the file type or the image is null.

The PNG and JPEG classes extend the AImage class, and have the same fields as all other AImages. They each have their own respective save methods 
that are overriden from AImage, since the save method creates an AWriter of the respective type of file. (i.e. new PNGWriter for PNGs). These methods
DO NOT directly handle I/O since this is in the method class, but rather call upon the writers to do whatever is required.


View Package:
The view package contains the interface ImageView which represents something that all future views of our models and editors can implement. 
Our current main view class is the SimpleImageView, which only has a method that allows the controller to render messages to the user. 
The field for this class is an Appendable.


New supported functionality in addition to functionality from HW05:
- Creating, saving, and l a LayeredImage. In a layered image, images can be removed and added, and can be applied filters and color transformations.
    - Saving a multi layered image creates a file which has all the information of all the layers within the image, and saves the images as separate files as well.
    - Just the top layer of a multi layered image may be saved as well. 
    - Layers can be set to visible or invisible.
- Saving and loading JPEG and PNG files, while also applying filters and color transformations to them
- Files can be converted to different file types and saved as such.
