package main.model;

import java.util.List;

/**
 * This interface is an extension of the IMEImageModel and adds enhanced functionalities that can
 * be performed on an image like blurring, sharpening, dithering etc.
 */
interface IMEImageModelPro extends IMEImageModel {

  /**
   * A method to blur the image using filter.
   *
   * @return object of blurred image.
   */
  IMEImageModelPro blur();

  /**
   * A method to sharpen the image using filter.
   *
   * @return object of sharp image.
   */
  IMEImageModelPro sharpen();

  /**
   * A method to apply filter over an image.
   *
   * @param transformMatrix filter matrix.
   * @return object of the resultant image.
   * @throws IllegalArgumentException if arguments aren't right.
   */
  IMEImageModelPro filter(List<List<Double>> transformMatrix) throws IllegalArgumentException;

  /**
   * A method to apply transformation over an image.
   *
   * @param transformMatrix transformation matrix.
   * @return object of the resultant image.
   * @throws IllegalArgumentException if arguments aren't right.
   */
  IMEImageModelPro transform(List<List<Double>> transformMatrix) throws IllegalArgumentException;

  /**
   * A method to convert an image to greyscale using transformation.
   *
   * @return object of the resultant image.
   * @throws IllegalArgumentException if arguments aren't right.
   */
  IMEImageModelPro greyscaleVersion() throws IllegalArgumentException;

  /**
   * A method to convert an image to sepia version using transformation.
   *
   * @return object of the resultant image.
   */
  IMEImageModelPro sepiaVersion();


  /**
   * A method to dither the image.
   *
   * @return object of the resultant image.
   */
  IMEImageModelPro dithering();


}
