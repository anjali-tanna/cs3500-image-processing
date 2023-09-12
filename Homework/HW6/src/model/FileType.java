package model;

/**
 * Enum for FileType, which includes PPM files, JPEG files, and PNG files. All of these FileTypes
 * are types of Image files.
 */
public enum FileType {
  PPM("ppm"),
  JPEG("jpg"),
  PNG("png");

  protected final String type;

  /**
   * Constructor for FileType.
   *
   * @param type the type of file
   */
  FileType(String type) {
    if (type == null) {
      throw new IllegalArgumentException("File type cannot be null");
    }
    this.type = type;
  }

  /**
   * Returns the type of file.
   *
   * @return the type of file as a String
   */
  public String getType() {
    return this.type;
  }
}
