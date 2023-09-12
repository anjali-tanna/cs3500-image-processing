import java.util.ArrayList;
import model.Color;
import model.ImageUtil;
import model.PPM;
import model.PPMWriter;
import model.Pixel;

/** Main class for the model package. Allows for filter and color transformations to occur. */
public class Main {

  /**
   * The main method allows for the PPM images to be created and transformed.
   *
   * @param args the name of the file
   */
  public static void main(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "milo.ppm";
    }
    ImageUtil util = new ImageUtil();
    PPM ppm = new PPM("milo", util.readPPM("milo.ppm")).applyGrayscale();
    PPMWriter writer = new PPMWriter(ppm);
    writer.writeFile();
  }

  /**
   * Main method to create the checkerBoard image.
   *
   * @param args the name of the file
   */
  public static void checkerBoardMain(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "checkerboard.ppm";
    }
    PPM ppm = new PPM("checkerboard", new ArrayList<ArrayList<Pixel>>());
    PPMWriter writer = new PPMWriter(ppm.createCheckerBoard(100, 8, Color.LAVENDER, Color.PINK));
    writer.writeFile();
  }
}
