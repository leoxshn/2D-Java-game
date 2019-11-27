package posidon.pixelium.gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import posidon.pixelium.*;

public abstract class Gameobject {
  protected int x, y;
  protected float velx, vely;
  protected ID id;
  protected SpriteSheet ss;
  public int mapcolor;
  public boolean spatial;
  public boolean block;
  public BufferedImage img;
  public BufferedImage shadow;

  public Gameobject(int x, int y, ID id, SpriteSheet ss) {
    this.x = x;
    this.y = y;
    this.id = id;
    this.ss = ss;
  }

  public abstract void tick();
  public abstract void render(Graphics g);
  //public abstract void rendershadow(Graphics g);
  public abstract Rectangle getBounds();

  public void outline(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setColor(new Color(200, 200, 200));
    g2d.setStroke(new BasicStroke(4));
    g2d.drawRect(x+2, y+2, 28, 28);
  }

  public ID getid() { return id; }
  public void setid(ID id) { this.id = id; }
  public int getcolor() { return mapcolor; }

  public int getx() { return x; }
  public void setx(int x) { this.x = x; }
  public int gety() { return y; }
  public void sety(int y) { this.y = y; }

  public float getvelx() { return velx; }
  public void setvelx(float velx) { this.velx = velx; }
  public float getvely() { return vely; }
  public void setvely(float vely) { this.vely = vely; }
}
