package model;

import static javax.imageio.ImageIO.read;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.awt.Color;

/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static ArrayList<ArrayList<Pixel>> readPPM(String filename) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    // read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    // now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 256): " + maxValue);

    ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> row = new ArrayList<Pixel>();
    for (int i = 0; i < height; i++) {
      row = new ArrayList<Pixel>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel current = new Pixel(r, g, b);
        row.add(current);
      }
      pixels.add(row);
    }
    return pixels;
  }

  /**
   * Reads the file of the image provided.
   *
   * @param filename the name of the file
   * @param f        the type of file, PPM, PNG, or JPEG
   * @return An AImage that was read
   */
  public AImage readImage(String filename, FileType f) {
    if (filename == null || f == null) {
      throw new IllegalArgumentException("Filename and filetype cannot be null");
    }
    if (!f.type.equals(filename.substring(filename.length() - 3))) {
      throw new IllegalArgumentException("Filetype does not match with the type specified");
    }
    if (f == FileType.PPM) {
      return new PPM(filename, readPPM(filename));
    }
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + filename + " not found!");
    }
    try {
      BufferedImage im = read(new FileInputStream(filename));
      int height = im.getHeight();
      int length = im.getWidth();
      ArrayList<ArrayList<Pixel>> pixels = new ArrayList<ArrayList<Pixel>>();
      ArrayList<Pixel> row = new ArrayList<Pixel>();
      for (int i = 0; i < height; i++) {
        row = new ArrayList<Pixel>();
        for (int k = 0; k < length; k++) {
          Color clr = new Color(im.getRGB(k, i), true);
          int r = clr.getRed();
          int g = clr.getGreen();
          int b = clr.getBlue();
          row.add(new Pixel(r, g, b));
        }
        pixels.add(row);
      }
      switch (f) {
        case JPEG:
          return new JPEG(filename, pixels);
        case PNG:
          return new PNG(filename, pixels);
        default:
          throw new IllegalArgumentException("File type invalid");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("File invalid");
    }
  }

  /**
   * Writes the file for the given type and image.
   *
   * @param ft    the type of file
   * @param image the image being written
   * @return a BufferedImage of the writen file
   */
  public BufferedImage writeImage(FileType ft, AImage image) {
    if (image == null || ft == null) {
      throw new IllegalArgumentException("Image and filetype cannot be null");
    }
    Scanner sc = null;
    try {
      sc = new Scanner(new FileInputStream(image.getFilename()));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File " + image.getFilename() + " not found!");
    }
    try {
      BufferedImage im = read(new FileInputStream(image.getFilename()));
      for (int i = 0; i < image.getPixels().size(); i++) {
        for (int k = 0; k < image.getPixels().get(0).size(); k++) {
          int r = image.getPixels().get(i).get(k).getRed();
          int g = image.getPixels().get(i).get(k).getGreen();
          int b = image.getPixels().get(i).get(k).getBlue();
          int rgb = (r << 16 | g << 8 | b);
          im.setRGB(k, i, rgb);
        }
      }
      return im;
    } catch (IOException e) {
      throw new IllegalArgumentException("File invalid");
    }
  }

  /**
   * Loads the layers of the file.
   *
   * @param filename the name of the file with the layers being loaded
   * @return a LayeredImage of the file with layers
   */
  public LayeredImage loadLayers(String filename) {
    if (filename == null) {
      throw new IllegalArgumentException("Filename cannot be null");
    }
    ArrayList<Layer> layers = new ArrayList<Layer>();
    Scanner sc = null;

    StringBuilder booleanBuilder = new StringBuilder();
    StringBuilder fileBuilder = new StringBuilder();
    StringBuilder typeBuilder = new StringBuilder();
    try {
      sc = new Scanner(new FileInputStream(filename));
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        fileBuilder.append(s);
        String b = sc.nextLine();
        booleanBuilder.append(b);
        String f = sc.nextLine();
        typeBuilder.append(f);
      }
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
    }
    Scanner bool = new Scanner(booleanBuilder.toString());
    Scanner files = new Scanner(fileBuilder.toString());
    Scanner types = new Scanner(typeBuilder.toString());
    while (types.hasNext()) {
      switch (types.next()) {
        case "jpg":
          layers.add(
              new Layer(Boolean.parseBoolean(bool.next()), readImage(files.next(), FileType.JPEG)));
          break;
        case "ppm":
          layers.add(
              new Layer(Boolean.parseBoolean(bool.next()), readImage(files.next(), FileType.PPM)));
          break;
        case "png":
          layers.add(
              new Layer(Boolean.parseBoolean(bool.next()), readImage(files.next(), FileType.PNG)));
          break;
        default:
          throw new IllegalArgumentException("Filename has invalid file format");
      }
    }
    return new LayeredImage(layers);
  }
}
