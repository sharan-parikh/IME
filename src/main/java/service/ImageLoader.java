package service;

import java.io.InputStream;

import model.RGBImageModel;

public interface ImageLoader {

  RGBImageModel load(InputStream in);

}
