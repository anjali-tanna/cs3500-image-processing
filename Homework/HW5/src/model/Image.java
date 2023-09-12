package model;

/**
 * This interface represents different operations that an Image model must support to return various
 * aspects of its state.
 */
public interface Image {

  /**
   * Applies the Grayscale ColorTransformation to an Image. Grayscale creates a black and white
   * grayscale effect on the image.
   *
   * @return A PPM {@code PPM} for a Grayscale applied ColorTransformation Image.
   */
  public AImage applyGrayscale();

  /**
   * Applies a Sepia ColorTransformation to an Image. Sepia creates a sepia effect on the image.
   *
   * @return A PPM {@code PPM} for a Sepia applied ColorTransformation Image.
   */
  public AImage applySepia();

  /**
   * Applies a Sharpen Filter to an Image. Sharpen creates a sharpened effect on the image.
   *
   * @return A PPM {@code PPM} for a Sharpen filtered Image.
   */
  public AImage applySharpen();

  /**
   * Applies a Blur Filter to an Image. Blur creates a blurred effect on the image.
   *
   * @return A PPM {@code PPM} for a Blur filtered Image.
   */
  public AImage applyBlur();
}
