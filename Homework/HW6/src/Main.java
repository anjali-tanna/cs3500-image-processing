import colortransformations.Grayscale;
import controller.AWriter;
import controller.PNGWriter;
import controller.SimpleImageController;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import model.AImage;
import model.Color;
import model.FileType;
import model.ImageUtil;
import model.PPM;
import controller.PPMWriter;
import model.Pixel;
import controller.ImageController;
import java.io.FileNotFoundException;

/** Main class for the model package. Allows for filter and color transformations to occur. */
public class Main {

  /**
   * The main method allows for the scripting.
   *
   * @param args the name of the file
   */
  public static void main(String[] args) throws FileNotFoundException {
    Readable rd = new InputStreamReader(System.in);
    Readable rdScriptOne = new InputStreamReader(new FileInputStream("res/scriptOne.txt"));
    // Readable rdScriptTwo = new InputStreamReader(new FileInputStream("res/scriptTwo.txt"));
    Appendable ap = System.out;
    ImageController controller = new SimpleImageController(rdScriptOne, ap);
    controller.start();
  }

  /**
   * The main method allows for the PPM images to be created and transformed.
   *
   * @param args the name of the file
   */
  public static void mainPPM(String[] args) {
    String filename;

    if (args.length > 0) {
      filename = args[0];
    } else {
      filename = "cape.png";
    }
    ImageUtil u = new ImageUtil();
    AImage ppm =
        u.readImage("cape.png", FileType.PNG).applyColorTransformation(new Grayscale(), "cape.png");
    AWriter w = new PNGWriter(ppm);
    w.writeFile();
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
