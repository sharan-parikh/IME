package main.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
final class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param in the content of the ppm input.
   */

  public static List<List<RGBPixel>> readPPM(InputStream in) {
    //now set up the scanner to read from the string we just built
    Scanner sc = new Scanner(in);

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new UnsupportedOperationException("Invalid PPM file: plain RAW file should begin with" +
              "P3");
    }

    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    List<List<RGBPixel>> image2D = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<RGBPixel> pixels = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        RGBPixel rgbPixel = new RGBPixel(r, b, g);
        pixels.add(rgbPixel);
      }
      image2D.add(pixels);
    }
    return image2D;
  }

  /**
   * Creates the correct ppm representation of a 2D image.
   *
   * @param pixels the 2D image representation.
   * @return the ppm content as a String.
   */
  public static String createPPMContent(List<List<RGBPixel>> pixels) {

    int height = pixels.size();
    int width = pixels.size() == 0 ? 0 : pixels.get(0).size();

    StringBuilder sb = new StringBuilder();

    sb.append("P3").append(System.lineSeparator()).append(width).append(" ").append(height)
            .append(" ").append(System.lineSeparator()).append(255)
            .append(System.lineSeparator());

    for (int i = 0; i < height; i++) {
      List<RGBPixel> row = pixels.get(i);
      for (int j = 0; j < width; j++) {
        sb.append(row.get(j).getRedComp()).append(System.lineSeparator());
        sb.append(row.get(j).getGreenComp()).append(System.lineSeparator());
        sb.append(row.get(j).getBlueComp()).append(System.lineSeparator());
      }
    }
    return sb.toString();
  }

  public static BufferedImage convert(int width, int height, List<List<RGBPixel>> image) {
    BufferedImage bufferedImage = null;
    //img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);


    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {

        int r = image.get(y).get(x).getRedComp();
        int g = image.get(y).get(x).getGreenComp();
        int b = image.get(y).get(x).getBlueComp();

        Color col = new Color(r, g, b);
        int rgb = col.getRGB();

        bufferedImage.setRGB(x, y, rgb);
      }
    }
    return bufferedImage;
  }
}
