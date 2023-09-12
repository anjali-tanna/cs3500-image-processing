package model;

/**
 * Colors created by Red, Green, and Blue values in a Pixel. <br>
 * The Colors in this enum are RED, BLUE, GREEN, BLACK, WHITE, PINK, CORAL, TEAL, LAVENDER.
 */
public enum Color {
  RED(new Pixel(255, 0, 0)),
  BLUE(new Pixel(0, 0, 255)),
  GREEN(new Pixel(0, 255, 0)),
  BLACK(new Pixel(0, 0, 0)),
  WHITE(new Pixel(255, 255, 255)),
  PINK(new Pixel(255, 51, 255)),
  CORAL(new Pixel(255, 153, 153)),
  TEAL(new Pixel(0, 204, 204)),
  LAVENDER(new Pixel(204, 153, 255));

  private final Pixel color;

  Color(Pixel color) {
    if (color == null) {
      throw new IllegalArgumentException("Pixel for a color cannot be null");
    }
    this.color = color;
  }

  /**
   * A method that determines the Red value in a Pixel.
   *
   * @return the red value of a Pixel.
   */
  public int getRed() {
    return this.color.getRed();
  }

  /**
   * A method that determines the Green value in a Pixel.
   *
   * @return the green value of a Pixel.
   */
  public int getGreen() {
    return this.color.getGreen();
  }

  /**
   * A method that determines the Blue value in a Pixel.
   *
   * @return the blue value of a Pixel.
   */
  public int getBlue() {
    return this.color.getBlue();
  }
}
