package main.model;

import java.util.Objects;

/**
 * This class represents an RGB pixel in a 2D image.
 */
class RGBPixel {

  private int redComp;

  private int blueComp;

  private int greenComp;

  /**
   * Constructs the RGBPixel object.
   *
   * @param redComp   the red component.
   * @param blueComp  the blue component.
   * @param greenComp the green component.
   */
  public RGBPixel(int redComp, int blueComp, int greenComp) {
    this.redComp = redComp;
    this.blueComp = blueComp;
    this.greenComp = greenComp;
  }

  /**
   * Gets the red component of the pixel.
   *
   * @return the red component value.
   */
  public int getRedComp() {
    return redComp;
  }

  /**
   * Gets the blue component of the pixel.
   *
   * @return the blue component value.
   */
  public int getBlueComp() {
    return blueComp;
  }

  /**
   * Gets the green component of the pixel.
   *
   * @return the green component value.
   */
  public int getGreenComp() {
    return greenComp;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RGBPixel rgbPixel = (RGBPixel) o;
    return redComp == rgbPixel.redComp && blueComp == rgbPixel.blueComp &&
            greenComp == rgbPixel.greenComp;
  }

  @Override
  public int hashCode() {
    return Objects.hash(redComp, blueComp, greenComp);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    return sb.append(redComp).append(",").append(greenComp).append(",").append(blueComp).toString();
  }

  public void setRedComp(int redComp) {
    this.redComp = redComp;
  }

  public void setBlueComp(int blueComp) {
    this.blueComp = blueComp;
  }

  public void setGreenComp(int greenComp) {
    this.greenComp = greenComp;
  }
}
