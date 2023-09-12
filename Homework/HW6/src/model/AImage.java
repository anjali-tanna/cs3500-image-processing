package model;

import colortransformations.AColorTransformation;
import colortransformations.Grayscale;
import colortransformations.Sepia;
import filtertransformations.AFilter;
import filtertransformations.Blur;
import filtertransformations.Sharpen;
import java.util.ArrayList;

/**
 * Abstract class that represents an AImage which implements the Image interface. Includes the
 * filename of an image and a list of pixels.
 */
public abstract class AImage implements Image {

  protected String filename;
  protected ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Constructor for an AImage, which includes a filename and list of pixels.
   *
   * @throws IllegalArgumentException if the filename or list of pixels is null.
   */
  AImage(String filename, ArrayList<ArrayList<Pixel>> pixels) {
    if (filename == null) {
      throw new IllegalArgumentException("Filename cannot be null");
    }
    if (pixels == null) {
      throw new IllegalArgumentException("List of pixels cannot be null");
    }
    this.filename = filename;
    this.pixels = pixels;
  }

  /**
   * Gets the filename from an Image.
   *
   * @return A string of the filename
   */
  public String getFilename() {
    return this.filename;
  }

  /**
   * Gets a list of Pixels in an Image.
   *
   * @return An Array List of Pixels of an Image
   */
  public ArrayList<ArrayList<Pixel>> getPixels() {
    ArrayList<ArrayList<Pixel>> newPixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> row = new ArrayList<Pixel>();
    for (int i = 0; i < this.pixels.size(); i++) {
      row = new ArrayList<Pixel>();
      for (int k = 0; k < this.pixels.get(0).size(); k++) {
        row.add(this.pixels.get(i).get(k));
      }
      newPixels.add(row);
    }
    return newPixels;
  }

  /**
   * Applies the Grayscale color transformation to an Image. Grayscale creates a black and white
   * grayscale of the image.
   *
   * @return A AImage {@code AImage} for a Grayscale Image.
   */
  public AImage applyGrayscale() {
    Grayscale g = new Grayscale();
    return this.applyColorTransformation(g, "grayscale_" + this.filename);
  }

  /**
   * Applies the Sepia color transformation to an Image. Sepia creates a sepia filter of the image.
   *
   * @return A AImage {@code AImage} for a Sepia Image.
   */
  public AImage applySepia() {
    Sepia s = new Sepia();
    return this.applyColorTransformation(s, "sepia_" + this.filename);
  }

  /**
   * Applies a ColorTransformation to an Image. Depending on the input, the ColorTransformation is
   * either Grayscale or Sepia. Grayscale creates a black and white grayscale effect on the image.
   * Sepia creates a sepia effect on the image.
   *
   * @param ct the type of ColorTransformation, either Grayscale or Sepia
   * @param newFilename the new generated filename for the image
   * @return An AImage {@code AImage} for a ColorTransformation Image.
   */
  public AImage applyColorTransformation(AColorTransformation ct, String newFilename) {
    ArrayList<ArrayList<Pixel>> newPixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<ArrayList<Pixel>> pix = this.getPixels();
    ArrayList<Pixel> row = new ArrayList<Pixel>();
    for (int i = 0; i < pixels.size(); i++) {
      row = new ArrayList<Pixel>();
      for (int k = 0; k < pixels.get(i).size(); k++) {
        Pixel p = ct.applyToPixel(pix.get(i).get(k));
        row.add(p);
      }
      newPixels.add(row);
    }
    this.filename = newFilename;
    this.pixels = newPixels;
    return this;
  }

  /**
   * Applies the Sharpen filter to an Image. Sharpen creates a sharpened effect on the image.
   *
   * @return A AImage {@code AImage} for a Sharpened Image.
   */
  public AImage applySharpen() {
    Sharpen s = new Sharpen();
    return this.applyFilter(s, 5, "sharpened_" + this.filename);
  }

  /**
   * Applies the Blur filter to an Image. Blur creates a blurred effect on the image.
   *
   * @return A AImage {@code AImage} for a Blurred Image.
   */
  public AImage applyBlur() {
    Blur b = new Blur();
    return this.applyFilter(b, 3, "blurred_" + this.filename);
  }

