package model;

import controller.PNGWriter;
import java.util.ArrayList;

/**
 * Represents a PNG, which extends the AImage class, which implements the Image interface. It
 * handles the methods that apply ColorTransformations and Filters to an PNG file. The PNG files are
 * a type of image file, and this class supports importing and exporting PNG files.
 */
public class PNG extends AImage {

  /**
   * Constructor for an AImage, which includes a filename and list of pixels.
   *
   * @param filename represents the filename of the image
   * @param pixels represents a list of pixels for the image
   * @throws IllegalArgumentException if the filename or list of pixels is null.
   */
  PNG(String filename, ArrayList<ArrayList<Pixel>> pixels) {
    super(filename, pixels);
  }

  @Override
  public void save() {
    PNGWriter writer = new PNGWriter(this);
    writer.createNewFile(this.filename);
    writer.writeFile();
  }
}
