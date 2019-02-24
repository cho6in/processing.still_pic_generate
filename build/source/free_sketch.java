import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class free_sketch extends PApplet {

/**
 * Reeling In The Years.
 * draw 3 images of Lissajous like figure enumeration.
 * reference : https://generateme.wordpress.com/2016/04/24/drawing-vector-field/
 * @author @deconbatch
 * @version 0.1
 * Processing 3.2.1
 * 2017.02.04
 */

public void setup() {

  
  colorMode(HSB, 360, 100, 100, 100);
  
  noLoop();

}

public void draw() {

  int   baseSeed = floor(random(100));
  float baseHue  = random(360.0f);

  for (int imgCnt = 0; imgCnt < 3; ++imgCnt) {

    noiseSeed(baseSeed + imgCnt * 10);

    float plotScale  = random(0.0001f, 0.0005f);
    int   plotCntMax = floor(10000 * plotScale * 10000);

    baseHue += 120.0f; // 120 hue difference each image
    background(baseHue % 360.0f, 100.0f, 5.0f, 100);
    blendMode(SCREEN);
    stroke(0.0f, 0.0f, 30.0f, 100.0f);

    for (float xInit = 0.1f; xInit <= 0.9f; xInit += 0.2f) {
      for (float yInit = 0.1f; yInit <= 0.9f; yInit += 0.2f) {

        float xCurr = xInit;
        float yCurr = yInit;
        float xPrev = 0.0f;
        float yPrev = 0.0f;

        for (int plotCnt = 0; plotCnt < plotCntMax; ++plotCnt) {

          xPrev += plotScale * cos(TWO_PI * noise(xCurr * 0.5f, yCurr * 0.5f) * 0.3f);
          yPrev += plotScale * sin(TWO_PI * noise(yCurr * 0.5f, xCurr * 0.5f) * 0.8f);
          xCurr += plotScale * cos(TWO_PI * xPrev * 8.0f);
          yCurr += plotScale * cos(TWO_PI * yPrev * 8.0f);

          strokeWeight(sin(PI * map(plotCnt, 0, plotCntMax, 0.0f, 1.0f)));
          point(xCurr * width, yCurr * height);

        }
      }
    }

    casing(baseHue);
    saveFrame("frames/" + String.format("%04d", imgCnt) + ".png");

  }

  exit();

}


/**
 * casing : draw fancy casing
 */
private void casing(float _baseHue) {
  blendMode(BLEND);
  fill(_baseHue % 360.0f, 100.0f, 20.0f, 0.0f);
  strokeWeight(50.0f);
  stroke(0.0f, 0.0f, 0.0f, 100.0f);
  rect(0.0f, 0.0f, width, height);
  strokeWeight(40.0f);
  stroke(0.0f, 0.0f, 100.0f, 100.0f);
  rect(0.0f, 0.0f, width, height);
  noStroke();
  noFill();
}
  public void settings() {  size(980, 980);  smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "free_sketch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
