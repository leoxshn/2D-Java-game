package posidon.pixelium;

import posidon.pixelium.gameobj.Gameobject;

public class Camera {
  private float x = 0, y = 0;
  private window window;

  public Camera(float x, float y, window window) {
    this.x = x;
    this.y = y;
    this.window = window;
  }

  public void tick(Gameobject obj) {
    x += ((obj.getx() - x) -window.width()/2) * 0.1f;
    y += ((obj.gety() - y) -window.height()/2) * 0.1f;
  }

  public float getx() { return x; }
  public void setx(int x) { this.x = x; }
  public float gety() { return y; }
  public void sety(int y) { this.y = y; }
}
