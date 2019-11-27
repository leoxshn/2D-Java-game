package posidon.pixelium;

import java.lang.Math;

public class Time {
  //timetange has to be 1/2 of the desired amount of secoonds per day
  private int timerange = 120;
  private float time = timerange;
  static float darkfloat = 0;

  public static float getdarkness() { return darkfloat; }
  public float gettime() { return time; }
  public void settime(float time) { this.time = time; }

  public void tick() {
    if (time - timerange < timerange) time = (time*100+1)/100;
    else time = 0;
    darkfloat = Math.abs(time - timerange) / timerange;
  }
}
