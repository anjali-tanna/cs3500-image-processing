package colortransformations;

import static org.junit.Assert.assertEquals;
import model.Pixel;
import org.junit.Test;

/** Test class for the AColorTransformation class. Tests the applyToPixel() method. */
public class AColorTransformationTest {

  // tests applyToPixel() method
  @Test
  public void applyToPixel() {
    Pixel p = new Pixel(12, 129, 210);
    AColorTransformation act = new Sepia();
    assertEquals(true, act.applyToPixel(p));
  }
}
