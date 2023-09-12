package model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/** Test class for the FileType enum. Tests the getType() method. */
public class FileTypeTest {

  // tests the getType() method for PNG
  @Test
  public void testGetTypePNG() {
    assertEquals("png", FileType.PNG.getType());
  }

  // tests the getType() method for PPM
  @Test
  public void testGetTypePPM() {
    assertEquals("ppm", FileType.PPM.getType());
  }

  // tests the getType() method for JPEG
  @Test
  public void testGetTypeJPEG() {
    assertEquals("jpeg", FileType.JPEG.getType());
  }
}
