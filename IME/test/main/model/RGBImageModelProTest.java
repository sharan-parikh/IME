package main.model;


import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.util.FileUtil;

import static org.junit.Assert.assertEquals;

/**
 * A method to test the pro version of the model.
 */
public class RGBImageModelProTest {

  private final List<List<RGBPixel>> pixels;

  public RGBImageModelProTest() throws FileNotFoundException {
    pixels = ImageUtil.readPPM(new FileInputStream("test-res/images/dog.ppm"));
  }

  @Test
  public void testBlur() throws IOException {
    IMEImageModelPro obj = new RGBImageModelPro(pixels);

    IMEImageModelPro res = obj.blur();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/blur.txt"),
            res.toString());

    IMEImageModelPro res1 = res.blur();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/blur2.txt"),
            res1.toString());
  }

  @Test
  public void testSharpen() throws IOException {
    IMEImageModelPro obj = new RGBImageModelPro(pixels);

    IMEImageModelPro res = obj.sharpen();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/sharp.txt"),
            res.toString());
  }

  @Test
  public void testGreyscale() throws IOException {
    IMEImageModelPro obj = new RGBImageModelPro(pixels);

    IMEImageModelPro res = obj.greyscaleVersion();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/greyNew.txt"),
            res.toString());
  }

  @Test
  public void testSepia() throws IOException {
    IMEImageModelPro obj = new RGBImageModelPro(pixels);

    IMEImageModelPro res = obj.sepiaVersion();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/sepia.txt"),
            res.toString());
  }

  @Test
  public void testDithering() throws IOException {
    IMEImageModelPro obj = new RGBImageModelPro(pixels);

    IMEImageModelPro res = obj.dithering();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/dither.txt"),
            res.toString());
  }

}
