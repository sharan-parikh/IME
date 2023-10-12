package main.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * This class is an implementation of IMEImageModel and represents a 2D image as collection of
 * RGB(red, blue, green) pixels.
 */
class RGBImageModel implements IMEImageModel {
  protected final List<List<RGBPixel>> pixels;
  protected final int height;
  protected final int width;
  protected final int maxPixel;

  /**
   * Constructs the RGBImageModel object and initializes the necessary variables.
   *
   * @param pixels the 2D arraylist of pixels that represents an image.
   */
  public RGBImageModel(List<List<RGBPixel>> pixels) {
    if (pixels == null) {
      throw new IllegalArgumentException("Pixels can not be null");
    }
    this.pixels = new ArrayList<>(pixels);
    height = this.pixels.size();
    width = this.pixels.size() == 0 ? 0 : this.pixels.get(0).size();
    this.maxPixel = calculateMaxPixel();
  }

  public List<List<RGBPixel>> getPixels() {
    return this.pixels;
  }


  private int computeValue(List<Integer> array) {
    if (array == null) {
      throw new IllegalArgumentException("Can not compute value for a null array");
    }
    if (array.size() == 0) {
      throw new IllegalArgumentException("Can not compute value for a empty array");
    }
    return Collections.max(array);
  }


  private int computeIntensity(List<Integer> array) {
    if (array == null) {
      throw new IllegalArgumentException("Can not compute intensity for a null array");
    }
    if (array.size() == 0) {
      throw new IllegalArgumentException("Can not compute intensity for a empty array");
    }
    int sum = 0;
    for (double i : array) {
      sum += i;
    }

    return sum / 3;
  }


  private int computeLuma(List<Integer> array) {
    if (array == null) {
      throw new IllegalArgumentException("Can not compute luma for a null array");
    }
    if (array.size() == 0) {
      throw new IllegalArgumentException("Can not compute luma for a empty array");
    }
    return (int) Math.round(((0.2126 * array.get(0)) + (0.7152 * array.get(1))
            + (0.0722 * array.get(2))));
  }

  @Override
  public int getHeight() {
    return height;
  }

  @Override
  public int getWidth() {
    return width;
  }

  /**
   * A method to calculate the maximum pixel value.
   *
   * @return the maximum pixel value of an image.
   */
  private int calculateMaxPixel() {
    int res = 0;
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        res = Stream.of(res, this.pixels.get(i).get(j).getRedComp(),
                this.pixels.get(i).get(j).getBlueComp(),
                this.pixels.get(i).get(j).getGreenComp()).max(Integer::compareTo).get();
      }
    }
    return res;
  }

  /**
   * Helper method to return the integer value of a pixel.
   *
   * @param i    row
   * @param j    column
   * @param type red, blue, green
   * @return integer value of a pixel.
   */
  private int visualizeHelperPixel(int i, int j, int type) {
    List<Integer> arr = new ArrayList<>();
    arr.add(this.pixels.get(i).get(j).getRedComp());
    arr.add(this.pixels.get(i).get(j).getGreenComp());
    arr.add(this.pixels.get(i).get(j).getBlueComp());


    if (type == 0) {
      return this.pixels.get(i).get(j).getRedComp();
    } else if (type == 1) {
      return this.pixels.get(i).get(j).getBlueComp();
    } else if (type == 2) {
      return this.pixels.get(i).get(j).getGreenComp();
    } else if (type == 3) {
      return computeValue(arr);
    } else if (type == 4) {
      return computeIntensity(arr);
    } else if (type == 5) {
      return computeLuma(arr);
    }
    return -1;
  }

  /**
   * Helper method to visualize different greyscale versions.
   *
   * @param type type of greyscale.
   * @return object of the resultant greyscale image.
   */
  private IMEImageModel visualizeHelper(int type) {

    List<List<RGBPixel>> res = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        RGBPixel eachPixel = new RGBPixel(visualizeHelperPixel(i, j, type),
                visualizeHelperPixel(i, j, type), visualizeHelperPixel(i, j, type));
        column.add(eachPixel);
      }
      res.add(column);
    }
    return new RGBImageModel(res);
  }

  @Override
  public IMEImageModel visualizeRed() {

    return visualizeHelper(0);
  }


  @Override
  public IMEImageModel visualizeGreen() {

    return visualizeHelper(2);

  }

  @Override
  public IMEImageModel visualizeBlue() {
    return visualizeHelper(1);
  }

  @Override
  public IMEImageModel visualizeValue() {
    return visualizeHelper(3);

  }

  @Override
  public IMEImageModel visualizeIntensity() {
    return visualizeHelper(4);
  }

  @Override
  public IMEImageModel visualizeLuma() {
    return visualizeHelper(5);
  }

  @Override
  public IMEImageModel flipHorizontally() {
    List<List<RGBPixel>> res = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int k = width - 1; k >= 0; k--) { //int k=width-1;k>=0;k--
        column.add(this.pixels.get(i).get(k));
      }
      res.add(column);
    }
    return new RGBImageModel(res);
  }

  @Override
  public IMEImageModel flipVertically() {
    List<List<RGBPixel>> res = new ArrayList<>();

    for (int k = height - 1; k >= 0; k--) {
      res.add(this.pixels.get(k));
    }
    return new RGBImageModel(res);

  }

  /**
   * Helper method to brighten or darken an image.
   *
   * @param amount value.
   * @return object of the resultant bright/dark image.
   */
  private IMEImageModel brightDark(int amount) {
    List<List<RGBPixel>> res = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = this.pixels.get(i).get(j).getRedComp() + amount;
        int g = this.pixels.get(i).get(j).getGreenComp() + amount;
        int b = this.pixels.get(i).get(j).getBlueComp() + amount;
        if (r > 255) {
          r = 255;
        }
        if (r < 0) {
          r = 0;
        }
        if (g > 255) {
          g = 255;
        }
        if (g < 0) {
          g = 0;
        }
        if (b > 255) {
          b = 255;
        }
        if (b < 0) {
          b = 0;
        }

        RGBPixel eachPixel = new RGBPixel(r, b, g);
        column.add(eachPixel);
      }
      res.add(column);
    }
    return new RGBImageModel(res);
  }

  @Override
  public IMEImageModel brighten(int amount) {

    return brightDark(amount);
  }

  @Override
  public IMEImageModel darken(int amount) {
    amount *= -1;
    return brightDark(amount);
  }

  @Override
  public List<IMEImageModel> rgbSplit() {
    List<IMEImageModel> res = new ArrayList<>();

    res.add(visualizeRed());
    res.add(visualizeGreen());
    res.add(visualizeBlue());
    return res;
  }

  @Override
  public int getMaxPixel() {
    return this.maxPixel;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < height; i++) {
      List<RGBPixel> row = this.getPixels().get(i);
      for (int j = 0; j < width; j++) {
        sb.append(row.get(j).toString()).append(" ");
      }
    }
    return sb.toString();
  }

  protected int clampValue(int value) {
    if (value > 255) {
      value = 255;
    } else if (value < 0) {
      value = 0;
    }
    return value;
  }
}
