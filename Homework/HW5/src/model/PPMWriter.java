package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents a PPMWriter, which implements the IWriter class. It handles the methods that allow new
 * PPM files to be created and written. PPM files are a type of image file, and this class supports
 * creating and writing them.
 */
public class PPMWriter implements IWriter {

  PPM image;

  /**
   * Constructor for a PPMWriter, which included the image, which is the image it is creating a PPM
   * file for.
   *
   * @throws IllegalArgumentException if the image is null.
   */
  public PPMWriter(PPM image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
  }

  @Override
  public void createNewFile() throws IllegalArgumentException {
    File newFile = new File(this.image.filename + ".ppm");
    try {
      if (!(newFile.createNewFile())) {
        throw new IllegalArgumentException("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }

  @Override
  public void writeFile() {
    this.createNewFile();
    try {
      FileWriter wr = new FileWriter(this.image.filename + ".ppm");
      wr.write(
          "P3\n" + this.image.pixels.get(0).size() + " " + this.image.pixels.size() + "\n255\n");
      for (int i = 0; i < this.image.pixels.size(); i++) {
        for (int k = 0; k < this.image.pixels.get(0).size(); k++) {
          wr.write(
              this.image.pixels.get(i).get(k).getRed()
                  + "\n"
                  + this.image.pixels.get(i).get(k).getGreen()
                  + "\n"
                  + this.image.pixels.get(i).get(k).getBlue()
                  + "\n");
        }
      }
      wr.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }
}
