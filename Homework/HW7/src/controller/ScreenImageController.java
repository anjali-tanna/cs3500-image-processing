package controller;

import colortransformations.AColorTransformation;
import filtertransformations.AFilter;
import java.util.ArrayList;
import model.AImage;
import model.FileType;
import model.ImageUtil;
import model.Layer;
import model.LayeredImage;

/**
 * Interface to represent ScreenImageController which implements the controller view for editing
 * images.
 */
public class ScreenImageController {

  protected final ImageUtil iu;
  protected String filename;
  protected AImage img;
  protected LayeredImage li;

  /**
   * Constructor for a ScreenImageController, which includes the ImageUtil, filename, AImage,
   * LayeredImage, and ScreenView.
   */
  public ScreenImageController() {
    this.iu = new ImageUtil();
    this.filename = "";
    if (this.filename.length() != 0) {
      this.img = iu.readImage(filename, this.getFiletype(filename));
    } else {
      this.img = null;
    }
    this.li = null;
  }

  /**
   * Sets the Filename of the ScreenImageController.
   *
   * @param f The string that represents the filename
   */
  public void setFilename(String f) {
    filename = f;
  }

  /**
   * Creates a new LayeredImage.
   */
  public void newLayeredImage() {
    ArrayList<Layer> layers = new ArrayList<Layer>();
    layers.add(new Layer(true, iu.readImage(filename, this.getFiletype(filename))));
    li = new LayeredImage(layers);
  }

  /**
   * Gets the FileType of the Image.
   *
   * @param f The string that represents the filename
   */
  private FileType getFiletype(String f) throws IllegalArgumentException {
    switch (f.substring(f.length() - 3)) {
      case "jpg":
        return FileType.JPEG;
      case "png":
        return FileType.PNG;
      case "ppm":
        return FileType.PPM;
      default:
        throw new IllegalArgumentException("Filename has invalid file format");
    }
  }

  /**
   * Converts the file and saves it to a folder.
   *
   * @param folder The string that represents the folder where the file is being saved
   * @param f      The string that represents the filename
   */
  public void convertAndSave(String folder, FileType f) {
    img.convertTo(f);
    img.save();
  }

  /**
   * Performs a ColorTransformation on an Image (Sepia or Grayscale).
   *
   * @param act The given ColorTransformation (sepia or grayscale)
   */
  public void colorImage(AColorTransformation act) {
    this.img.applyColorTransformation(act, this.filename);
  }

  /**
   * Performs a FilterTransformation on an Image (Blur or Sharpen).
   *
   * @param f The given ColorTransformation (blur or sharpen)
   */
  public void filterImage(AFilter f) {
    this.img.applyFilter(f, f.matrix[0].length, this.filename);
  }

  /**
   * Gets the name of the topmost file layer.
   *
   * @param s The string of the name of the layer
   * @returns String that represents the name of the top layer.
   */
  public String getTopmostFilename(String s) {
    li = iu.loadLayers(s);
    return li.getTopmostVisibleLayer().getFilename();
  }

  /**
   * Removes a layer.
   *
   * @param s The string of the layer name being removed
   */
  public void remove(String s) {
    this.li.removeLayer(Integer.parseInt(s));
  }

  /**
   * Adds a layer.
   *
   * @param s The string of the layer name being added
   */
  public void add(String s) {
    this.li.addLayer(true, iu.readImage(s, this.getFiletype(s)));
  }


}