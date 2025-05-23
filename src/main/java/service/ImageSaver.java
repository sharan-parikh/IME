package service;

import java.io.IOException;
import java.io.OutputStream;

import model.Image;

public interface ImageSaver {

  void save(Image image, OutputStream out) throws IOException;
}
