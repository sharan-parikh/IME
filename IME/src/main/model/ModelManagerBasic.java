package main.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * An interface that declares methods to manage the operations of
 * a basic model(Assignment 4).
 */
public interface ModelManagerBasic {

  /**
   * A method that loads a particular image.
   *
   * @param destImgName name of the image.
   * @param in          input stream of the image.
   * @throws IOException thrown if any error occurs while reading from the input stream.
   */
  void load(String destImgName, InputStream in) throws IOException;

  /**
   * It calls the method to flip the image horizontally
   * and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   flipped output image name.
   * @throws NoSuchElementException if source does not exit.
   */
  void flipHorizontally(String sourceImgName, String destImgName)
          throws NoSuchElementException;

  /**
   * It calls the method to flip the image vertically
   * and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   flipped output image name.
   * @throws NoSuchElementException if source does not exit.
   */
  void flipVertically(String sourceImgName, String destImgName)
          throws NoSuchElementException;

  /**
   * It calls the method to brighten the image
   * and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   bright output image name.
   * @param constant      amount to brighten it by.
   * @throws NoSuchElementException if source does not exit.
   */
  void brighten(String sourceImgName, String destImgName, int constant)
          throws NoSuchElementException;

  /**
   * It calls the method to darken the image
   * and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   bright output image name.
   * @param constant      amount to darken it by.
   * @throws NoSuchElementException if image with does not exit.
   */
  void darken(String sourceImgName, String destImgName, int constant)
          throws NoSuchElementException;

  /**
   * A method that calls and stores the greyscale visualization of images.
   *
   * @param sourceImgName input image name.
   * @param destImgName   output image name after visualization.
   * @param type          type of visualization.
   * @throws NoSuchElementException if image with does not exit.
   */
  void visualize(String sourceImgName, String destImgName, GreyscaleType type)
          throws NoSuchElementException;

  /**
   * A method that calls and stores the split of an image.
   *
   * @param sourceImagName input image that is to be split.
   * @param destImgNames   list of output split images.
   * @throws NoSuchElementException if image with does not exit.
   */
  void rgbSplit(String sourceImagName, List<String> destImgNames)
          throws IllegalArgumentException;

  /**
   * A method that calls and stores the combined images.
   *
   * @param sourceImgNames list of images that are to be combined.
   * @param destImgName    output image name.
   * @throws NoSuchElementException if any of the source images don't exist.
   */
  void rgbCombine(List<String> sourceImgNames, String destImgName)
          throws IllegalArgumentException;

  /**
   * Converts the underlying model to the preferred output stream.
   *
   * @param sourceImgName the name of the image to be saved.
   * @param out           the output stream where the image will be saved.
   * @throws IOException this exception will be thrown in case any error occurs while writing to
   *                     output stream.
   */
  void save(String sourceImgName, OutputStream out)
          throws IOException;

  /**
   * Clears all the images stored in the application session.
   */
  void clear();

  /**
   * A method to check if an image with the supplied alias exists.
   *
   * @param imgName name of the image to get.
   * @return an object of the search image.
   */
  boolean has(String imgName);

  /**
   * A method to remove the image from the map.
   *
   * @param imgName name of image to be removed.
   */
  void remove(String imgName);

}
