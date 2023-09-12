Astitva Asthana and Anjali Tanna HW05.
Created 6/11/21.

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


*******All example pictures were blurred more than once to ensure that the filter was working*******
The rest of the filters and transformations were only applied once to each image.


For the images we used as examples, we own both the images and are authorizing the usage of them for this project.
