package colortransformations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Test class for the Sepia. Tests the Sepia filter. */
public class SepiaTest {

  // tests the [0, 0] constructor value of the Sepia matrix
  @Test
  public void testSepiaConstruct() {
    AColorTransformation s = new Sepia();
    assertEquals(.393, s.matrix[0][0], 0);
  }

  // tests the first row and second column values of the Sepia matrix
  @Test
  public void testSepiaRow0Col1() {
    AColorTransformation s = new Sepia();
    assertEquals(.769, s.matrix[0][1], 0);
  }

  // tests the first row and third column values of the Sepia matrix
  @Test
  public void testSepiaRow0Col2() {
    AColorTransformation s = new Sepia();
    assertEquals(.189, s.matrix[0][2], 0);
  }

  // tests the second row and first column values of the Sepia matrix
  @Test
  public void testSepiaRow1Col0() {
    AColorTransformation s = new Sepia();
    assertEquals(.349, s.matrix[1][0], 0);
  }

  // tests the second row and second column values of the Sepia matrix
  @Test
  public void testSepiaRow1Col1() {
    AColorTransformation s = new Sepia();
    assertEquals(.686, s.matrix[1][1], 0);
  }

  // tests the second row and third column values of the Sepia matrix
  @Test
  public void testSepiaRow1Col2() {
    AColorTransformation s = new Sepia();
    assertEquals(.168, s.matrix[1][2], 0);
  }

  // tests the third row and first column values of the Sepia matrix
  @Test
  public void testSepiaRow2Col0() {
    AColorTransformation s = new Sepia();
    assertEquals(.272, s.matrix[2][0], 0);
  }

  // tests the third row and second column values of the Sepia matrix
  @Test
  public void testSepiaRow2Col1() {
    AColorTransformation s = new Sepia();
    assertEquals(.534, s.matrix[2][1], 0);
  }

  // tests the third row and third column values of the Sepia matrix
  @Test
  public void testSepiaRow2Col2() {
    AColorTransformation s = new Sepia();
    assertEquals(.131, s.matrix[2][2], 0);
  }
}
