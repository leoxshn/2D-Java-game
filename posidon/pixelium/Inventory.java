package posidon.pixelium;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.util.ArrayList;
import java.util.List;
import posidon.pixelium.gameobj.*;
import posidon.pixelium.*;

public class Inventory {

  public int selection;
  public List<Gameobject> dock = new ArrayList<Gameobject>(9);

  private Handler handler;
  private SpriteSheet ss;

  public Inventory(Handler handler, SpriteSheet ss) {
    this.handler = handler;
    this.ss = ss;
    selection = 0;
  }

  public void render(Graphics2D g, window window) {
    g.setColor(new Color(50, 50, 50, 200));
    g.fillRect(0, 0, window.width(), window.height());
    g.setColor(new Color(255, 255, 255, 200));
    g.fillRect(40, 40, window.width() / 2 - 100, window.height() - 80);
  }

  public void renderdock(Graphics2D g, window window) {
    g.setColor(new Color(0, 0, 0, 200));
    g.fillRect((window.width()-48*9)/2, window.height() - 96, 48*9, 48);
    g.setColor(new Color(0, 0, 0, 255));
    g.setStroke(new BasicStroke(3));
    g.drawRect((window.width()-48*9)/2, window.height() - 96, 48*9, 48);
    g.setColor(new Color(0, 255, 0, 255));
    g.setStroke(new BasicStroke(5));
    g.drawRect((window.width()-48*9)/2 + 48*selection, window.height() - 96, 48, 48);
    try {
      for (int i = 0; i < 9; i++) {
        g.drawImage(dock.get(i).img, (window.width()-48*9)/2 + 48*i + 8, window.height() - 88, null);
      }
    } catch(Exception e) {}
  }

  public void tick() {
    try {
      dock.set(0, new Block(0, 0, ID.block, ss, 0));
      dock.set(1, new Block(0, 0, ID.block, ss, 1));
      dock.set(2, new Block(0, 0, ID.block, ss, 2));
      dock.set(3, new Block(0, 0, ID.block, ss, 3));
      dock.set(4, new Block(0, 0, ID.block, ss, 4));
      dock.set(5, new Mob(0, 0, ID.entity, ss, handler));
    } catch (Exception e) {
      dock.add(new Block(0, 0, ID.block, ss, 0));
      dock.add(new Block(0, 0, ID.block, ss, 1));
      dock.add(new Block(0, 0, ID.block, ss, 2));
      dock.add(new Block(0, 0, ID.block, ss, 3));
      dock.add(new Block(0, 0, ID.block, ss, 4));
      dock.add(new Mob(0, 0, ID.entity, ss, handler));
    }
  }
}
