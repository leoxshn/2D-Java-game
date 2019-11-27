package posidon.pixelium.gameobj;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.image.BufferedImage;
import posidon.pixelium.*;

public class Player extends Gameobject {

  Game game;
  public int speed = 2;
  SpriteSheet ss;
  Handler handler;

  public Player(int x, int y, ID id, SpriteSheet ss, Handler handler, Game game) {
    super(x, y, id, ss);
    this.ss = ss;
    this.handler = handler;
    this.game = game;
    img = ss.grabimg(1, 2, 32, 32);
  }

  public void tick() {
    x += velx;
    y += vely;
    handler.curx += velx;
    handler.cury += vely;

    for (int i = 0; i < handler.obj.size(); i++) {
      Gameobject tmpobj = handler.obj.get(i);
      if (getBounds().intersects(tmpobj.getBounds())) {
        if (tmpobj.getid() == ID.block) {
          x -= velx;
          y -= vely;
        } else if (tmpobj.getid() == ID.drop) {
          game.ammo += ((Drop)tmpobj).ammo;
          handler.removeobj(tmpobj);
        } else if (tmpobj.getid() == ID.enemy) {
          game.health -= 1;
          velx = x - tmpobj.getx();
          vely = y - tmpobj.gety();
        }
      }
    }

    //MOVEMENT////////////////////////////////
    if (handler.sprinting()) speed = 3;
    else if (handler.crouching()) speed = 1;
    else speed = 2;
    if (handler.isup()) {
      vely = -speed;
      img = ss.grabimg(3, 2, 32, 32);
    } else if (!handler.isdown()) vely = 0;
    if (handler.isdown()) {
      vely = speed;
      img = ss.grabimg(1, 2, 32, 32);
    } else if (!handler.isup()) vely = 0;
    if (handler.isleft()) {
      velx = -speed;
      img = ss.grabimg(4, 2, 32, 32);
    } else if (!handler.isright()) velx = 0;
    if (handler.isright()) {
      velx = speed;
      img = ss.grabimg(2, 2, 32, 32);
    } else if (!handler.isleft()) velx = 0;
    //////////////////////////////////////////
  }

  public void render(Graphics g) {
    g.drawImage(img, x, y, null);
  }

  public Rectangle getBounds() {
    return new Rectangle(x + 4, y+1, 24, 30);
  }
}
