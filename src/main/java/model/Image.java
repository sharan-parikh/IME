package model;

public interface Image {

  /**
   * Returns the width of the image in pixels.
   *
   * @return The width of the image.
   */
  int getWidth();

  /**
   * Returns the height of the image in pixels.
   *
   * @return The height of the image.
   */
  int getHeight();

  /**
   * Returns the maximum color value (e.g., 255 for 8-bit color).
   *
   * @return The maximum color value.
   */
  int getMaxValue();

  /**
   * Returns the Pixel object at the specified row and column.
   *
   * @param row The row index (0-based).
   * @param col The column index (0-based).
   * @return The Pixel object at the given coordinates.
   * @throws IllegalArgumentException If row or column are out of bounds.
   */
  RGBPixel getPixel(int row, int col) throws IllegalArgumentException;

  /**
   * Sets the Pixel object at the specified row and column.
   *
   * @param row   The row index (0-based).
   * @param col   The column index (0-based).
   * @param pixel The RGBPixel object to set.
   * @throws IllegalArgumentException If row or column are out of bounds, or pixel is null.
   */
  void setPixel(int row, int col, RGBPixel pixel) throws IllegalArgumentException;

  /**
   * Creates and returns a deep copy of this image.
   * This is useful for non-destructive operations where the original image
   * should remain unchanged.
   *
   * @return A new Image object that is a copy of the current image.
   */
  Image copy();
}
