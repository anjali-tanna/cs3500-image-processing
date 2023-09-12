package controller;

/** Represents the IWriter interface. Handles the createNewFile() and writeFile() methods. */
public interface IWriter {

  /**
   * Creates a new file with the type of file and image given.
   *
   * @throws IllegalArgumentException if the file already exists
   */
  void createNewFile(String filename) throws IllegalArgumentException;

  /** Writes a new PPM file. */
  void writeFile();
}
