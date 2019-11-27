package posidon.pixelium;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.BasicStroke;
import java.util.LinkedList;
import java.awt.MouseInfo;
import posidon.pixelium.gameobj.*;
import posidon.pixelium.Camera;
import posidon.pixelium.window;

public class Handler {
  public LinkedList<Gameobject> obj = new LinkedList<Gameobject>();

  private boolean up = false, down = false, right = false, left = false;
  private boolean sprint = false, crouch = false;
  private boolean openinventory;
  public float curx, cury;
  public Game game;

  public Handler(Game game) {
    this.game = game;
  }

  public void tick() {
    for (int i = 0; i < obj.size(); i++) {
      try { obj.get(i).tick(); }
      catch(Exception e) {e.printStackTrace();}
    }
  }

  public void render(Graphics g, Camera camera, window window) {
    LinkedList<Gameobject> floors = new LinkedList<Gameobject>();
    LinkedList<Gameobject> entities = new LinkedList<Gameobject>();
    LinkedList<Gameobject> blocks = new LinkedList<Gameobject>();
    for (int i = 0; i < obj.size(); i++) {
      try {
        Gameobject tmpobj = obj.get(i);
        if (tmpobj.getx() > camera.getx() - 32
        && tmpobj.gety() > camera.gety() - 32
        && tmpobj.getx() < camera.getx() + window.width()
        && tmpobj.gety() < camera.gety() + window.height()) {
          if (tmpobj.getid() == ID.floor) floors.add(tmpobj);
          else if (tmpobj.getid() == ID.entity || tmpobj.getid() == ID.player) entities.add(tmpobj);
          else if (tmpobj.getid() == ID.block) blocks.add(tmpobj);
        }
      } catch(Exception e) {}
    }
    for (int i = 0; i < floors.size(); i++) { floors.get(i).render(g); }
    for (int i = 0; i < blocks.size(); i++) { ((Block)blocks.get(i)).rendershadow(g); }
    for (int i = 0; i < entities.size(); i++) { entities.get(i).render(g); }
    for (int i = 0; i < blocks.size(); i++) { blocks.get(i).render(g); }
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color(255, 255, 255, 50));
    g2d.setStroke(new BasicStroke(3));
    g2d.drawRect(((int)(curx/32))*32, ((int)(cury/32))*32, 32, 32);
  }

  public void addobj(Gameobject tmpobj) {
    if(tmpobj.block) {
      boolean placeisocupiedsorry = false;
      for (int i = 0; i < obj.size(); i++) {
        if ((int)(obj.get(i).getx()/32) == (int)(tmpobj.getx()/32) && (int)(obj.get(i).gety()/32) == (int)(tmpobj.gety()/32)) placeisocupiedsorry = true;
      }
      if(!placeisocupiedsorry) {
        obj.add(tmpobj);
        try {
          if (tmpobj.getid() == ID.block) {
            game.blockmap.setRGB(tmpobj.getx()/32, tmpobj.gety()/32, tmpobj.getcolor());
            new bufferedimgloader().saveimage(game.blockmap, "world/blocks.png");
          } else if (tmpobj.getid() == ID.floor) {
            game.floormap.setRGB(tmpobj.getx()/32, tmpobj.gety()/32, tmpobj.getcolor());
            new bufferedimgloader().saveimage(game.floormap, "world/floor.png");
          }
        } catch (Exception e) {}
      }
    } else obj.add(tmpobj);
  }

  public void removeobj(Gameobject tmpobj) {
    if (tmpobj.getid() == ID.block) {
      game.blockmap.setRGB(tmpobj.getx()/32, tmpobj.gety()/32, new Color(0x000000).getRGB());
      new bufferedimgloader().saveimage(game.blockmap, "world/blocks.png");
    } else if (tmpobj.getid() == ID.floor) {
      game.floormap.setRGB(tmpobj.getx()/32, tmpobj.gety()/32, new Color(0x000000).getRGB());
      new bufferedimgloader().saveimage(game.floormap, "world/floor.png");
    }
    obj.remove(tmpobj);
  }

	public boolean isup() { return up; }
  public void setup(boolean up) { this.up = up; }
  public boolean isdown() { return down; }
  public void setdown(boolean down) { this.down = down; }
  public boolean isright() { return right; }
  public void setright(boolean right) { this.right = right; }
  public boolean isleft() { return left; }
  public void setleft(boolean left) { this.left = left; }

  public boolean sprinting() { return sprint; }
  public void setsprint(boolean sprint) { this.sprint = sprint; }
  public boolean crouching() { return crouch; }
  public void setcrouch(boolean crouch) { this.crouch = crouch; }

  public boolean isinvopen() { return openinventory; }
  public void setinvstate(boolean openinventory) { this.openinventory = openinventory; }
}
