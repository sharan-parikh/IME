package service;

import java.io.InputStream;
import java.io.OutputStream;

import constant.Component;
import model.Image;

public interface ImageProcessor {

  Image verticalFlip(Image srcImage);

  Image horizontalFlip(Image srcImage);

  Image visualize(Image srcImage, Component component);

  Image brighten(Image srcImage, int increment);

  Image[] rgbSplit(Image srcImage);

  Image rgbCombine(Image redImage, Image greenImage, Image blueImage);
}
