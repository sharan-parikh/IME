package model;

import lombok.Getter;
import lombok.Setter;


public class RGBImageModel {

  @Getter
  @Setter
  private RGBPixel[][] pixels;

  @Getter
  private final int height;

  @Getter
  private final int width;

  public RGBImageModel(int height, int width) {
    pixels = new RGBPixel[height][width];
    this.height = height;
    this.width = width;
  }

  public void setPixel(int x, int y, RGBPixel pixel) {
    checkBounds(x, y);
    pixels[x][y] = pixel;
  }

  public RGBPixel getPixel(int x, int y) {
    checkBounds(x, y);
    return pixels[x][y];
  }

  private void checkBounds(int x, int y) {
    if (x < 0 || x >= width || y < 0 || y >= height) {
      throw new IndexOutOfBoundsException("Pixel coordinates out of bounds.");
    }
  }
}
