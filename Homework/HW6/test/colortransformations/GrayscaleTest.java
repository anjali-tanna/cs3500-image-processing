package colortransformations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Test class for the Grayscale. Tests the Grayscale filter. */
public class GrayscaleTest {

  // tests the values of the Grayscale matrix in the first column
  @Test
  public void testGrayscaleCol1() {
    AColorTransformation g = new Grayscale();
    boolean t = .2126 == g.matrix[0][0] && .2126 == g.matrix[1][0] && .2126 == g.matrix[2][0];
    assertEquals(true, t);
  }

  // tests the values of the Grayscale matrix in the second column
  @Test
  public void testGrayscaleCol2() {
    AColorTransformation g = new Grayscale();
    boolean t = .7152 == g.matrix[0][1] && .7152 == g.matrix[1][1] && .7152 == g.matrix[2][1];
    assertEquals(true, t);
  }

  // tests the values of the Grayscale matrix in the third column
  @Test
  public void testGrayscaleCol3() {
    AColorTransformation g = new Grayscale();
    boolean t = .0722 == g.matrix[0][2] && .0722 == g.matrix[1][2] && .0722 == g.matrix[2][2];
    assertEquals(true, t);
  }
}
