package model;

import lombok.Getter;

@Getter
public class RGBPixel {

  private int redComp;

  private int greenComp;

  private int blueComp;

  // Constants for clamping pixel values
  private static final int MIN_PIXEL_VALUE = 0;
  private static final int MAX_PIXEL_VALUE = 255;

  /**
   * Constructs a RGBPixel object with the given red, green, and blue components.
   * All component values are clamped to be within the range [0, 255].
   *
   * @param redComp   The red component value.
   * @param greenComp The green component value.
   * @param blueComp The blue component value.
   */
  public RGBPixel(int redComp, int greenComp, int blueComp) {
    this.redComp = clamp(redComp);
    this.blueComp = clamp(blueComp);
    this.greenComp = clamp(greenComp);
  }

  /**
   * Calculates the 'Intensity' of the pixel, which is the average of its
   * red, green, and blue components.
   *
   * @return The intensity component.
   */
  public int getIntensity() {
    return (redComp + greenComp + blueComp) / 3;
  }

  /**
   * Calculates the 'Luma' of the pixel, which is a weighted sum of its
   * red, green, and blue components, commonly used for brightness perception.
   * Formula: 0.2126 * R + 0.7152 * G + 0.0722 * B
   *
   * @return The luma component.
   */
  public int getLuma() {
    return (int) Math.round(0.2126 * redComp + 0.7152 * greenComp + 0.0722 * greenComp);
  }

  /**
   * Clamps an integer value to be within the valid pixel range [0, 255].
   *
   * @param value The integer value to clamp.
   * @return The clamped value.
   */
  private int clamp(int value) {
    return Math.max(MIN_PIXEL_VALUE, Math.min(MAX_PIXEL_VALUE, value));
  }
}
