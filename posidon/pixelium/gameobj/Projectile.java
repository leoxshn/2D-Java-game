package posidon.pixelium.gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import posidon.pixelium.*;

public class Projectile extends Gameobject{

  Handler handler;

  public Projectile(int x, int y, ID id, SpriteSheet ss, Handler handler, int mx, int my) {
    super(x, y, id, ss);

    this.handler = handler;

    velx = (mx - x) / 10;
    vely = (my - y) / 10;
    spatial = false;
  }

  public void tick() {
    x += velx;
    y += vely;
    for(int i = 0; i < handler.obj.size(); i++) {
      Gameobject tmpobj = handler.obj.get(i);
      /*if (getBounds().intersects(tmpobj.getBounds())) {
        //if (tmpobj.getid() == ID.block) handler.removeobj(this);
        /*else if (tmpobj.getid() == ID.entity) {
          ((Mob)tmpobj).ondamage();
          handler.removeobj(this);
        }/
      }*/
    }
  }

  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillOval(x, y, 8, 8);
  }

  public Rectangle getBounds() {
    return new Rectangle(x, y, 8, 8);
  }
}
