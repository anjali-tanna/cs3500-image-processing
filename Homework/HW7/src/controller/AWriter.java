package controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.AImage;
import model.FileType;
import model.ImageUtil;
import java.io.FileOutputStream;

/**
 * Abstract class that represents an AWriter which implements IWriter which allows for files of
 * Images to be written. Includes the AImage and the FileType.
 */
public abstract class AWriter implements IWriter {

  protected AImage image;
  protected FileType fileType;

  /**
   * Constructor for an AImage, which includes an image.
   */
  AWriter(AImage image) {
    if (image == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.image = image;
  }

  /**
   * Gets the image of the AImage.
   *
   * @return AImage
   */
  public AImage getImage() {
    return this.image;
  }

  /**
   * Creates a new file.
   *
   * @param filename the name of the new file
   * @throws IllegalArgumentException if file exists already
   */
  public void createNewFile(String filename) throws IllegalArgumentException {
    File newFile = new File(filename);
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
    ImageUtil iu = new ImageUtil();
    BufferedImage im = iu.writeImage(this.fileType, this.image);
    try {
      new FileWriter(this.image.getFilename()).close();
      ImageIO.write(
          im, this.fileType.getType(), new FileOutputStream(this.image.getFilename()));
    } catch (IOException e) {
      System.out.println("An error occurred");
    }
  }

  /**
   * Saves the image in the correct format.
   */
  public void save() {
    // method used in Controller
  }
}
