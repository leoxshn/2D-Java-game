package posidon.pixelium;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Point;

public class window {
  private JFrame frame;
  public window(int width, int height, String title) {
    frame = new JFrame(title);
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));
    frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(getClass().getResource("cursor.png")).getImage(),new Point(0,0),"custom cursor"));

    frame.setResizable(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public void startgame(Game game) {
    frame.add(game);
  }

  public int width() {
    return frame.getWidth();
  }
  public int height() {
    return frame.getHeight();
  }
}
