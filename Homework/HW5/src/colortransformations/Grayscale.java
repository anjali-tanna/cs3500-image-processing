package colortransformations;

/**
 * Represents a Grayscale filter, which extends the AColorTransformation class. It creates the
 * matrix for the Grayscale filter.
 */
public class Grayscale extends AColorTransformation {

  /** Constructor for a Grayscale, which includes a matrix needed in order to grayscale an image. */
  public Grayscale() {
    this.matrix =
        new double[][] {{.2126, .7152, .0722}, {.2126, .7152, .0722}, {.2126, .7152, .0722}};
  }
}
