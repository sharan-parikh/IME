package main.controller;

import java.io.IOException;
import java.util.List;

import main.model.GreyscaleType;

/**
 * This interface contains the callback methods which will be consumed by the UI components.
 */
public interface UIFeatures {

  /**
   * Loads the image using the absolute file path supplied.
   *
   * @param filePath the absolute file path.
   * @throws IOException if any problem occurs while reading the file.
   */
  void loadImage(String filePath) throws IOException;

  /**
   * Saves the image using the absolute file path supplied.
   *
   * @param filePath the path where the image will be saved as a file.
   * @throws IOException if any problem occurs while reading the file.
   */
  void saveImage(String filePath) throws IOException;

  /**
   * Brightens the image current image loaded by an amount equal to the constant supplied.
   *
   * @param constant the amount by which the image should be brightened.
   */
  void brightenImage(int constant);

  /**
   * Performs a greyscale operation on the current image loaded depending on thr greyscale type
   * supplied.
   *
   * @param type the greyscale type.
   */
  void visualizeImage(GreyscaleType type);

  /**
   * Flips the current image loaded horizontally.
   */
  void flipImageHorizontally();

  /**
   * Flips the current image loaded vertically.
   */
  void flipImageVertically();

  /**
   * Performs rgb split operation on the current loaded image.
   *
   * @param greyscaleType the greyscale type that the user wants to get displayed on the UI.
   */
  void rgbSplit(GreyscaleType greyscaleType);

  /**
   * Combines the red, green and blue components of the respective images located at the supplied
   * file paths.
   * @param filePaths the file paths where the images to combine exist.
   * @throws IOException if any problem occurs while reading any of the files.
   */
  void rgbCombine(List<String> filePaths) throws IOException;

  /**
   * Sharpens the currently loaded image.
   */
  void sharpenImage();

  /**
   * Blurs the currently loaded image.
   */
  void blurImage();

  /**
   * Dithers the currently loaded image.
   */
  void ditherImage();

  /**
   * Creates a sepia version image of the currently loaded image.
   */
  void sepiaVersion();
}
