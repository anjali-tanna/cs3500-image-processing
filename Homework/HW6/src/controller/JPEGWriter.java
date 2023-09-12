package controller;

import model.AImage;
import model.FileType;
import model.JPEG;

/**
 * Represents a JPEGWriter, which implements the AWriter class. It handles the methods that allow
 * new JPEG files to be created and written. JPEG files are a type of image file, and this class
 * supports creating and writing them.
 */
public class JPEGWriter extends AWriter {

  /**
   * Constructor for a JPEGWriter, which includes the image, which is the image it is creating a
   * JPEG file for.
   *
   * @throws IllegalArgumentException if the image is not a JPEG file.
   */
  public JPEGWriter(AImage image) {
    super(image);
    if (!(image instanceof JPEG)) {
      throw new IllegalArgumentException("JPEG Writer can only utilize JPEG images.");
    }
    this.fileType = FileType.JPEG;
  }
}
