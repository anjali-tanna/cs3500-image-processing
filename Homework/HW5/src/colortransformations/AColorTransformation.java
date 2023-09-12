package colortransformations;

import model.Pixel;

/**
 * Abstract class that represents an AColorTransformation which implements IColorTransformations
 * which allows for color transformation of Images. Includes the matrix of the color transformation.
 */
public abstract class AColorTransformation implements IColorTransformations {

  public double matrix[]
      [];

  /** Constructor for an AColorTransformation, which includes a matrix. */
  AColorTransformation() {
    this.matrix = matrix;
  }

  @Override
  public Pixel applyToPixel(Pixel p) {
    int newRed =
        (int)
            ((p.getRed() * this.matrix[0][0])
                + Math.round(p.getGreen() * this.matrix[0][1])
                + Math.round(p.getBlue() * this.matrix[0][2]));
    int newGreen =
        (int)
            ((p.getRed() * this.matrix[1][0])
                + Math.round(p.getGreen() * this.matrix[1][1])
                + Math.round(p.getBlue() * this.matrix[1][2]));
    int newBlue =
        (int)
            ((p.getRed() * this.matrix[2][0])
                + Math.round(p.getGreen() * this.matrix[2][1])
                + Math.round(p.getBlue() * this.matrix[2][2]));
    return new Pixel(newRed, newGreen, newBlue);
  }
}
