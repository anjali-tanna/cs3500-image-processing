package view;

import java.io.IOException;

/**
 * SimpleImageView implements the ImageView interface and the methods renderMessage. It handles the
 * Image model and an Appendable to display the image state as a string.
 */
public class SimpleImageView implements ImageView {

  private final Appendable ap;

  /** Constructor for SimpleImageView. */
  public SimpleImageView(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.ap.append(message);
    } catch (IOException e) {
      throw new IOException("Couldn't display message");
    }
  }
}
