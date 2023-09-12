package model;

/**
 * Represents a Layer of an Image. Images can have multiple Layers and can be visible or not, has a
 * FileType (PPM, JPEG, or PNG), an Image, and a name.
 */
public class Layer {

  protected boolean isVisible;
  protected FileType fileType;
  protected AImage image;
  protected String name;

  /**
   * Constructor for a Layer, which includes whether it is visible and an AImage.
   *
   * @param isVisible represents whether the layer is visible or not
   * @param image represents the image
   * @throws IllegalArgumentException if the image or filetype is null.
   */
  public Layer(boolean isVisible, AImage image) {
    if (image == null || fileType == null) {
      throw new IllegalArgumentException("Image or filetype cannot be null");
    }
    this.image = image;
    this.isVisible = isVisible;
    this.name = this.image.filename;
    switch (image.getFilename().substring(image.getFilename().length() - 3)) {
      case "jpg":
        this.fileType = FileType.JPEG;
        break;
      case "png":
        this.fileType = FileType.PNG;
        break;
      case "ppm":
        this.fileType = FileType.PPM;
        break;
      default:
        throw new IllegalArgumentException("Filename has invalid file format");
    }
  }

  /**
   * Returns the FileType of the Layer.
   *
   * @return FileType of Layer
   */
  public FileType getFileType() {
    return this.fileType;
  }

  /**
   * Checks if the Layer is visible.
   *
   * @return boolean if the layer is visible or not
   */
  public boolean getVisibility() {
    boolean b = this.isVisible;
    return b;
  }

  /**
   * Returns the image layer.
   *
   * @return AImage of the layer.
   */
  public AImage getImage() {
    return this.image;
  }
}
