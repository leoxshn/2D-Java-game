package posidon.pixelium;

class Pixelium {
  public static void main(String args[]) {
    window window = new window(720, 480, "pixelium");

    new Game(window);
  }
}
