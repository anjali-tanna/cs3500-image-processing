package filtertransformations;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Test class for the Blur class. Tests the Blur filter at each Row and Column. */
public class BlurTest {

  // tests the [0, 0] constructor value of the Blur matrix
  @Test
  public void testBlurConstruct() {
    AFilter b = new Blur();
    assertEquals(.0625, b.matrix[0][0], 0);
  }

  // tests the first row and second column values of the Blur matrix
  @Test
  public void testBlurRow0Col1() {
    AFilter b = new Blur();
    assertEquals(.125, b.matrix[0][1], 0);
  }

  // tests the first row and third column values of the Blur matrix
  @Test
  public void testBlurRow0Col2() {
    AFilter b = new Blur();
    assertEquals(.0625, b.matrix[0][2], 0);
  }

  // tests the second row and first column values of the Blur matrix
  @Test
  public void testBlurRow1Col0() {
    AFilter b = new Blur();
    assertEquals(.125, b.matrix[1][0], 0);
  }

  // tests the second row and second column values of the Blur matrix
  @Test
  public void testBlurRow1Col1() {
    AFilter b = new Blur();
    assertEquals(.25, b.matrix[1][1], 0);
  }

  // tests the second row and third column values of the Blur matrix
  @Test
  public void testBlurRow1Col2() {
    AFilter b = new Blur();
    assertEquals(.125, b.matrix[1][2], 0);
  }

  // tests the third row and first column values of the Blur matrix
  @Test
  public void testBlurRow2Col0() {
    AFilter b = new Blur();
    assertEquals(.0625, b.matrix[2][0], 0);
  }

  // tests the third row and second column values of the Blur matrix
  @Test
  public void testBlurRow2Col1() {
    AFilter b = new Blur();
    assertEquals(.125, b.matrix[2][1], 0);
  }

  // tests the third row and third column values of the Blur matrix
  @Test
  public void testBlurRow2Col2() {
    AFilter b = new Blur();
    assertEquals(.0625, b.matrix[2][2], 0);
  }
}
