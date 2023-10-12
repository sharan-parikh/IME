package main.model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.imageio.ImageIO;


/**
 * The concrete class that implements ModelManagerPro
 * and extends the ModelManagerBasicImpl
 * where all the operations of IMEImageModelPro are used.
 *
 */
public class ModelManagerProImpl extends ModelManagerBasicImpl
        implements ModelManagerPro {

  @Override
  protected IMEImageModelPro get(String imgName) throws NoSuchElementException {
    IMEImageModelPro model = createModel(modelStorage.get(imgName).getPixels());
    if (model == null) {
      throw new NoSuchElementException(getImgNotExistsMessage(imgName));
    }
    return model;
  }

  protected List<List<RGBPixel>> readOtherFormat(InputStream in) throws IOException {
    BufferedImage img = ImageIO.read(in);
    List<List<RGBPixel>> res = new ArrayList<>();

    for (int y = 0; y < img.getHeight(); y++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int x = 0; x < img.getWidth(); x++) {
        //Retrieving contents of a pixel
        int pixel = img.getRGB(x, y);
        //Creating a Color object from pixel value
        Color color = new Color(pixel, true);
        //Retrieving the R G B values
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        column.add(new RGBPixel(red, blue, green));

      }
      res.add(column);
    }
    return res;
  }

  @Override
  public void load(String sourceImgName, InputStream in, ImageFormat format)
          throws IOException, IllegalArgumentException {
    IMEImageModelPro model;
    if (format == null) {
      throw new IllegalArgumentException("File format supplied is not supported.");
    }
    if (format.equals(ImageFormat.PPM)) {
      super.load(sourceImgName, in);
    } else {
      model = createModel(readOtherFormat(in));
      addModel(sourceImgName, model);
    }
  }

  @Override
  public void save(String sourceImgName, OutputStream out, ImageFormat format)
          throws IOException, NoSuchElementException {
    switch (format) {
      case PPM:
        super.save(sourceImgName, out);
        break;
      case BMP:
      case JPG:
      case PNG:
        saveOtherFormat(sourceImgName, out, format);
        break;
      default: //not needed
    }
  }

  protected void saveOtherFormat(String sourceImgName, OutputStream out, ImageFormat format)
          throws IOException {
    IMEImageModelPro modelToSave = get(sourceImgName);
    BufferedImage image = ImageUtil.convert(modelToSave.getWidth()
            , modelToSave.getHeight(), modelToSave.getPixels());
    ImageIO.write(image, format.toString(), out);
  }

  @Override
  public void blur(String sourceImgName, String destImgName) throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).blur().getPixels()));
  }

  @Override
  public void dither(String sourceImgName, String destImgName) throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).dithering().getPixels()));
  }

  @Override
  public void sepiaVersion(String sourceImgName, String destImgName) throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).sepiaVersion().getPixels()));
  }

  @Override
  public void sharpen(String sourceImgName, String destImgName) throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).sharpen().getPixels()));
  }

  @Override
  protected RGBImageModelPro createModel(List<List<RGBPixel>> image) {
    return new RGBImageModelPro(image);
  }

  @Override
  public BufferedImage convert(String imgName) {
    IMEImageModel model = get(imgName);
    return ImageUtil.convert(model.getWidth(), model.getHeight(), model.getPixels());
  }

  @Override
  public int[][] histData(String imgName) {
    IMEImageModelPro model = get(imgName);
    Histogram histogram = new Histogram();
    return histogram.freqVals(model);
  }


}
