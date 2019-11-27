package posidon.pixelium.input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import posidon.pixelium.*;
import posidon.pixelium.gameobj.*;
import javax.swing.SwingUtilities;

public class Mouseinput extends MouseAdapter {
  private Handler handler;
  private Camera camera;
  private Game game;
  private SpriteSheet ss;
  private Inventory inventory;

  public Mouseinput(Handler handler, Camera camera, Game game, SpriteSheet ss, Inventory inventory) {
    this.handler = handler;
    this.camera = camera;
    this.game = game;
    this.ss = ss;
    this.inventory = inventory;
  }

  public void mousePressed(MouseEvent e) {
    if (SwingUtilities.isLeftMouseButton(e)) {
      for (int i = 0; i < handler.obj.size(); i++) {
        Gameobject tmpobj = handler.obj.get(i);
        if(tmpobj.getid() != ID.player
        && handler.curx > tmpobj.getx()
        && handler.cury > tmpobj.gety()
        && handler.curx < tmpobj.getx() + 32
        && handler.cury < tmpobj.gety() + 32) {
          handler.removeobj(tmpobj);
          break;
        }
      }
      /*int mx = (int) (e.getX() + camera.getx());
      int my = (int) (e.getY() + camera.gety());
      handler.addobj(new Projectile((int)camera.getx() + game.window.width()/2, (int)camera.gety() + game.window.height()/2, ID.projectile, ss, handler, mx, my));
      game.ammo--;*/
    } else if (SwingUtilities.isRightMouseButton(e)) {
      Gameobject tmpobj = inventory.dock.get(inventory.selection);
      if (tmpobj.spatial) {
        tmpobj.setx(((int)(handler.curx/32))*32);
        tmpobj.sety(((int)(handler.cury/32))*32);
        if (handler.crouching()) tmpobj.setid(ID.floor);
        handler.addobj(tmpobj);
      }
    }
  }

  public void mouseMoved(MouseEvent e) {
    handler.curx = e.getX() + camera.getx();
    handler.cury = e.getY() + camera.gety();
  }
}
