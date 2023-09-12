package controller;

import model.AImage;
import model.FileType;
import model.PNG;

/**
 * Represents a PNGWriter, which implements the AWriter class. It handles the methods that allow new
 * JPEG files to be created and written. PNG files are a type of image file, and this class supports
 * creating and writing them.
 */
public class PNGWriter extends AWriter {

  /**
   * Constructor for a PNGWriter, which includes the image, which is the image it is creating a PNG
   * file for.
   *
   * @throws IllegalArgumentException if the image is not a PNG file.
   */
  public PNGWriter(AImage image) {
    super(image);
    if (!(image instanceof PNG)) {
      throw new IllegalArgumentException("PNG Writer can only utilize PNG images.");
    }
    this.fileType = FileType.PNG;
  }
}
