package model;

/**
 * Abstract class that represents an AImage which implements the Image interface. Includes the
 * filename of an image.
 */
public abstract class AImage implements Image {
  protected String filename;

  /**
   * Constructor for an AImage, which includes a filename.
   *
   * @throws IllegalArgumentException if the filename is null.
   */
  AImage(String filename) {
    if (filename == null) {
      throw new IllegalArgumentException("Filename cannot be null");
    }
    this.filename = filename;
  }
}
