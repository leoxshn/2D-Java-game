package posidon.pixelium;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.AlphaComposite;
import java.util.Properties;
import java.io.FileInputStream;
import java.awt.image.BufferedImage;
import java.lang.Integer;
import java.lang.String;
import posidon.pixelium.gameobj.*;
import posidon.pixelium.input.*;

public class Game extends Canvas implements Runnable {
  private static final long serialVersionUID = 1L;
  private boolean running = false;
  private Thread thread;
  private Handler handler;
  public Camera camera;
  public window window;
  public static SpriteSheet ss;
  public Inventory inventory;
  public static BufferedImage floormap = null;
  public static BufferedImage blockmap = null;
  private BufferedImage tileset = null;
  private BufferedImage floor = null;
  public int ammo = 100;
  public int health = 50;
  public Time time;
  private World world;

  public Game(window window) {
    this.window = window;
    window.startgame(this);
    time = new Time();
    handler = new Handler(this);
    bufferedimgloader loader = new bufferedimgloader();
    tileset = loader.loadimage("/posidon/pixelium/textures/tileset.png");
    floormap = loader.loadmap("floor.png");
    blockmap = loader.loadmap("blocks.png");
    ss = new SpriteSheet(tileset);
    floor = ss.grabimg(1, 1, 32, 32);
    inventory = new Inventory(handler, ss);
    world = new World();
    world.load(floormap, blockmap, ss, handler, this);
    camera = new Camera(world.player.getx()-window.width()/2, world.player.gety()-window.height()/2, window);
    this.addKeyListener(new Keyinput(handler, inventory));
    this.addMouseListener(new Mouseinput(handler, camera, this, ss, inventory));
    this.addMouseMotionListener(new Mouseinput(handler, camera, this, ss, inventory));
    start();
    System.gc();
  }

  private void start() {
    running = true;
    thread = new Thread(this);
    thread.start();
  }

  private void stop() {
    running = false;
    try { thread.join(); }
    catch(InterruptedException e) { e.printStackTrace(); }
  }

  public void run() {
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(running) {
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while(delta >= 1) {
        tick();
        delta--;
      }
      render();
      frames++;
      if (System.currentTimeMillis() - timer > 1000){
        timer += 1000;
        frames = 0;
      }
    }
    stop();
  }

  public void tick() {
    camera.tick(world.player);
    handler.tick();
    inventory.tick();
    time.tick();
    world.quicksave(time.gettime(), inventory);
  }


  public void paint(Graphics g) {
    g.setColor(Color.black);
    g.fillRect(0, 0, window.width(), window.height());
    BufferedImage brand = new bufferedimgloader().loadimage("/posidon/pixelium/textures/brand.png");
    g.drawImage(brand, (window.width()-brand.getWidth())/2, (window.height()-brand.getHeight())/2, null);
  }

  public void render() {
    BufferStrategy bs = this.getBufferStrategy();
    if (bs == null) {
      this.createBufferStrategy(3);
      return;
    }
    Graphics2D g = (Graphics2D) bs.getDrawGraphics();

    //DRAWING/////////////////////////////////
    g.translate(-camera.getx(), -camera.gety());
    for (int xx = (int)camera.getx()/32 - 1; xx < (window.width() + (int)camera.getx())/32 + 1; xx++) {
      for (int yy = (int)camera.gety()/32 - 1; yy < (window.height() + (int)camera.gety())/32 + 1; yy++) {
        g.drawImage(floor, xx*32, yy*32, null);
      }
    }
    g.setColor(new Color(0, 0, 30, (int)(200*time.getdarkness())));
    g.fillRect((int)camera.getx()-32, (int)camera.gety()-32, window.width()+64, window.height()+64);
    handler.render(g, camera, window);
    g.translate(camera.getx(), camera.gety());
    if (handler.isinvopen()) inventory.render(g, window);
    else inventory.renderdock(g, window);
    g.setColor(new Color(0x0088FF));
    g.setStroke(new BasicStroke(2));
    g.fillRect(5, 5, 200, 20);
    g.setColor(new Color(0x0066FF));
    g.fillRect(5, 5, health*4, 20);
    g.setColor(new Color(0xFFFFFF));
    g.drawRect(5, 5, 200, 20);
    g.setColor(new Color(0xFFFFFF));
    g.drawString("Ammo:" + ammo, 5, 40);
    g.drawString("Time:" + time.gettime(), 5, 60);
    //////////////////////////////////////////

    g.dispose();
    bs.show();
  }
}
