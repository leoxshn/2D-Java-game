package posidon.pixelium;

import java.awt.image.BufferedImage;

public class SpriteSheet {
  public BufferedImage img;

  public SpriteSheet(BufferedImage img) {
    this.img = img;
  }

  public BufferedImage grabimg(int column, int row, int width, int height) {
    return img.getSubimage((column-1)*32, (row-1)*32, width, height);
  }
}
