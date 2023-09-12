package controller;

import java.io.FileWriter;
import java.io.IOException;
import model.AImage;
import model.FileType;
import model.PPM;

/**
 * Represents a PPMWriter, which implements the IWriter class. It handles the methods that allow new
 * PPM files to be created and written. PPM files are a type of image file, and this class supports
 * creating and writing them.
 */
public class PPMWriter extends AWriter {

  /**
   * Constructor for a PPMWriter, which includes the image, which is the image it is creating a PPM
   * file for.
   *
   * @throws IllegalArgumentException if the image is null.
   */
  public PPMWriter(AImage image) {
    super(image);
    if (!(image instanceof PPM)) {
      throw new IllegalArgumentException("PPM Writer can only utilize PPM images.");
    }
    this.fileType = FileType.PPM;
  }

  /** Writes the file. */
  @Override
  public void writeFile() {
    this.createNewFile(this.image.getFilename());
    try {
      FileWriter wr = new FileWriter(this.image.getFilename() + ".ppm");
      wr.write(
          "P3\n"
              + this.image.getPixels().get(0).size()
              + " "
              + this.image.getPixels().size()
              + "\n255\n");
      for (int i = 0; i < this.image.getPixels().size(); i++) {
        for (int k = 0; k < this.image.getPixels().get(0).size(); k++) {
          wr.write(
              this.image.getPixels().get(i).get(k).getRed()
                  + "\n"
                  + this.image.getPixels().get(i).get(k).getGreen()
                  + "\n"
                  + this.image.getPixels().get(i).get(k).getBlue()
                  + "\n");
        }
      }
      wr.close();
    } catch (IOException e) {
      System.out.println("An error occurred.");
    }
  }
}
