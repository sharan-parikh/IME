package service;

import java.io.InputStream;

import constant.Component;
import model.RGBImageModel;

public interface ImageProcessorBasic {

  void save(String name, InputStream in);

  RGBImageModel load(String imgName);

  RGBImageModel verticalFlip(RGBImageModel image);

  RGBImageModel horizontalFlip(RGBImageModel image);

  RGBImageModel visualize(Component component);

  RGBImageModel brighten(RGBImageModel image, int increment);

  RGBImageModel rgbSplit(RGBImageModel image);

  RGBImageModel rgbCombine(RGBImageModel redImage, RGBImageModel greenImage, RGBImageModel blueImage);
}