  /**
   * Applies a Filter to an Image. Depending on the input, the Filter is either Sharpen or Blur.
   * Sharpen creates a sharpened effect on the image. Blur creates a blurred effect on the image.
   *
   * @param f the type of AFilter, either Blur or Sharpen
   * @param matrixSize the size of the matrix of the filter, (3 for Blur, 5 for Sharpen)
   * @param newFilename the new generated filename for the image
   * @return A AImage {@code AImage} for a filtered Image.
   */
  public AImage applyFilter(AFilter f, int matrixSize, String newFilename) {
    ArrayList<ArrayList<Pixel>> p = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int k = 0; k < this.pixels.get(0).size(); k++) {
        double newRed = 0;
        double newGreen = 0;
        double newBlue = 0;
        for (int r = 0; r < matrixSize; r++) {
          for (int c = 0; c < matrixSize; c++) {
            newRed =
                newRed
                    + f.matrix[r][c]
                        * getPixelAt(i + (r - (matrixSize / 2)), k + (c - (matrixSize / 2)), p)
                            .getRed();
            newGreen =
                newGreen
                    + f.matrix[r][c]
                        * getPixelAt(i + (r - (matrixSize / 2)), k + (c - (matrixSize / 2)), p)
                            .getGreen();
            newBlue =
                newBlue
                    + f.matrix[r][c]
                        * getPixelAt(i + (r - (matrixSize / 2)), k + (c - (matrixSize / 2)), p)
                            .getBlue();
          }
        }
        p.get(i).set(k, new Pixel((int) newRed, (int) newGreen, (int) newBlue));
      }
    }
    this.filename = newFilename;
    this.pixels = p;
    return this;
  }

  /**
   * Returns a Pixel from an Array List of Pixels given the row and column.
   *
   * @param i the row where the Pixel is located
   * @param k the column where the Pixel is located
   * @param p the list of Pixels where the Pixel is located
   * @return A Pixel {@code Pixel} at a certain location
   */
  private Pixel getPixelAt(int i, int k, ArrayList<ArrayList<Pixel>> p) {
    if (i < 0 || k < 0 || i >= p.size() || k >= p.get(0).size()) {
      return new Pixel(0, 0, 0);
    } else {
      return p.get(i).get(k);
    }
  }

  /**
   * Returns an image in the format specified with the same picture as the one provided.
   *
   * @param ft the FileType that the file is being converted to
   * @return An AImage of the type specified
   * @throws IllegalArgumentException if the FileType is null or invalid
   */
  public AImage convertTo(FileType ft) throws IllegalArgumentException {
    if (ft == null) {
      throw new IllegalArgumentException("File type cannot be null");
    }
    switch (ft) {
      case PNG:
        return new PNG(this.getFilename(), this.getPixels());
      case PPM:
        return new PPM(this.getFilename(), this.getPixels());
      case JPEG:
        return new JPEG(this.getFilename(), this.getPixels());
      default:
        throw new IllegalArgumentException("Invalid file type");
    }
  }

  /**
   * Returns a layered version of the FileType.
   *
   * @param ft the FileType that the file is being layered
   * @param isVisible whether or not the layer is visible
   * @return A LayeredImage of the type specified
   * @throws IllegalArgumentException if the FileType is null or invalid
   */
  public LayeredImage makeLayered(FileType ft, boolean isVisible) {
    if (ft == null) {
      throw new IllegalArgumentException("File type cannot be null");
    }
    ArrayList<Layer> layers = new ArrayList<Layer>();
    ArrayList<AImage> image = new ArrayList<AImage>();
    switch (ft) {
      case PPM:
        image.add(new PPM(this.getFilename(), this.getPixels()));
        layers.add(new Layer(isVisible, image.get(0)));
        return new LayeredImage(layers);
      case PNG:
        image.add(new PNG(this.getFilename(), this.getPixels()));
        layers.add(new Layer(isVisible, image.get(0)));
        return new LayeredImage(layers);
      case JPEG:
        image.add(new JPEG(this.getFilename(), this.getPixels()));
        layers.add(new Layer(isVisible, image.get(0)));
        return new LayeredImage(layers);
      default:
        throw new IllegalArgumentException("File type not supported or is invalid");
    }
  }

  public abstract void save();
  // method used in Controller
}
