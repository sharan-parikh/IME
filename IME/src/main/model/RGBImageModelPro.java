package main.model;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that is an implementation of IMEImageModelPro and defined the extended operations
 * on an image.
 */
class RGBImageModelPro extends RGBImageModel implements IMEImageModelPro {

  /**
   * A constructor to call super.
   *
   * @param pixels pixel matrix.
   */
  public RGBImageModelPro(List<List<RGBPixel>> pixels) {
    super(pixels);
  }

  @Override
  public IMEImageModelPro blur() {
    List<List<Double>> filter = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Double> column = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        double value = 1.0 / 16;
        if (i % 2 == 0 && j % 2 != 0) {
          value *= 2;
        }
        if (i % 2 != 0 && j % 2 == 0) {
          value *= 2;
        }
        if (i % 2 != 0 && j % 2 != 0) {
          value *= 4;
        }
        column.add(value);
      }
      filter.add(column);
    }
    return filter(filter);
  }

  @Override
  public IMEImageModelPro sharpen() {
    List<List<Double>> filter = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      List<Double> column = new ArrayList<>();
      for (int j = 0; j < 5; j++) {
        double value = 0;
        if (i == 0 || j == 0 || i == 4 || j == 4) {
          value = -1.0 / 8;
        }
        if (i > 0 && j > 0 && i != 4 && j != 4) {
          value = 1.0 / 4;
        }
        if (i == 2 && j == 2) {
          value = 1;
        }
        column.add(value);
      }
      filter.add(column);
    }
    return filter(filter);
  }

  @Override
  public IMEImageModelPro transform(List<List<Double>> transformMatrix)
          throws IllegalArgumentException {
    List<List<RGBPixel>> res = new ArrayList<>();

    for (int i = 0; i < height; i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int j = 0; j < width; j++) {

        double r = 0;
        double g = 0;
        double b = 0;

        r += transformMatrix.get(0).get(0) * pixels.get(i).get(j).getRedComp();
        r += transformMatrix.get(0).get(1) * pixels.get(i).get(j).getGreenComp();
        r += transformMatrix.get(0).get(2) * pixels.get(i).get(j).getBlueComp();

        g += transformMatrix.get(1).get(0) * pixels.get(i).get(j).getRedComp();
        g += transformMatrix.get(1).get(1) * pixels.get(i).get(j).getGreenComp();
        g += transformMatrix.get(1).get(2) * pixels.get(i).get(j).getBlueComp();

        b += transformMatrix.get(2).get(0) * pixels.get(i).get(j).getRedComp();
        b += transformMatrix.get(2).get(1) * pixels.get(i).get(j).getGreenComp();
        b += transformMatrix.get(2).get(2) * pixels.get(i).get(j).getBlueComp();

        int redComp = clampValue((int) Math.round(r));
        int blueComp = clampValue((int) Math.round(b));
        int greenComp = clampValue((int) Math.round(g));

        column.add(new RGBPixel(redComp, blueComp, greenComp));
      }
      res.add(column);
    }
    return new RGBImageModelPro(res);
  }


  @Override
  public IMEImageModelPro filter(List<List<Double>> transformMatrix)
          throws IllegalArgumentException {
    List<List<RGBPixel>> res = new ArrayList<>();
    int dimFilter = transformMatrix.size();

    //System.out.println(dimFilter);
    for (int i = 0; i < height; i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int j = 0; j < width; j++) {

        double r = 0;
        double g = 0;
        double b = 0;
        for (int x = 0; x < dimFilter; x++) {
          for (int y = 0; y < dimFilter; y++) {
            int imageX = i - (dimFilter / 2) + x;
            int imageY = j - (dimFilter / 2) + y;
            if (imageX < 0 || imageY < 0 || imageX > height - 1 || imageY > width - 1) {
              continue;
            }
            //System.out.println(i+" "+j+" "+x+" "+y+" "+imageX + " "+ imageY);
            r += pixels.get(imageX).get(imageY).getRedComp() * transformMatrix.get(x).get(y);
            g += pixels.get(imageX).get(imageY).getGreenComp() * transformMatrix.get(x).get(y);
            b += pixels.get(imageX).get(imageY).getBlueComp() * transformMatrix.get(x).get(y);
          }
        }
        int redComp = clampValue((int) Math.round(r));
        int blueComp = clampValue((int) Math.round(b));
        int greenComp = clampValue((int) Math.round(g));

        column.add(new RGBPixel(redComp, blueComp, greenComp));
      }
      res.add(column);
    }
    return new RGBImageModelPro(res);
  }

  @Override
  public IMEImageModelPro greyscaleVersion() throws IllegalArgumentException {
    List<List<Double>> transValue = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Double> column = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          column.add(0.2126);
        }
        if (j == 1) {
          column.add(0.7152);
        }
        if (j == 2) {
          column.add(0.0722);
        }
      }
      transValue.add(column);
    }
    return transform(transValue);
  }

  @Override
  public IMEImageModelPro visualizeRed() throws IllegalArgumentException {
    List<List<Double>> transValue = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Double> column = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          column.add(1.0);
        }
        if (j == 1) {
          column.add(0.0);
        }
        if (j == 2) {
          column.add(0.0);
        }
      }
      transValue.add(column);
    }
    return transform(transValue);
  }

  @Override
  public IMEImageModelPro visualizeGreen() {
    List<List<Double>> transValue = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Double> column = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          column.add(0.0);
        }
        if (j == 1) {
          column.add(1.0);
        }
        if (j == 2) {
          column.add(0.0);
        }
      }
      transValue.add(column);
    }
    return transform(transValue);
  }

  @Override
  public IMEImageModelPro visualizeBlue() {
    List<List<Double>> transValue = new ArrayList<>();
    for (int i = 0; i < 3; i++) {
      List<Double> column = new ArrayList<>();
      for (int j = 0; j < 3; j++) {
        if (j == 0) {
          column.add(0.0);
        }
        if (j == 1) {
          column.add(0.0);
        }
        if (j == 2) {
          column.add(1.0);
        }
      }
      transValue.add(column);
    }
    return transform(transValue);
  }

  @Override
  public IMEImageModelPro sepiaVersion() {
    List<List<Double>> transValue = new ArrayList<>();
    List<Double> column = new ArrayList<>();
    column.add(0.393);
    column.add(0.769);
    column.add(0.189);
    transValue.add(column);
    column = new ArrayList<>();

    column.add(0.349);
    column.add(0.686);
    column.add(0.168);
    transValue.add(column);
    column = new ArrayList<>();

    column.add(0.272);
    column.add(0.534);
    column.add(0.131);
    transValue.add(column);

    return transform(transValue);
  }

  @Override
  public IMEImageModelPro dithering() {
    IMEImageModelPro greyscale = greyscaleVersion();
    List<List<RGBPixel>> res = new ArrayList<>(greyscale.getPixels());

    List<List<RGBPixel>> resNew = new ArrayList<>();

    for (int i = 1; i < greyscale.getHeight() - 1; i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int j = 1; j < greyscale.getWidth() - 1; j++) {
        int red = res.get(i).get(j).getRedComp();
        int redNew = red <= maxPixel / 2 ? 0 : maxPixel;
        double error = red - redNew;

        column.add(new RGBPixel(redNew, redNew, redNew));

        res.get(i).get(j + 1).setRedComp(res.get(i).get(j + 1).getRedComp()
                + (int) Math.round(7.0 / 16 * error));
        res.get(i).get(j + 1).setBlueComp(res.get(i).get(j + 1).getBlueComp()
                + (int) Math.round(7.0 / 16 * error));
        res.get(i).get(j + 1).setGreenComp(res.get(i).get(j + 1).getGreenComp()
                + (int) Math.round(7.0 / 16 * error));

        res.get(i + 1).get(j - 1).setRedComp(res.get(i + 1).get(j - 1).getRedComp()
                + (int) Math.round(3.0 / 16 * error));
        res.get(i + 1).get(j - 1).setBlueComp(res.get(i + 1).get(j - 1).getBlueComp()
                + (int) Math.round(3.0 / 16 * error));
        res.get(i + 1).get(j - 1).setGreenComp(res.get(i + 1).get(j - 1).getGreenComp()
                + (int) Math.round(3.0 / 16 * error));

        res.get(i + 1).get(j).setRedComp(res.get(i + 1).get(j).getRedComp()
                + (int) Math.round(5.0 / 16 * error));
        res.get(i + 1).get(j).setBlueComp(res.get(i + 1).get(j).getBlueComp()
                + (int) Math.round(5.0 / 16 * error));
        res.get(i + 1).get(j).setGreenComp(res.get(i + 1).get(j).getGreenComp()
                + (int) Math.round(5.0 / 16 * error));

        res.get(i + 1).get(j + 1).setRedComp(res.get(i + 1).get(j + 1).getRedComp()
                + (int) Math.round(1.0 / 16 * error));
        res.get(i + 1).get(j + 1).setBlueComp(res.get(i + 1).get(j + 1).getBlueComp()
                + (int) Math.round(1.0 / 16 * error));
        res.get(i + 1).get(j + 1).setGreenComp(res.get(i + 1).get(j + 1).getGreenComp()
                + (int) Math.round(1.0 / 16 * error));


      }
      resNew.add(column);
    }

    return new RGBImageModelPro(resNew);
  }


}
