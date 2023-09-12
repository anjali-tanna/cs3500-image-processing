package filtertransformations;

/**
 * Represents a Blur filter, which extends the AFilter class. It creates the matrix for the Blur
 * filter.
 */
public class Blur extends AFilter {

  /** Constructor for a Blur, which includes a matrix needed in order to blur an image. */
  public Blur() {
    this.matrix = new double[][] {{.0625, .125, .0625}, {.125, .25, .125}, {.0625, .125, .0625}};
  }
}
