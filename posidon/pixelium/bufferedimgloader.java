package posidon.pixelium;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Color;
import java.io.File;

public class bufferedimgloader {

  private BufferedImage image;

  public BufferedImage loadextimage(String path) {
    try { image = ImageIO.read(new File(System.getProperty("user.dir")+path)); }
    catch (Exception e) { e.printStackTrace(); }
    return image;
  }

  public BufferedImage loadmap(String path) {
    try { image = ImageIO.read(new File(System.getProperty("user.dir")+"/pixelium/world/"+path)); }
    catch (Exception e) {
      e.printStackTrace();
      int w = 200, h = 200;
      image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
      Graphics2D g = image.createGraphics();
      g.setColor(Color.black);
      g.fillRect(0, 0, w, h);
      g.dispose();
      try {
        File file = new File(System.getProperty("user.dir")+"/pixelium/world/"+path);
        file.mkdirs();
        ImageIO.write(image, "png", file);
      } catch (Exception e2) { e2.printStackTrace(); }
    }
    return image;
  }

  public BufferedImage loadimage(String path) {
    try { image = ImageIO.read(getClass().getResource(path)); }
    catch (Exception e) { e.printStackTrace(); }
    return image;
  }

  public boolean saveimage(BufferedImage img, String path) {
    try {
      ImageIO.write(img, "png", new File(System.getProperty("user.dir")+"/pixelium/"+path));
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
