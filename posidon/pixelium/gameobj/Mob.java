package posidon.pixelium.gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.Random;
import java.awt.image.BufferedImage;
import posidon.pixelium.*;

public class Mob extends Gameobject {

  private Handler handler;
  public int health = 20;

  public Mob(int x, int y, ID id, SpriteSheet ss, Handler handler) {
    super(x, y, id, ss);
    this.handler = handler;
    img = ss.grabimg(5, 2, 32, 32);
    spatial = true;
  }

  public void tick() {
    x += velx;
    y += vely;
    BufferedImage temp = handler.game.blockmap;
    for (int xx = -1; xx < 2; xx++) {
      for (int yy = -1; yy < 2; yy++) {
        try {
          if (temp.getRGB((int)(x/32)+xx, (int)(y/32)+yy) != 0xFF000000) {
            x -= velx;
            y -= vely;
            velx *= -1;
            vely *= -1;
          }
        } catch (Exception e) {e.printStackTrace();}
      }
    }
    Random r = new Random();
    int random = r.nextInt(50);
    if (random == 0) {
      velx = (r.nextInt(3)-1);
      vely = (r.nextInt(3)-1);
    } else if (random == 1) if (
    x < handler.game.camera.getx() - 512 ||
    y < handler.game.camera.gety() - 512 ||
    x > handler.game.camera.getx() + handler.game.window.width() + 512 ||
    y > handler.game.camera.gety() + handler.game.window.height() + 512) {
      handler.removeobj(this);
    }
  }

  public void ondamage() {
    health--;
    if (health <= 0) handler.removeobj(this);
  }

  public void render(Graphics g) {
    g.drawImage(img, x, y, null);
  }

  public Rectangle getBounds() {
    return new Rectangle(x + 1, y + 1, 30, 30);
  }
}
