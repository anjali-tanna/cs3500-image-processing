package model;

/**
 * Represents a Pixel of an Image. Pixels make up an image and have a position in the image (row,
 * column), as well as a color. It handles the methods that get the Red, Green, and Blue Pixel
 * values of a Pixel.
 */
public class Pixel {

  private Integer red;
  private Integer green;
  private Integer blue;

  /**
   * Constructor for a Pixel, which includes Red Pixel value, Green Pixel value, and Blue Pixel
   * value.
   *
   * @throws IllegalArgumentException if any of these Pixel values are null.
   */
  public Pixel(Integer red, Integer green, Integer blue) {
    if (red == null || green == null || blue == null) {
      throw new IllegalArgumentException("Pixel values cannot be null");
    }
    if (red < 0) {
      red = 0;
    }
    if (red > 255) {
      red = 255;
    }
    if (green < 0) {
      green = 0;
    }
    if (green > 255) {
      green = 255;
    }
    if (blue < 0) {
      blue = 0;
    }
    if (blue > 255) {
      blue = 255;
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * A method that determines the Red value in a Pixel.
   *
   * @return the red value of a Pixel.
   */
  public int getRed() {
    Integer r = this.red;
    return r;
  }

  /**
   * A method that determines the Green value in a Pixel.
   *
   * @return the green value of a Pixel.
   */
  public int getGreen() {
    Integer g = this.green;
    return g;
  }

  /**
   * A method that determines the Blue value in a Pixel.
   *
   * @return the blue value of a Pixel.
   */
  public int getBlue() {
    Integer b = this.blue;
    return b;
  }
}
