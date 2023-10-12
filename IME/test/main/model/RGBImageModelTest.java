package main.model;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import main.util.FileUtil;

import static org.junit.Assert.assertEquals;

/**
 * This class contains the unit tests for the RGBImageModel class.
 */
public class RGBImageModelTest {

  List<List<RGBPixel>> pixels;

  public RGBImageModelTest() throws FileNotFoundException {
    pixels = ImageUtil.readPPM(new FileInputStream("test-res/images/dog.ppm"));
  }

  @Test
  public void testVisualizeRed() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.visualizeRed();
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/red.txt"),
            res.toString());
  }

  @Test
  public void testVisualizeGreen() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.visualizeGreen();

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/green.txt"),
            res.toString());

  }


  @Test
  public void testVisualizeBlue() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.visualizeBlue();

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/blue.txt"),
            res.toString());

  }

  @Test
  public void testVisualizeValue() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.visualizeValue();


    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/value.txt"),
            res.toString());

  }

  @Test
  public void testVisualizeIntensity() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.visualizeIntensity();

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/intensity.txt"),
            res.toString());

  }

  @Test
  public void testVisualizeLuma() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.visualizeLuma();

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/luma.txt"),
            res.toString());

  }

  @Test
  public void testFlipHorizontal() {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.flipHorizontally();

    assertEquals(pixels.toString(),
            obj.flipHorizontally().flipHorizontally().getPixels().toString());
  }

  @Test
  public void testFlipVertical() {
    IMEImageModel obj = new RGBImageModel(pixels);

    assertEquals(pixels.toString(), obj.flipVertically().flipVertically().getPixels().toString());

  }


  @Test
  public void testBrighten() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.brighten(50);

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/bright.txt"),
            res.toString());
  }

  @Test
  public void testDarken() throws IOException {
    IMEImageModel obj = new RGBImageModel(pixels);
    IMEImageModel res = obj.darken(50);

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/dark.txt"),
            res.toString());

  }

  @Test
  public void testRgbSplit() throws IOException {

    IMEImageModel obj = new RGBImageModel(pixels);
    List<IMEImageModel> res = obj.rgbSplit();

    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/red.txt"),
            res.get(0).toString());
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/green.txt"),
            res.get(1).toString());
    assertEquals(FileUtil.readFromFile("test-res/ExpectedPixels/blue.txt"),
            res.get(2).toString());

  }

}

