package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import model.AImage;
import model.LayeredImage;

/**
 * Represents a LayerWriter, which implements the IWriter class. It handles the methods that allow
 * new layers to be created and written. Layers are a type of image file, and this class supports
 * creating and writing them.
 */
public class LayerWriter implements IWriter {

  protected LayeredImage image;

  /**
   * Constructor for LayerWriter which takes in a LayeredImage.
   *
   * @param image the image that has layers being written
   */
  LayerWriter(LayeredImage image) {
    super();
    if (image == null) {
      throw new IllegalArgumentException("Layered image cannot be null");
    }
    this.image = image;
  }

  @Override
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

  /** Saves all the layers as one image file. */
  public void saveAll() {
    this.createNewFile("layered_" + this.image.getLayers().get(0).getImage().getFilename());
    try {
      FileWriter wr =
          new FileWriter("layered_" + this.image.getLayers().get(0).getImage().getFilename());
      for (int i = 0; i < this.image.getLayers().size(); i++) {
        AImage im = this.image.getLayers().get(i).getImage();
        try {
          wr.write(
              im.getFilename()
                  + "\n"
                  + this.image.getLayers().get(i).getVisibility()
                  + "\n"
                  + this.image.getLayers().get(i).getFileType()
                  + "\n");
        } catch (IOException e) {
          System.out.println("Error in saving layers");
        }
      }
    } catch (IOException e) {
      System.out.println("Error in saving layers");
    }
    for (int i = 0; i < this.image.getLayers().size(); i++) {
      this.image.getLayers().get(i).getImage().save();
    }
  }

  @Override
  public void writeFile() {
    this.image.getTopmostVisibleLayer().save();
  }
}
