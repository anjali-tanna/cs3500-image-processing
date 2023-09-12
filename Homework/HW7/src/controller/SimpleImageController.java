package controller;

import colortransformations.AColorTransformation;
import colortransformations.Grayscale;
import colortransformations.Sepia;
import filtertransformations.AFilter;
import filtertransformations.Blur;
import filtertransformations.Sharpen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import model.AImage;
import model.ImageUtil;
import model.Layer;
import model.LayeredImage;
import view.SimpleImageView;
import model.FileType;

/**
 * Interface to represent SimpleImageController which implements the ImageController interface and
 * handles the controller for editing images.
 */
public class SimpleImageController implements ImageController {

  protected ImageUtil iu = new ImageUtil();
  protected Readable rd;
  protected Appendable ap;
  protected SimpleImageView view;
  protected Scanner sc;
  protected String filename;
  protected AImage img;
  protected LayeredImage li;

  /**
   * Constructor for a SimpleImageController, which includes the Readable, Appendable,
   * SimpleImageView, Scanner, filename, AImage, and LayeredImage.
   *
   * @throws IllegalArgumentException if the readable or appendable are null
   */
  public SimpleImageController(Readable rd, Appendable ap) {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Commands or image cannot be null.");
    }
    this.rd = rd;
    this.ap = ap;
    this.view = new SimpleImageView(ap);
    this.sc = new Scanner(this.rd);
    this.filename = "";
    this.img = img;
    this.iu = iu;
    this.li = null;
  }

  @Override
  public void start() {
    try {
      this.view.renderMessage("The Image Editor has started.\n");
      this.view.renderMessage("Input \"load\" and then a file name to load an image.\n");
      this.view.renderMessage(
          "Input \"save\" to save the topmost layer or current single image." + "\n");
      this.view.renderMessage("Input \"layer\" to create a layered image with the next image.\n");
      this.view.renderMessage(
          "Input \"filter\" followed by one of the following filters:\n" + "blur, sharpen.\n");
      this.view.renderMessage(
          "Input \"color\" followed by one of the following filters:\n" + "grayscale, sepia.\n");
      this.view.renderMessage("Input \"quit\", to quit the program.\n");
      this.view.renderMessage(
          "With a layer, input \"add\" to add the next file to the layered image with the visi"
              + "bility true or false depending on the next word provided."
              + "\n input \"remove\" with a number to remove the file number "
              + "from the layered image. "
              + "\n input \"layer\" then a standard filter or color input followed "
              + "by a layer number.\n");
      this.view.renderMessage("Input \"saveas\" followed by the filetype to save as the filetype.");
    } catch (IOException e) {
      throw new IllegalArgumentException("Editor could not be started.\n");
    }
    useEditor();
  }

  /**
   * Allows for user input to use the SimpleImageController to edit images.
   */
  private void useEditor() {
    if (!sc.hasNext()) {
      throw new IllegalStateException("Invalid: no commands available.\n");
    }
    while (sc.hasNext()) {
      String input = sc.next().toLowerCase();
      if (input.equals("quit")) {
        try {
          this.view.renderMessage("The editor has been quit.\n");
          return;
        } catch (IOException e) {
          throw new IllegalStateException("Error: unable to quit.\n");
        }
      }
      switch (input) {
        case "load":
          importImage(sc.next());
          break;
        case "filter":
          this.importImage(sc.next());
          this.filterImage();
          try {
            this.view.renderMessage("Filter applied. \n");
          } catch (IOException e) {
            throw new IllegalStateException("Filter could not be applied");
          }
          break;
        case "color":
          this.importImage(sc.next());
          this.colorImage();
          try {
            this.view.renderMessage("Color transformation applied. \n");
          } catch (IOException e) {
            throw new IllegalStateException("Color transformation could not be applied");
          }
          break;
        case "save":
          this.saveImage();
          break;
        case "layer":
          String next = sc.next().toLowerCase();
          if (!next.equals("filter")
              || !next.equals("color")
              || !next.equals("add")
              || !next.equals("remove")) {
            this.createLayer();
            try {
              this.view.renderMessage("Layer created.");
            } catch (IOException e) {
              throw new IllegalStateException("Layer could not be created");
            }
            break;
          }
          if (next.equals("add")) {
            this.importImage(sc.next());
            this.addToLayer();
            try {
              this.view.renderMessage("Image added.");
            } catch (IOException e) {
              throw new IllegalStateException("Image could not be added");
            }
            break;
          }
          if (next.equals("remove")) {
            this.removeLayer();
            try {
              this.view.renderMessage("Layer removed.");
            } catch (IOException e) {
              throw new IllegalStateException("Layer could not be removed");
            }
          }
          if (next.equals("filter")) {
            this.applyLayerFilter();
          }
          if (next.equals("color")) {
            this.applyLayerColor();
            try {
              this.view.renderMessage("Color transformation applied.");
            } catch (IOException e) {
              throw new IllegalStateException("Color transformation could not be applied");
            }
          }
          break;
        case "saveas":
          this.saveAs();
          try {
            this.view.renderMessage("File saved");
          } catch (IOException e) {
            throw new IllegalStateException("File could not be saved");
          }
          break;
        default:
          try {
            this.view.renderMessage("Valid command is required to be loaded.\n");
          } catch (IOException e) {
            throw new IllegalStateException("Message could not be rendered.\n");
          }
          useEditor();
      }
    }
  }

  /**
   * Removes a layer from the LayeredImage.
   */
  private void removeLayer() {
    int num = Integer.parseInt(sc.next());
    this.li.removeLayer(num);
  }

  /**
   * Applies a filter to the Layer (either blur or sharpen).
   */
  private void applyLayerFilter() {
    String next = sc.next().toLowerCase();
    int num = Integer.parseInt(sc.next());
    if ("blur".equals(next)) {
      this.li.applyFilterTo(num, new Blur());
    } else if ("sharpen".equals(next)) {
      this.li.applyFilterTo(num, new Sharpen());
    }
  }

  /**
   * Applies a color transformation to the Layer (either sepia or grayscale).
   */
  private void applyLayerColor() {
    String next = sc.next().toLowerCase();
    int num = Integer.parseInt(sc.next());
    if ("sepia".equals(next)) {
      this.li.applyTransformationTo(num, new Sepia());
    } else if ("grayscale".equals(next)) {
      this.li.applyTransformationTo(num, new Grayscale());
    }
  }

  /**
   * Gets the FileType of the file.
   *
   * @param filename the name of the file where the type is being retrieved
   * @return FileType of the file (PNG, PPM, JPEG)
   */
  private FileType getFiletype(String filename) {
    switch (filename.substring(filename.length() - 3)) {
      case "jpg":
        return FileType.JPEG;
      case "ppm":
        return FileType.PPM;
      case "png":
        return FileType.PNG;
      default:
        throw new IllegalArgumentException("Invalid filetype\n");
    }
  }

  /**
   * Imports the image that is being modified.
   *
   * @param filename name of the file being imported
   */
  private void importImage(String filename) {
    File file = new File(filename);
    if (file.isDirectory()) {
      try {
        this.img = iu.readImage(filename, this.getFiletype(filename));
        this.view.renderMessage("Layer imported successfully.\n");
      } catch (IOException e) {
        throw new IllegalStateException("Message could not be rendered.\n");
      }
    }
  }

  /**
   * Applies the filter on the image (blur or sharpen).
   */
  private void filterImage() {
    AFilter fil = null;
    switch (sc.next()) {
      case "blur":
        this.img.applyFilter(new Blur(), 3, this.filename);
        break;
      case "sharpen":
        this.img.applyFilter(new Sharpen(), 5, this.filename);
        break;
      default:
        throw new IllegalArgumentException("Filter invalid");
    }
  }

  /**
   * Saves the image in an alternative format.
   */
  private void saveAs() {
    String next = sc.next().toLowerCase();
    switch (next) {
      case "ppm":
        this.img.convertTo(FileType.PPM).save();
        break;
      case "jpg":
        this.img.convertTo(FileType.JPEG).save();
        break;
      case "png":
        this.img.convertTo(FileType.PNG).save();
        break;
      default:
        throw new IllegalArgumentException("Invalid file type");
    }
  }

  /**
   * Applies the color transformation on the image (sepia or grayscale).
   */
  private void colorImage() {
    AColorTransformation col = null;
    switch (sc.next().toLowerCase()) {
      case "sepia":
        col = new Sepia();
        this.img.applyColorTransformation(col, this.filename);
        break;
      case "grayscale":
        col = new Grayscale();
        this.img.applyColorTransformation(col, this.filename);
        break;
      default:
        throw new IllegalArgumentException("Color transformation invalid");
    }
  }

  /**
   * Adds to the given layer.
   */
  private void addToLayer() {
    boolean b = Boolean.parseBoolean(sc.next().toLowerCase());
    this.li.addLayer(b, this.img);
  }

  /**
   * Checks if the integer is valid.
   *
   * @param s String
   * @return boolean, true if integer is valid
   */
  private boolean validInteger(String s) {
    int ind;
    try {
      ind = Integer.parseInt(s);
    } catch (NumberFormatException e) {
      return false;
    }
    return ind > 0;
  }

  /**
   * Creates a new layer in the LayeredImage.
   */
  private void createLayer() {
    importImage(sc.next());
    this.li.addLayer(true, this.img);
  }

  /**
   * Creates a layer in the LayeredImage.
   *
   * @param filename the name of the file being created.
   */
  private void createLayer(String filename) {
    AImage image = iu.readImage(filename, this.getFiletype(filename));
    Layer l = new Layer(true, image);
    li = new LayeredImage(new ArrayList<Layer>(Arrays.asList(l)));
  }


  /**
   * Saves the image in the correct format.
   */
  private void saveImage() {
    if (this.li == null) {
      this.img.save();
    } else {
      this.li.getTopmostVisibleLayer().save();
    }
    try {
      this.view.renderMessage("Saved.");
    } catch (IOException e) {
      throw new IllegalStateException("Could not save.");
    }
  }
}
