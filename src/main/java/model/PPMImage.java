package model;


import lombok.Getter;

public class PPMImage implements Image {

  @Getter
  private RGBPixel[][] pixels;

  private final int height;

  private final int width;

  private final int maxValue;

  public PPMImage(int width, int height, int maxValue) {
    pixels = new RGBPixel[height][width];
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        this.pixels[i][j] = new RGBPixel(0, 0, 0);
      }
    }
    this.height = height;
    this.width = width;
    this.maxValue = maxValue;
  }

  private PPMImage(int width, int height, int maxValue, RGBPixel[][] pixels) {
    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.pixels = new RGBPixel[height][width];
    for (int r = 0; r < height; r++) {
      for (int c = 0; c < width; c++) {
        RGBPixel originalPixel = pixels[r][c];
        this.pixels[r][c] = new RGBPixel(originalPixel.getRedComp(), originalPixel.getGreenComp(), originalPixel.getBlueComp());
      }
    }
  }

  public void setPixel(int x, int y, RGBPixel pixel) {
    checkBounds(x, y);
    pixels[x][y] = pixel;
  }

  @Override
  public int getWidth() {
    return width;
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getMaxValue() {
    return maxValue;
  }

  public RGBPixel getPixel(int x, int y) {
    checkBounds(x, y);
    return pixels[x][y];
  }

  @Override
  public Image copy() {
    return new PPMImage(width, height, maxValue, pixels);
  }

  private void checkBounds(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException("Pixel coordinates out of bounds.");
    }
  }
}
