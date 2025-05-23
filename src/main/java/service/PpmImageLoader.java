package service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.Image;
import model.PPMImage;
import model.RGBPixel;

public class PpmImageLoader implements ImageLoader {
  @Override
  public Image load(InputStream in) {
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
    return new PPMImage(width, height, maxValue);
  }
}
