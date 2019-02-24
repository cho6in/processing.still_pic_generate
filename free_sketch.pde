/**
 * Reeling In The Years.
 * draw 3 images of Lissajous like figure enumeration.
 * reference : https://generateme.wordpress.com/2016/04/24/drawing-vector-field/
 * @author @deconbatch
 * @version 0.1
 * Processing 3.2.1
 * 2017.02.04
 */

void setup() {

  size(980, 980);
  colorMode(HSB, 360, 100, 100, 100);
  smooth();
  noLoop();

}

void draw() {

  int   baseSeed = floor(random(100));
  float baseHue  = random(360.0);

  for (int imgCnt = 0; imgCnt < 3; ++imgCnt) {

    noiseSeed(baseSeed + imgCnt * 10);

    float plotScale  = random(0.0001, 0.0005);
    int   plotCntMax = floor(10000 * plotScale * 10000);

    baseHue += 120.0; // 120 hue difference each image
    background(baseHue % 360.0, 100.0, 5.0, 100);
    blendMode(SCREEN);
    stroke(0.0, 0.0, 30.0, 100.0);

    for (float xInit = 0.1; xInit <= 0.9; xInit += 0.2) {
      for (float yInit = 0.1; yInit <= 0.9; yInit += 0.2) {

        float xCurr = xInit;
        float yCurr = yInit;
        float xPrev = 0.0;
        float yPrev = 0.0;

        for (int plotCnt = 0; plotCnt < plotCntMax; ++plotCnt) {

          xPrev += plotScale * cos(TWO_PI * noise(xCurr * 0.5, yCurr * 0.5) * 0.3);
          yPrev += plotScale * sin(TWO_PI * noise(yCurr * 0.5, xCurr * 0.5) * 0.8);
          xCurr += plotScale * cos(TWO_PI * xPrev * 8.0);
          yCurr += plotScale * cos(TWO_PI * yPrev * 8.0);

          strokeWeight(sin(PI * map(plotCnt, 0, plotCntMax, 0.0, 1.0)));
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
  fill(_baseHue % 360.0, 100.0, 20.0, 0.0);
  strokeWeight(50.0);
  stroke(0.0, 0.0, 0.0, 100.0);
  rect(0.0, 0.0, width, height);
  strokeWeight(40.0);
  stroke(0.0, 0.0, 100.0, 100.0);
  rect(0.0, 0.0, width, height);
  noStroke();
  noFill();
}
