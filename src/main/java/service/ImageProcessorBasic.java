package service;

import java.io.InputStream;
import java.io.OutputStream;

import constant.Component;
import model.RGBImageModel;

public interface ImageProcessorBasic {

  void save(String name, OutputStream out);

  RGBImageModel load(String imgName, InputStream out);

  RGBImageModel verticalFlip(RGBImageModel image);

  RGBImageModel horizontalFlip(RGBImageModel image);

  RGBImageModel visualize(Component component);

  RGBImageModel brighten(RGBImageModel image, int increment);

  RGBImageModel rgbSplit(RGBImageModel image);

  RGBImageModel rgbCombine(RGBImageModel redImage, RGBImageModel greenImage, RGBImageModel blueImage);
}
