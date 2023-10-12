package main.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * The concrete class that implements ModelManagerBasic
 * where all the operations of IMEImageModel are used.
 *
 */
public class ModelManagerBasicImpl implements ModelManagerBasic {

  protected Map<String, IMEImageModel> modelStorage;

  /**
   * A constructor to initialize the class map variable.
   */
  public ModelManagerBasicImpl() {
    modelStorage = new HashMap<>();
  }

  protected void addModel(String imgName, IMEImageModel model) {
    modelStorage.put(imgName, model);
  }

  @Override
  public void load(String destImgName, InputStream in) throws IOException {
    addModel(destImgName, createModel(ImageUtil.readPPM(in)));
  }

  @Override
  public void flipHorizontally(String sourceImgName, String destImgName)
          throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).flipHorizontally().getPixels()));
  }

  @Override
  public void flipVertically(String sourceImgName, String destImgName)
          throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).flipVertically().getPixels()));
  }

  @Override
  public void brighten(String sourceImgName, String destImgName, int constant)
          throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).brighten(constant).getPixels()));
  }

  @Override
  public void darken(String sourceImgName, String destImgName, int constant)
          throws NoSuchElementException {
    addModel(destImgName, createModel(get(sourceImgName).darken(constant).getPixels()));
  }

  @Override
  public void visualize(String sourceImgName, String destImgName, GreyscaleType type)
          throws NoSuchElementException {
    IMEImageModel modelToVisualize = get(sourceImgName);
    IMEImageModel visualized = null;
    switch (type) {
      case RED:
        visualized = modelToVisualize.visualizeRed();
        break;
      case GREEN:
        visualized = modelToVisualize.visualizeGreen();
        break;
      case BLUE:
        visualized = modelToVisualize.visualizeBlue();
        break;
      case LUMA:
        visualized = modelToVisualize.visualizeLuma();
        break;
      case VALUE:
        visualized = modelToVisualize.visualizeValue();
        break;
      case INTENSITY:
        visualized = modelToVisualize.visualizeIntensity();
        break;
      default: //not needed
    }
    addModel(destImgName, createModel(visualized.getPixels()));
  }

  @Override
  public void rgbSplit(String sourceImgName, List<String> destImgNames)
          throws IllegalArgumentException {
    IMEImageModel modelToSplit = get(sourceImgName);
    List<IMEImageModel> rgbComponents = modelToSplit.rgbSplit();
    if (destImgNames.size() != rgbComponents.size()) {
      throw new IllegalArgumentException("Expected 3 image names to combine, but received: "
              + destImgNames.size());
    }
    for (int i = 0; i < destImgNames.size(); i++) {
      addModel(destImgNames.get(i), createModel(rgbComponents.get(i).getPixels()));
    }
  }

  @Override
  public void rgbCombine(List<String> sourceImgNames, String destImgName)
          throws NoSuchElementException {
    List<IMEImageModel> modelList = sourceImgNames.stream().map(arg -> get(arg))
            .collect(Collectors.toList());
    List<List<RGBPixel>> res = new ArrayList<>();
    IMEImageModel r = modelList.get(0);
    IMEImageModel g = modelList.get(1);
    IMEImageModel b = modelList.get(2);
    if (!(r.getHeight() == g.getHeight() && g.getHeight() == b.getHeight())) {
      throw new IllegalArgumentException("The images are not of same size");
    }
    if (!(r.getWidth() == g.getWidth() && g.getWidth() == b.getWidth())) {
      throw new IllegalArgumentException("The images are not of same size");
    }

    for (int i = 0; i < r.getHeight(); i++) {
      List<RGBPixel> column = new ArrayList<>();
      for (int j = 0; j < r.getWidth(); j++) {
        RGBPixel eachPixel = new RGBPixel(r.getPixels().get(i).get(j).getRedComp(),
                b.getPixels().get(i).get(j).getBlueComp(),
                g.getPixels().get(i).get(j).getGreenComp());
        column.add(eachPixel);
      }
      res.add(column);
    }

    addModel(destImgName, createModel(res));
  }

  /**
   * This method should be extended whenever a class extends this class, so that the necessary
   * model conversion take place.
   *
   * @param imgName the image name.
   * @return the appropriate image model.
   * @throws NoSuchElementException if there is no image model associated with the supplied image
   *                                name.
   */
  protected IMEImageModel get(String imgName) throws NoSuchElementException {
    IMEImageModel model = modelStorage.get(imgName);
    if (model == null) {
      throw new NoSuchElementException(getImgNotExistsMessage(imgName));
    }
    return model;
  }

  @Override
  public void save(String sourceName, OutputStream out) throws NoSuchElementException, IOException {
    byte[] content = ImageUtil.createPPMContent(get(sourceName)
            .getPixels()).getBytes(StandardCharsets.US_ASCII);
    out.write(content);
  }

  @Override
  public void remove(String imgName) {
    modelStorage.remove(imgName);
  }

  @Override
  public void clear() {
    modelStorage.clear();
  }

  protected String getImgNotExistsMessage(String imageName) {
    return "Image name: " + imageName + " does not exist.";
  }

  protected RGBImageModel createModel(List<List<RGBPixel>> image) {
    return new RGBImageModel(image);
  }

  @Override
  public boolean has(String imgName) {
    return modelStorage.containsKey(imgName);
  }
}
