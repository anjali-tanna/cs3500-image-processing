package colortransformations;

/**
 * Represents a Sepia filter, which extends the AColorTransformation class. It creates the matrix
 * for the Sepia filter.
 */
public class Sepia extends AColorTransformation {

  /**
   * Constructor for a Sepia, which includes a matrix needed in order to sepia an image.
   */
  public Sepia() {
    this.matrix = new double[][]{{.393, .769, .189}, {.349, .686, .168}, {.272, .534, .131}};
  }
}
