package posidon.pixelium.input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import posidon.pixelium.*;
import posidon.pixelium.gameobj.*;

public class Keyinput extends KeyAdapter {

  Handler handler;
  Inventory inventory;

  public Keyinput(Handler handler, Inventory inventory) {
    this.handler = handler;
    this.inventory = inventory;
  }

  public void keyPressed(KeyEvent e) {
    int key = e.getKeyCode();
    for (int i = 0; i < handler.obj.size(); i++) {
      Gameobject tmpobj = handler.obj.get(i);
      if (tmpobj.getid() == ID.player && !handler.isinvopen()) {
        if (key == KeyEvent.VK_W) handler.setup(true);
        if (key == KeyEvent.VK_A) handler.setleft(true);
        if (key == KeyEvent.VK_S) handler.setdown(true);
        if (key == KeyEvent.VK_D) handler.setright(true);
        if (e.isControlDown()) handler.setsprint(true);
        if (key == KeyEvent.VK_SHIFT) handler.setcrouch(true);
        switch (key) {
          case KeyEvent.VK_1:
          inventory.selection = 0;
          break;

          case KeyEvent.VK_2:
          inventory.selection = 1;
          break;

          case KeyEvent.VK_3:
          inventory.selection = 2;
          break;

          case KeyEvent.VK_4:
          inventory.selection = 3;
          break;

          case KeyEvent.VK_5:
          inventory.selection = 4;
          break;

          case KeyEvent.VK_6:
          inventory.selection = 5;
          break;

          case KeyEvent.VK_7:
          inventory.selection = 6;
          break;

          case KeyEvent.VK_8:
          inventory.selection = 7;
          break;

          case KeyEvent.VK_9:
          inventory.selection = 8;
          break;
        }
      }
    }
  }

  public void keyReleased(KeyEvent e) {
    int key = e.getKeyCode();
    for (int i = 0; i < handler.obj.size(); i++) {
      Gameobject tmpobj = handler.obj.get(i);
      if (tmpobj.getid() == ID.player) {
        if (key == KeyEvent.VK_W) handler.setup(false);
        if (key == KeyEvent.VK_A) handler.setleft(false);
        if (key == KeyEvent.VK_S) handler.setdown(false);
        if (key == KeyEvent.VK_D) handler.setright(false);
        if (!e.isControlDown()) handler.setsprint(false);
        if (key == KeyEvent.VK_SHIFT) handler.setcrouch(false);
        if (key == KeyEvent.VK_E) {
          handler.setinvstate(!handler.isinvopen());
          handler.setup(false);
          handler.setleft(false);
          handler.setdown(false);
          handler.setright(false);
          handler.setsprint(false);
          handler.setcrouch(false);
        }
      }
    }
  }
}
