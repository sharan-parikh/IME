package service;

import java.util.HashMap;
import java.util.Map;

import model.RGBImageModel;

public class ImageModelStorage {

  private Map<String, RGBImageModel> store;

  public ImageModelStorage() {
    this.store = new HashMap<>();
  }

  public RGBImageModel get(String name) {
    if(name == null) {
      throw new IllegalArgumentException("Image name cannot be null");
    }
    return store.get(name);
  }

  public void add(String name, RGBImageModel imgModel) {
    if(name == null) {
      throw new IllegalArgumentException("Image name cannot be null");
    }
    if(imgModel == null) {
      throw new IllegalArgumentException("Image model cannot be null");
    }
    store.put(name, imgModel);
  }
}
