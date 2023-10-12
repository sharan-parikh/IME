package main.model;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.NoSuchElementException;

/**
 * An interface that declares methods to manage the operations of
 * the pro version of the model(assignment 5).
 */
public interface ModelManagerPro extends ModelManagerBasic {

  /**
   * A method that loads a particular image.
   *
   * @param destImageName name of the image.
   * @param in            input stream of the image.
   * @param format        the image format.
   * @throws IOException thrown if any error occurs while reading from the input stream.
   */
  void load(String destImageName, InputStream in, ImageFormat format)
          throws IOException, IllegalArgumentException;

  /**
   * A method that blurs the input image using a filter
   * and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   output image name.
   * @throws IllegalArgumentException if input images does not exist.
   */
  void blur(String sourceImgName, String destImgName) throws IllegalArgumentException;

  /**
   * A method that dithers the input image
   * and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   output image name.
   * @throws NoSuchElementException if input images does not exist.
   */
  void dither(String sourceImgName, String destImgName) throws NoSuchElementException;

  /**
   * A method that generates the sepia version of the input image
   * using transformation and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   name of the transformed image.
   * @throws NoSuchElementException if input images does not exist.
   */
  void sepiaVersion(String sourceImgName, String destImgName) throws NoSuchElementException;

  /**
   * A method that generates the sharper version of the input image
   * using transformation and stores it in the storage unit.
   *
   * @param sourceImgName input image name.
   * @param destImgName   name of the transformed image.
   * @throws NoSuchElementException if input images does not exist.
   */
  void sharpen(String sourceImgName, String destImgName) throws NoSuchElementException;

  /**
   * Saves the image to the preferred output stream.
   *
   * @param sourceImgName the name of the image to be saved.
   * @param out           the preferred output stream
   * @param format        the image format
   * @throws IOException            if any I/O error occurs while saving.
   * @throws NoSuchElementException if the image name provided does not exist.
   */
  void save(String sourceImgName, OutputStream out, ImageFormat format)
          throws IOException, NoSuchElementException;

  /**
   * A method to convert model to a buffered image object.
   *
   * @param imgName name of image to be converted to buffered image.
   * @return buffered image object.
   */
  BufferedImage convert(String imgName) throws NoSuchElementException;

  /**
   * A method to generate histogram data on a image from the map.
   *
   * @param imgName name of image in map.
   * @return array of value-frequencies.
   */
  int[][] histData(String imgName);
}
