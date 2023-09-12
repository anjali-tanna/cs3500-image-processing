package model;

/** Represents the IWriter interface. Handles the createNewFile() and writeFile() methods. */
public interface IWriter {

  /**
   * Creates a new PPM file.
   *
   * @throws IllegalArgumentException if the file already exists
   */
  public void createNewFile() throws IllegalArgumentException;

  /** Writes a new PPM file. */
  public void writeFile();
}
