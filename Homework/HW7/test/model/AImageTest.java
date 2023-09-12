package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import colortransformations.Sepia;
import filtertransformations.Blur;
import org.junit.Test;

/**
 * Test class for the AImage class. Tests the getFilename(), applyColorTransformation(),
 * applyFilter(), convertTo(), and makeLayered() methods.
 */
public class AImageTest {

  // tests the getFilename() method
  @Test
  public void testGetFilename() {
    AImage image = new PNG("anjali and steve", new ArrayList<ArrayList<Pixel>>());
    assertEquals("anjali and steve", image.getFilename());
  }

  // tests the getPixels() method
  @Test
  public void testGetPixels() {
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    assertEquals(lolop, image.getPixels());
  }

  // tests the applyColorTransformation() method
  @Test
  public void testApplyColorTransformation() {
    Sepia s = new Sepia();
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    AImage newImage = new JPEG("sepia_OOD Summer 1", lolop);
    assertEquals(newImage, image.applyColorTransformation(s, "sepia_OOD Summer 1"));
  }

  // tests the applyFilter() method
  @Test
  public void testApplyFilter() {
    Blur b = new Blur();
    Pixel one = new Pixel(33, 55, 123);
    Pixel two = new Pixel(15, 36, 38);
    Pixel three = new Pixel(57, 124, 155);
    ArrayList<Pixel> pixels = new ArrayList<Pixel>(Arrays.asList(one, two, three));
    ArrayList<ArrayList<Pixel>> lolop = new ArrayList<ArrayList<Pixel>>(Arrays.asList(pixels));
    AImage image = new JPEG("OOD Summer 1", lolop);
    AImage newImage = new JPEG("blurred_OOD Summer 1", lolop);
    assertEquals(newImage, image.applyFilter(b, b.matrix[0].length, "blurred_OOD Summer 1"));
  }

  // tests the convertTo() method
  @Test(expected = IllegalArgumentException.class)
  public void testConvertToNull() {
    PNG p = new PNG("Null", new ArrayList<ArrayList<Pixel>>());
    AImage a = p.convertTo(null);
  }

  // tests the makeLayered() method
  @Test(expected = IllegalArgumentException.class)
  public void testMakeLayeredNull() {
    LayeredImage l = new LayeredImage(new ArrayList<Layer>());
    AImage a = new PPM("Null", new ArrayList<ArrayList<Pixel>>());
    a.makeLayered(null, true);
  }
}
