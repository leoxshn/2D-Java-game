package posidon.pixelium.gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import posidon.pixelium.*;
import java.util.Arrays;
import java.lang.Integer;

public class Block extends Gameobject {

  private Integer[] lightblocks = new Integer[] {
    4, 5
  };
  boolean shadowed = true;

  public Block(int x, int y, ID id, SpriteSheet ss, int blocknumber) {
    super(x, y, id, ss);
    img = ss.grabimg(blocknumber + 2, 1, 32, 32);
    mapcolor = World.getblockcolor(blocknumber);
    spatial = true;
    block = true;
    if (!Arrays.asList(lightblocks).contains(blocknumber)) shadow = new bufferedimgloader().loadimage("/posidon/pixelium/textures/blockshadow.png");
    else {
      shadow = new bufferedimgloader().loadimage("/posidon/pixelium/textures/blockglow.png");
      shadowed = false;
    }
  }

  public void tick() {
    /*if (health <= 0) handler.removeobj(this);
    for(int i = 0; i < handler.obj.size(); i++) {
      Gameobject tmpobj = handler.obj.get(i);
      if (tmpobj.getid() == ID.projectile) {
        if (getBounds().intersects(tmpobj.getBounds())) health--;
      }
    }*/
  }

  public void render(Graphics g) {
    g.drawImage(img, x, y, null);
    if (shadowed) {
      if (id == ID.floor) {
        g.setColor(new Color(0, 0, 10, (int)(100*Time.getdarkness())));
        g.fillRect(x, y, 32, 32);
      }
      g.setColor(new Color(0, 0, 100, (int)(120*Time.getdarkness())));
      g.fillRect(x, y, 32, 32);
    }
  }

  public void rendershadow(Graphics g) {
     g.drawImage(shadow, x-32, y-32, null);
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, 32, 32);
  }
}
