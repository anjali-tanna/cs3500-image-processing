package model;

import controller.PPMWriter;
import java.util.ArrayList;

/**
 * Represents a PPM, which extends the AImage class, which implements the Image interface. It
 * handles the methods that apply ColorTransformations and Filters to an PPM file. The PPM files are
 * a type of image file, and this class supports importing and exporting PPM files.
 */
public class PPM extends AImage {

  /**
   * Constructor for a PPM, which includes the super(filename), and pixels, which is the list of
   * pixels in the PPM file.
   */
  public PPM(String filename, ArrayList<ArrayList<Pixel>> pixels) {
    super(filename, pixels);
  }

  /**
   * Creates a PPM file of a checkerboard, given the tile size, number of tiles, and two colors.
   * When put into a PPM reading software, it creates an image of the checkerboard.
   *
   * @param tileSize      the length of the number of Pixels in a single tile on the board
   * @param numberOfTiles the number of tiles for the width of the checkerboard
   * @param one           the first color of the tiles on the board
   * @param two           the second color of the tiles on the board
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
   * @param tileSize      the length of the number of Pixels in a single tile on the board
   * @param numberOfTiles the number of tiles for the width of the checkerboard, which is even.
   * @param one           the first color of the tiles on the board
   * @param two           the second color of the tiles on the board
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
   * @param tileSize      the length of the number of Pixels in a single tile on the board
   * @param numberOfTiles the number of tiles for the width of the checkerboard, which is odd.
   * @param one           the first color of the tiles on the board
   * @param two           the second color of the tiles on the board
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

  @Override
  public void save() {
    PPMWriter writer = new PPMWriter(this);
    writer.createNewFile(this.filename);
    writer.writeFile();
  }
}
