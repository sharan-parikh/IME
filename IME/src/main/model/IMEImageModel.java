package main.model;

import java.util.List;

/**
 * This interface represents all the basic functionalities that can be performed on a 2D image.
 */
interface IMEImageModel {

  /**
   * Flips the image horizontally.
   *
   * @return the horizontally flipped image main.model.
   */
  IMEImageModel flipHorizontally();

  /**
   * Flips the image vertically.
   *
   * @return the vertically flipped image main.model.
   */
  IMEImageModel flipVertically();

  /**
   * Brightens the image by adding a constant value to the individual components of each pixel.
   *
   * @param constant the value needed for adding so that the image gets brightened.
   * @return the brightened image main.model.
   */
  IMEImageModel brighten(int constant);

  /**
   * Darkens the image by subtracting a constant value from the individual components of each pixel.
   *
   * @param constant the value to be subtracted so that the image gets darkened.
   * @return the darkened image main.model.
   */
  IMEImageModel darken(int constant);

  /**
   * A method to visualize the red channel greyscale image.
   *
   * @return an object of ImageProcessing type that represents the red channel greyscale image.
   */
  IMEImageModel visualizeRed();

  /**
   * A method to visualize the green channel greyscale image.
   *
   * @return an object of ImageProcessing type that represents the green channel greyscale image.
   */
  IMEImageModel visualizeGreen();

  /**
   * A method to visualize the blue channel greyscale image.
   *
   * @return an object of ImageProcessing type that represents the blue channel greyscale image.
   */
  IMEImageModel visualizeBlue();

  /**
   * A method to visualize the greyscale image generated using value.
   *
   * @return an object of ImageProcessing type that represents the value greyscale image.
   */
  IMEImageModel visualizeValue();

  /**
   * A method to visualize the greyscale image generated using intensity.
   *
   * @return an object of ImageProcessing type that represents the intensity greyscale image.
   */
  IMEImageModel visualizeIntensity();

  /**
   * A method to visualize the greyscale image generated using luma.
   *
   * @return an object of ImageProcessing type that represents the luma greyscale image.
   */
  IMEImageModel visualizeLuma();

  /**
   * A method to get the 2D pixel matrix.
   *
   * @return an 2D list of RGBPixel.
   */
  List<List<RGBPixel>> getPixels();


  /**
   * Splits the image model into ito three(red, blue and green) greyscale images.
   *
   * @return a list of image models containing the greyscale image models in the order red, green
   *         and then blue greyscale image.
   */
  List<IMEImageModel> rgbSplit();

  /**
   * Gives a string representation of the image main.model.
   *
   * @return representation of the image model as a string.
   */
  String toString();

  /**
   * To get the maximum pixel value of this image representation.
   *
   * @return returns the maximum pixel value of this image.
   */
  int getMaxPixel();

  /**
   * To get the image height.
   *
   * @return height.
   */
  int getHeight();

  /**
   * To get the image width.
   *
   * @return width.
   */
  int getWidth();

}
