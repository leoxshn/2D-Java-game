package posidon.pixelium.gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import posidon.pixelium.*;

public class Drop extends Gameobject {

  int ammo;

  public Drop(int x, int y, ID id, SpriteSheet ss, int ammo) {
    super(x, y, id, ss);
    this.ammo = ammo;
    spatial = true;
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(Color.yellow);
    g.fillRect(x, y, 16, 16);
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, 16, 16);
  }
}
