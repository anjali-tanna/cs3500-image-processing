package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the Pixel class. Tests the getRed(), getGreen(), and getBlue() methods for Pixels.
 */
public class PixelTest {

  // tests the getRed() method when the Pixel is out of Bounds
  @Test
  public void testPixelOutBoundsRed() {
    Pixel p = new Pixel(-2, 12, 14);
    assertEquals(0, p.getRed());

    Pixel z = new Pixel(290, 3, 4);
    assertEquals(255, z.getRed());
  }

  // tests the getGreen() method when the Pixel is out of Bounds
  @Test
  public void testPixelOutBoundsGreen() {
    Pixel p = new Pixel(34, -4, 14);
    assertEquals(0, p.getGreen());

    Pixel z = new Pixel(123, 256, 4);
    assertEquals(255, z.getGreen());
  }

  // tests the getBlue() method when the Pixel is out of Bounds
  @Test
  public void testPixelOutBoundsBlue() {
    Pixel p = new Pixel(34, 231, -1);
    assertEquals(0, p.getBlue());

    Pixel z = new Pixel(123, 200, 400);
    assertEquals(255, z.getBlue());
  }

  // tests the getRed() method when the Red Pixel value is 0
  @Test
  public void testGetRed() {
    Pixel pixel = new Pixel(0, 255, 255);
    assertEquals(0, pixel.getRed());
  }

  // tests the getRed() method when the Red Pixel value is 255
  @Test
  public void testGetRed2() {
    Pixel pixel = new Pixel(255, 0, 255);
    assertEquals(255, pixel.getRed());
  }

  // tests the getRed() method when the Red Pixel value is 154
  @Test
  public void testGetRed3() {
    Pixel pixel = new Pixel(154, 255, 255);
    assertEquals(154, pixel.getRed());
  }

  // tests the getGreen() method when the Green Pixel value is 0
  @Test
  public void testGetGreen() {
    Pixel pixel = new Pixel(255, 0, 255);
    assertEquals(0, pixel.getGreen());
  }

  // tests the getGreen() method when the Green Pixel value is 255
  @Test
  public void testGetGreen2() {
    Pixel pixel = new Pixel(0, 255, 255);
    assertEquals(255, pixel.getGreen());
  }

  // tests the getGreen() method when the Green Pixel value is 176
  @Test
  public void testGetGreen3() {
    Pixel pixel = new Pixel(154, 176, 255);
    assertEquals(176, pixel.getGreen());
  }

  // tests the getBlue() method when the Blue Pixel value is 0
  @Test
  public void testGetBlue() {
    Pixel pixel = new Pixel(0, 255, 0);
    assertEquals(0, pixel.getBlue());
  }

  // tests the getBlue() method when the Blue Pixel value is 255
  @Test
  public void testGetBlue2() {
    Pixel pixel = new Pixel(255, 0, 255);
    assertEquals(255, pixel.getBlue());
  }

  // tests the getBlue() method when the Blue Pixel value is 185
  @Test
  public void testGetBlue3() {
    Pixel pixel = new Pixel(0, 255, 185);
    assertEquals(185, pixel.getBlue());
  }
}
