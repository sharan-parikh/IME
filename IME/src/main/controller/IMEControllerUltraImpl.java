package main.controller;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import main.common.ExceptionMessages;
import main.model.GreyscaleType;
import main.model.ImageFormat;
import main.model.ModelManagerPro;
import main.ui.ActionCommands;
import main.ui.actionlisteners.ButtonListener;
import main.ui.runnables.LoadActionRunnable;
import main.ui.runnables.SaveButtonRunnable;
import main.view.GUIView;

/**
 * This controller is specifically catering the new view which contains the UI components.
 */
public class IMEControllerUltraImpl implements UIFeatures, IMEControllerBasic {

  private ModelManagerPro modelManager;

  private final GUIView view;

  private final String defaultImgName = "image";

  private List<String> tempImgNames; // used for split and combine operations.

  /**
   * Constructs the IMEControllerUltraImpl object and initializes the necessary variables.
   *
   * @param modelManager the model manager instance.
   * @param view         the view instance.
   */
  public IMEControllerUltraImpl(ModelManagerPro modelManager, GUIView view) {
    this.modelManager = modelManager;
    this.view = view;
    tempImgNames = Arrays.asList(new String[]{"img1", "img2", "img3"});
    configureButtonListener();
  }

  @Override
  public void control() {
    view.addFeatures(this);
  }

  @Override
  public void loadImage(String filePath) throws IOException {
    String fileExtension = getFileExtension(filePath);
    try {
      modelManager.load(defaultImgName,
              new FileInputStream(filePath),
              ImageFormat.valueOf(fileExtension.toUpperCase()));
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (IllegalArgumentException ex) {
      view.showErrorDialog("Image format: " + fileExtension + " is not suppoerted");
    } catch (IOException ex) {
      view.showErrorDialog(ExceptionMessages.IMGLOADUNSUCCESSFULL);
    }
  }

  @Override
  public void saveImage(String filePath) throws IOException {
    String fileExtension = getFileExtension(filePath);
    try {
      modelManager.save(defaultImgName,
              new FileOutputStream(filePath),
              ImageFormat.valueOf(fileExtension.toUpperCase()));
      view.showOperationSuccessDialog("Image saved successfully.", "Save Successful");
    } catch (IllegalArgumentException ex) {
      view.showErrorDialog("Image format: " + fileExtension + " is not suppoerted");
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void brightenImage(int constant) {
    try {
      modelManager.brighten(defaultImgName, defaultImgName, constant);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
    modelManager.brighten(defaultImgName, defaultImgName, constant);
    view.repaintImage(modelManager.convert(defaultImgName));
  }

  @Override
  public void visualizeImage(GreyscaleType type) {
    try {
      modelManager.visualize(defaultImgName, defaultImgName, type);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void flipImageHorizontally() {
    try {
      modelManager.flipHorizontally(defaultImgName, defaultImgName);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void flipImageVertically() {
    try {
      modelManager.flipVertically(defaultImgName, defaultImgName);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void rgbSplit(GreyscaleType greyscaleType) {
    // 0 means red component, 1 means green component, 2 means blue component.

    int greyscaleType2 = 0;
    if (greyscaleType.equals(GreyscaleType.RED)) {
      greyscaleType2 = 0;
    } else if (greyscaleType.equals(GreyscaleType.GREEN)) {
      greyscaleType2 = 1;
    } else if (greyscaleType.equals(GreyscaleType.BLUE)) {
      greyscaleType2 = 2;
    }

    String imgNameToKeep = tempImgNames.get(greyscaleType2);
    for (String name : tempImgNames) {
      if (!name.equals(imgNameToKeep)) {
        modelManager.remove(name); // removing the unwanted components.
      }
    }

    try {
      view.repaintImage(modelManager.convert(imgNameToKeep));
      view.repaintHistogram(modelManager.histData(imgNameToKeep));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void rgbCombine(List<String> filePaths) throws IOException {
    tempImgNames = Arrays.asList(new String[]{"img1", "img2", "img3"});
    for (int i = 0; i < tempImgNames.size(); i++) {
      modelManager.load(tempImgNames.get(i),
              new FileInputStream(filePaths.get(i)),
              ImageFormat.valueOf(getFileExtension(filePaths.get(i)).toUpperCase()));
    }
    try {
      modelManager.rgbCombine(tempImgNames, defaultImgName);
      tempImgNames.forEach(name -> modelManager.remove(name)); // remove the temporary image names
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void sharpenImage() {
    try {
      modelManager.sharpen(defaultImgName, defaultImgName);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void blurImage() {
    try {
      modelManager.blur(defaultImgName, defaultImgName);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void ditherImage() {
    try {
      modelManager.dither(defaultImgName, defaultImgName);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  @Override
  public void sepiaVersion() {
    try {
      modelManager.sepiaVersion(defaultImgName, defaultImgName);
      view.repaintImage(modelManager.convert(defaultImgName));
      view.repaintHistogram(modelManager.histData(defaultImgName));
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  protected String getFileExtension(String filePath) {
    return filePath.substring(filePath.lastIndexOf(".") + 1);
  }

  private void rgbSplitBtnClicked() {
    tempImgNames = Arrays.asList(new String[]{"img1", "img2", "img3"});
    try {
      modelManager.rgbSplit(defaultImgName, tempImgNames);
      List<BufferedImage> list = new ArrayList<>();
      list.add(modelManager.convert(tempImgNames.get(0)));
      list.add(modelManager.convert(tempImgNames.get(1)));
      list.add(modelManager.convert(tempImgNames.get(2)));
      view.paintSplit(list);
    } catch (NoSuchElementException ex) {
      view.showErrorDialog(ExceptionMessages.NOIMGLOADED);
    }
  }

  private void configureButtonListener() {
    Map<String, Runnable> btnClickedActions = new HashMap<>();
    btnClickedActions.put(ActionCommands.LOADBTNCLICKED, new LoadActionRunnable(this));
    btnClickedActions.put(ActionCommands.SAVEBTNCLICKED, new SaveButtonRunnable(this));
    btnClickedActions.put(ActionCommands.DITHERBTNCLICKED, () -> ditherImage());
    btnClickedActions.put(ActionCommands.BLURBTNCLICKED, () -> blurImage());
    btnClickedActions.put(ActionCommands.SEPIABTNCLICKED, () -> sepiaVersion());
    btnClickedActions.put(ActionCommands.SHARPENBTNCLICKED, () -> sharpenImage());
    btnClickedActions.put(ActionCommands.HORFLIPBTNCLICKED, () -> flipImageHorizontally());
    btnClickedActions.put(ActionCommands.VERFLIPBTNCLICKED, () -> flipImageVertically());
    btnClickedActions.put(ActionCommands.GREYSCALEBTNCLICKED, () ->
            visualizeImage(view.getSelectedGSType()));
    btnClickedActions.put(ActionCommands.BRIGHTENBTNCLICKED, () ->
            brightenImage(view.getBrightenValue()));
    btnClickedActions.put(ActionCommands.RGBCOMBINEBTNCLICKED, () ->
            view.showRgbCombineDialog());
    btnClickedActions.put(ActionCommands.RGBSPLITBTNCLICKED, () -> rgbSplitBtnClicked());
    ButtonListener btnListener = new ButtonListener(btnClickedActions);
    view.addActionListener(btnListener);
  }
}
