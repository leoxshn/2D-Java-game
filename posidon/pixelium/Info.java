package posidon.pixelium;

import java.io.Serializable;

public class Info implements Serializable{

    public int playerx, playery;
    public float time;
    public int dockselection;

    public Info(int playerx, int playery, float time, int dockselection){
        this.playerx = playerx;
        this.playery = playery;
        this.time = time;
        this.dockselection = dockselection;
    }
}
