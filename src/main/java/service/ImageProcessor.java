package service;

import java.io.InputStream;
import java.io.OutputStream;

import constant.Component;
import model.Image;

public interface ImageProcessor {

  Image verticalFlip(Image image);

  Image horizontalFlip(Image image);

  Image visualize(Component component);

  Image brighten(Image image, int increment);

  Image rgbSplit(Image image);

  Image rgbCombine(Image redImage, Image greenImage, Image blueImage);
}
