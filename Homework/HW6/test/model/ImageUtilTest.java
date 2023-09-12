package model;

import java.util.ArrayList;
import org.junit.Test;

/** Test class for the ImageUtil class. */
public class ImageUtilTest {

  // tests the readImage() method for a null filename
  @Test(expected = IllegalArgumentException.class)
  public void testReadImageNullFileName() {
    ImageUtil iu = new ImageUtil();
    iu.readImage(null, FileType.PNG);
  }

  // tests the readImage() method for a null FileType
  @Test(expected = IllegalArgumentException.class)
  public void testReadImageNullFileType() {
    ImageUtil iu = new ImageUtil();
    iu.readImage("null", null);
  }

  // tests the writeImage() method for a null filename
  @Test(expected = IllegalArgumentException.class)
  public void testWriteImageNullFileName() {
    ImageUtil iu = new ImageUtil();
    AImage a = new PPM("null", new ArrayList<ArrayList<Pixel>>());
    iu.writeImage(null, a);
  }

  // tests the writeImage() method for a null FileType
  @Test(expected = IllegalArgumentException.class)
  public void testWriteImageNullFileType() {
    ImageUtil iu = new ImageUtil();
    iu.writeImage(FileType.JPEG, null);
  }

  // tests the loadLayers() method for a null filename
  @Test(expected = IllegalArgumentException.class)
  public void testLoadLayersNull() {
    ImageUtil iu = new ImageUtil();
    iu.loadLayers(null);
  }
}
