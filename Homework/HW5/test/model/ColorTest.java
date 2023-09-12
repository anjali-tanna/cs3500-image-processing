package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Test class for the Color enum. Tests the getRed(), getGreen(), and getBlue() methods for Colors.
 */
public class ColorTest {

  // tests the getRed() method for the Color enum
  @Test
  public void testGetRed() {
    assertEquals(255, Color.RED.getRed());
  }

  // tests the getRed() method for the color Lavender
  @Test
  public void testGetRedLavender() {
    assertEquals(204, Color.LAVENDER.getRed());
  }

  // tests the getRed() method for the color Black
  @Test
  public void testGetRedBlack() {
    assertEquals(0, Color.BLACK.getRed());
  }

  // tests the getRed() method for the color White
  @Test
  public void testGetRedWhite() {
    assertEquals(255, Color.WHITE.getRed());
  }

  // tests the getGreen() method for the Color enum
  @Test
  public void testGetGreen() {
    assertEquals(255, Color.GREEN.getGreen());
  }

  // tests the getGreen() method for the color Teal
  @Test
  public void testGetGreenTeal() {
    assertEquals(204, Color.TEAL.getGreen());
  }

  // tests the getGreen() method for the color Black
  @Test
  public void testGetGreenBlack() {
    assertEquals(0, Color.BLACK.getGreen());
  }

  // tests the getGreen() method for the color White
  @Test
  public void testGetGreenWhite() {
    assertEquals(255, Color.WHITE.getGreen());
  }

  // tests the getBlue() method for the Color enum
  @Test
  public void testGetBlue() {
    assertEquals(255, Color.BLUE.getBlue());
  }

  // tests the getBlue() method for the color Coral
  @Test
  public void testGetBlueCoral() {
    assertEquals(153, Color.CORAL.getBlue());
  }

  // tests the getBlue() method for the color Black
  @Test
  public void testGetBlueBlack() {
    assertEquals(0, Color.BLACK.getBlue());
  }

  // tests the getBlue() method for the color White
  @Test
  public void testGetBlueWhite() {
    assertEquals(255, Color.WHITE.getBlue());
  }
}
