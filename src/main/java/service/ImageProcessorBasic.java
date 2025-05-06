package service;

import constant.Component;
import model.RGBImageModel;

public interface ImageProcessorBasic {

  RGBImageModel verticalFlip(RGBImageModel image);

  RGBImageModel horizontalFlip(RGBImageModel image);

  RGBImageModel visualize(Component component);

  RGBImageModel brighten(RGBImageModel image, int increment);

  RGBImageModel rgbSplit(RGBImageModel image);

  RGBImageModel rgbCombine(RGBImageModel redImage, RGBImageModel greenImage, RGBImageModel blueImage);
}
