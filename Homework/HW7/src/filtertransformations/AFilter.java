package filtertransformations;

/**
 * Abstract class that represents an AFilter which allows for filtering of Images. Includes the
 * matrix of the filter.
 */
public abstract class AFilter {

  public double[][] matrix;

  /**
   * Constructor for an AFilter, which includes a matrix needed in order to filter an image.
   */
  AFilter() {
    this.matrix = matrix;
  }
}
