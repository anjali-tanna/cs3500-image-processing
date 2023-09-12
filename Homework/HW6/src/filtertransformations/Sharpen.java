package filtertransformations;

/**
 * Represents a Sharpen filter, which extends the AFilter class. It creates the matrix for the
 * Sharpen filter.
 */
public class Sharpen extends AFilter {

  /** Constructor for a Sharpen, which includes a matrix needed in order to sharpen an image. */
  public Sharpen() {
    this.matrix =
        new double[][] {
          {-.125, -.125, -.125, -.125, -.125},
          {-.125, .25, .25, .25, -.125},
          {-.125, .25, 1, .25, -.125},
          {-.125, .25, .25, .25, -.125},
          {-.125, -.125, -.125, -.125, -.125}
        };
  }
}
