package model;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;

/** Test class for the Layer class. */
public class LayerTest {

  // tests the getFileType() method for PPM
  @Test()
  public void testGetFileTypePPM() {
    AImage a = new PPM("filename", new ArrayList<ArrayList<Pixel>>());
    Layer l = new Layer(true, a);
    assertEquals(FileType.PPM, l.getFileType());
  }

  // tests the getFileType() method for PNG
  @Test()
  public void testGetFileTypePNG() {
    AImage a = new PNG("filename", new ArrayList<ArrayList<Pixel>>());
    Layer l = new Layer(true, a);
    assertEquals(FileType.PNG, l.getFileType());
  }

  // tests the getFileType() method for JPEG
  @Test()
  public void testGetFileTypeJPEG() {
    AImage a = new JPEG("filename", new ArrayList<ArrayList<Pixel>>());
    Layer l = new Layer(true, a);
    assertEquals(FileType.JPEG, l.getFileType());
  }

  // tests the getVisibility() method
  @Test
  public void testGetVisibility() {
    AImage a = new PNG("filename", new ArrayList<ArrayList<Pixel>>());
    Layer visible = new Layer(true, a);
    Layer notVisible = new Layer(false, a);
    assertEquals(true, visible.getVisibility());
    assertEquals(false, notVisible.getVisibility());
  }

  // tests the getImage() method
  @Test()
  public void testGetImage() {
    AImage a = new JPEG("filename", new ArrayList<ArrayList<Pixel>>());
    Layer l = new Layer(true, a);
    assertEquals(a, l.getImage());
  }
}
