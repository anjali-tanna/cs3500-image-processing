package colortransformations;

import model.Pixel;

/**
 * Interface to represent IColorTransformations which handles the applyToPixel() method, and is
 * implemented by the abstract class AColorTransformation.
 */
public interface IColorTransformations {

  /**
   * Applies the ColorTransformation to an Pixel. Grayscale creates a black and white grayscale
   * effect on the Pixel. Sepia creates a sepia effect on the Pixel.
   *
   * @return A Pixel {@code Pixel} for a Grayscale or Sepia applied ColorTransformation on a Pixel.
   */
  Pixel applyToPixel(Pixel p);
}
