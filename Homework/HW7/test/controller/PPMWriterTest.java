package controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import model.PPM;
import model.Pixel;
import org.junit.Test;

/** Test class for the PPMWriter class. Tests the methods used to write PPM files. */
public class PPMWriterTest {

  // tests if the image is null, throws exception
  @Test(expected = IllegalArgumentException.class)
  public void testNullImage() {
    PPMWriter ppmw = new PPMWriter(null);
  }

  // tests writeFile() method
  @Test
  public void testWriteFile() {
    PPM steve = new PPM("steve", new ArrayList<ArrayList<Pixel>>());
    PPMWriter ppmw = new PPMWriter(steve);
    ppmw.writeFile();
    assertEquals(true, ppmw);
  }
}
