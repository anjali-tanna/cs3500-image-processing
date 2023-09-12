package model;

import colortransformations.AColorTransformation;
import colortransformations.Sepia;
import colortransformations.Grayscale;
import filtertransformations.AFilter;
import filtertransformations.Blur;
import filtertransformations.Sharpen;
import java.util.ArrayList;

/**
 * Represents a PPM, which extends the AImage class, which implements the Image interface. It
 * handles the methods that apply ColorTransformations and Filters to an PPM file. The PPM files are
 * a type of image file, and this class supports importing and exporting PPM files.
 */
public class PPM extends AImage {
  protected ArrayList<ArrayList<Pixel>> pixels;

  /**
   * Constructor for a PPM, which includes the super(filename), and pixels, which is the list of
   * pixels in the PPM file.
   */
  public PPM(String filename, ArrayList<ArrayList<Pixel>> pixels) {
    super(filename);
    this.pixels = pixels;
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

  @Override
  public PPM applyGrayscale() {
    Grayscale g = new Grayscale();
    return this.applyColorTransformation(g, "grayscale_" + this.filename);
  }

  @Override
  public PPM applySepia() {
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
   * @return A PPM {@code PPM} for a ColorTransformation Image.
   */
  private PPM applyColorTransformation(AColorTransformation ct, String newFilename) {
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
    return new PPM(newFilename, newPixels);
  }

  @Override
  public PPM applySharpen() {
    Sharpen s = new Sharpen();
    return this.applyFilter(s, 5, "sharpened_" + this.filename);
  }

  @Override
  public PPM applyBlur() {
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
   * @return A PPM {@code PPM} for a filtered Image.
   */
  private PPM applyFilter(AFilter f, int matrixSize, String newFilename) {
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
    return new PPM(newFilename, p);
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
   * Creates a PPM file of a checkerboard, given the tile size, number of tiles, and two colors.
   * When put into a PPM reading software, it creates an image of the checkerboard.
   *
   * @param tileSize the length of the number of Pixels in a single tile on the board
   * @param numberOfTiles the number of tiles for the width of the checkerboard
   * @param one the first color of the tiles on the board
   * @param two the second color of the tiles on the board
   * @return A PPM {@code PPM} of a checkerboard image.
   */
  public PPM createCheckerBoard(int tileSize, int numberOfTiles, Color one, Color two) {
    if (numberOfTiles % 2 == 0) {
      return createEvenCheckerBoard(tileSize, numberOfTiles, one, two);
    } else {
      return createOddCheckerBoard(tileSize, numberOfTiles, one, two);
    }
  }

  /**
   * Creates a PPM file of a checkerboard, when the number of tiles for the length is even. When put
   * into a PPM reading software, it creates an image of the checkerboard.
   *
   * @param tileSize the length of the number of Pixels in a single tile on the board
   * @param numberOfTiles the number of tiles for the width of the checkerboard, which is even.
   * @param one the first color of the tiles on the board
   * @param two the second color of the tiles on the board
   * @return A PPM {@code PPM} of a checkerboard image.
   */
  private PPM createEvenCheckerBoard(int tileSize, int numberOfTiles, Color one, Color two) {
    ArrayList<ArrayList<Pixel>> boardPixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> pixelsOne = new ArrayList<Pixel>();
    for (int m = 0; m < numberOfTiles / 2; m++) {
      for (int i = 0; i < tileSize; i++) {
        pixelsOne = new ArrayList<Pixel>();
        for (int k = 0; k < numberOfTiles / 2; k++) {
          for (int j = 0; j < tileSize; j++) {
            Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
            pixelsOne.add(o);
          }
          for (int y = 0; y < tileSize; y++) {
            Pixel t = new Pixel(two.getRed(), two.getGreen(), two.getBlue());
            pixelsOne.add(t);
          }
        }
        boardPixels.add(pixelsOne);
      }
      for (int i = 0; i < tileSize; i++) {
        pixelsOne = new ArrayList<Pixel>();
        for (int k = 0; k < numberOfTiles / 2; k++) {
          for (int y = 0; y < tileSize; y++) {
            Pixel t = new Pixel(two.getRed(), two.getGreen(), two.getBlue());
            pixelsOne.add(t);
          }
          for (int j = 0; j < tileSize; j++) {
            Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
            pixelsOne.add(o);
          }
        }
        boardPixels.add(pixelsOne);
      }
    }
    return new PPM("checkerboard", boardPixels);
  }

  /**
   * Creates a PPM file of a checkerboard, when the number of tiles for the length is odd. When put
   * into a PPM reading software, it creates an image of the checkerboard.
   *
   * @param tileSize the length of the number of Pixels in a single tile on the board
   * @param numberOfTiles the number of tiles for the width of the checkerboard, which is odd.
   * @param one the first color of the tiles on the board
   * @param two the second color of the tiles on the board
   * @return A PPM {@code PPM} of a checkerboard image.
   */
  private PPM createOddCheckerBoard(int tileSize, int numberOfTiles, Color one, Color two) {
    ArrayList<ArrayList<Pixel>> boardPixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> pixelsOne = new ArrayList<Pixel>();
    for (int m = 0; m < numberOfTiles / 2; m++) {
      for (int i = 0; i < tileSize; i++) {
        pixelsOne = new ArrayList<Pixel>();
        for (int k = 0; k < numberOfTiles / 2; k++) {
          for (int j = 0; j < tileSize; j++) {
            Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
            pixelsOne.add(o);
          }
          for (int y = 0; y < tileSize; y++) {
            Pixel t = new Pixel(two.getRed(), two.getGreen(), two.getBlue());
            pixelsOne.add(t);
          }
        }
        for (int z = 0; z < tileSize; z++) {
          Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
          pixelsOne.add(o);
        }
        boardPixels.add(pixelsOne);
      }
      for (int i = 0; i < tileSize; i++) {
        pixelsOne = new ArrayList<Pixel>();
        for (int k = 0; k < numberOfTiles / 2; k++) {
          for (int y = 0; y < tileSize; y++) {
            Pixel t = new Pixel(two.getRed(), two.getGreen(), two.getBlue());
            pixelsOne.add(t);
          }
          for (int j = 0; j < tileSize; j++) {
            Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
            pixelsOne.add(o);
          }
        }
        for (int z = 0; z < tileSize; z++) {
          Pixel t = new Pixel(two.getRed(), two.getGreen(), two.getBlue());
          pixelsOne.add(t);
        }
        boardPixels.add(pixelsOne);
      }
    }
    for (int i = 0; i < tileSize; i++) {
      pixelsOne = new ArrayList<Pixel>();
      for (int k = 0; k < numberOfTiles / 2; k++) {
        for (int j = 0; j < tileSize; j++) {
          Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
          pixelsOne.add(o);
        }
        for (int y = 0; y < tileSize; y++) {
          Pixel t = new Pixel(two.getRed(), two.getGreen(), two.getBlue());
          pixelsOne.add(t);
        }
      }
      for (int z = 0; z < tileSize; z++) {
        Pixel o = new Pixel(one.getRed(), one.getGreen(), one.getBlue());
        pixelsOne.add(o);
      }
      boardPixels.add(pixelsOne);
    }
    return new PPM("checkerboard", boardPixels);
  }
}
