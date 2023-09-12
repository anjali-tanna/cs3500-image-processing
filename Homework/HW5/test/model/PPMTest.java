package model;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.Test;

/**
 * Test class for the PPM class. Tests the methods that apply ColorTransformations and Filters to
 * images.
 */
public class PPMTest {

  // test for when the filename is null, throws exception
  @Test(expected = IllegalArgumentException.class)
  public void testNullFilename() {
    PPM s = new PPM(null, new ArrayList<ArrayList<Pixel>>());
  }

  // test for getPixels() method
  @Test
  public void testGetPixels() {
    PPM testChecker = new PPM("check", new ArrayList<ArrayList<Pixel>>());
    testChecker.createCheckerBoard(10, 5, Color.PINK, Color.BLACK);
    assertEquals(true, testChecker.pixels.equals(testChecker.getPixels()));
  }

  // test for applyGrayscale() method
  @Test
  public void testGrayscale() {
    PPM testChecker = new PPM("check", new ArrayList<ArrayList<Pixel>>());
    testChecker.createCheckerBoard(10, 5, Color.PINK, Color.BLACK);
    boolean t =
        testChecker.applyGrayscale().pixels.get(0).get(0).getRed()
                == testChecker.applyGrayscale().pixels.get(0).get(0).getGreen()
            && testChecker.applyGrayscale().pixels.get(0).get(0).getRed()
                == testChecker.applyGrayscale().pixels.get(0).get(0).getBlue();
    assertEquals(true, t);
  }
}
