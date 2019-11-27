package posidon.pixelium;

import posidon.pixelium.gameobj.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.ClassNotFoundException;
import java.io.EOFException;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class World {

  Player player;
  int playerx, playery;
  int mwidth, mheight;

  public static int[] mapcolors = new int[] {
    0xFFFFFF,
    0x502A1E,
    0x381910,
    0xE1C0A1,
    0xFFFF00
  };

  public void load(BufferedImage floor, BufferedImage blocks, SpriteSheet ss, Handler handler, Game game) {
    loadfloor(floor, ss, handler);
    loadblocks(blocks, ss, handler);
    mwidth = floor.getWidth();
    mheight = floor.getHeight();
    getinfo(game);
    player = new Player(playerx*32, playery*32, ID.player, ss, handler, game);
    handler.addobj(player);
    System.gc();
  }

  private void loadfloor(BufferedImage img, SpriteSheet ss, Handler handler) {
    for(int xx = 0; xx < img.getWidth(); xx++) {
      for(int yy = 0; yy < img.getHeight(); yy++) {
        int px = img.getRGB(xx, yy);
        int index = getblocknum(px);
        if (index != -1) handler.addobj(new Block(xx*32, yy*32, ID.floor, ss, index));
      }
    }
  }

  private void loadblocks(BufferedImage img, SpriteSheet ss, Handler handler) {
    for(int xx = 0; xx < img.getWidth(); xx++) {
      for(int yy = 0; yy < img.getHeight(); yy++) {
        int px = img.getRGB(xx, yy);
        int index = getblocknum(px);
        if (index != -1) handler.addobj(new Block(xx*32, yy*32, ID.block, ss, index));
      }
    }
  }

  public static int getblocknum(int px) {
    for (int i = 0; i < mapcolors.length; i++) {
      if (getblockcolor(i) == px) return i;
    } return -1;
  }

  public static int getblockcolor(int i) { return new Color(mapcolors[i]).getRGB(); }

  void quicksave(float time, Inventory inventory) {
    saveinfo(player.getx()/32, player.gety()/32, time, inventory.selection);
  }

  void saveinfo(int playerx, int playery, float time, int dockselection) {
    try {
      //Saving of object in a file
      FileOutputStream file = new FileOutputStream(System.getProperty("user.dir")+"/pixelium/world/info");
      ObjectOutputStream out = new ObjectOutputStream(file);
      // Method for serialization of object
      out.writeObject(new Info(playerx, playery, time, dockselection));
      out.close();
      file.close();
    } catch(IOException ex) {}
  }

  void getinfo(Game game) {
    try {
      Info obj = null;
      // Reading the object from a file
      FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"/pixelium/world/info");
      ObjectInputStream in = new ObjectInputStream(file);
      // Method for deserialization of object
      obj = (Info)in.readObject();
      in.close();
      file.close();
      System.out.println("player spawned at " + obj.playerx + ", " + obj.playery);
      this.playerx = obj.playerx;
      this.playery = obj.playery;
      game.time.settime(obj.time);
      game.inventory.selection = obj.dockselection;
    } catch(Exception e) {
      saveinfo(mwidth/2, mheight/2, 0, 0);
      getinfo(game);
    }
  }
}
