package service;

import java.io.IOException;
import java.io.InputStream;

import model.Image;
import model.PPMImage;

public interface ImageLoader {

  Image load(InputStream in) throws IOException;

}
