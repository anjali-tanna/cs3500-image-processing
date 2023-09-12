package model;

import controller.JPEGWriter;
import java.util.ArrayList;

/**
 * Represents a JPEG, which extends the AImage class, which implements the Image interface. It
 * handles the methods that apply ColorTransformations and Filters to an JPEG file. The JPEG files
 * are a type of image file, and this class supports importing and exporting JPEG files.
 */
public class JPEG extends AImage {

  /**
   * Constructor for an AImage, which includes a filename and list of pixels.
   *
   * @param filename represents the filename of the image
   * @param pixels represents the pixels in the image
   * @throws IllegalArgumentException if the filename or list of pixels is null.
   */
  JPEG(String filename, ArrayList<ArrayList<Pixel>> pixels) {
    super(filename, pixels);
  }

  @Override
  public void save() {
    JPEGWriter writer = new JPEGWriter(this);
    writer.createNewFile(this.filename);
    writer.writeFile();
  }
}
