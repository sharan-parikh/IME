package main.controller;

import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import main.model.GreyscaleType;
import main.model.ModelManagerPro;
import main.model.ModelManagerProImpl;
import main.view.GUIView;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Controller unit tests for ultra version.
 */
public class IMEControllerUltraImplTest {

  private ModelManagerPro modelManager;

  private GUIView view;

  private String defaultImgName = "image";

  private boolean repaintImageCalled;

  private boolean repaintHistogramCalled;

  private boolean showErrorDialogCalled;

  private boolean showSuccessDialog;

  /**
   * Constructor.
   */
  public IMEControllerUltraImplTest() {
    modelManager = new ModelManagerProImpl();
    view = new GUIViewStub();
  }

  @Before
  public void reset() {
    repaintImageCalled = false;
    repaintHistogramCalled = false;
    showErrorDialogCalled = false;
    showSuccessDialog = false;
  }

  @Test
  public void testLoadImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly");
    }
    assertTrue(modelManager.has(defaultImgName));
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void testSaveImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    try {
      features.saveImage("test-res/images/dog.png");
    } catch (IOException ex) {
      fail("dog.png file could not be saved properly.");
    }
    assertTrue(modelManager.has(defaultImgName));
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
    assertTrue(showSuccessDialog);
    try {
      InputStream in = new FileInputStream("test-res/images/dog.png");
    } catch (FileNotFoundException ex) {
      fail("Test for saving a file failed.");
    }
  }

  @Test
  public void brightenImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.brightenImage(20);
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void visualizeImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.visualizeImage(GreyscaleType.LUMA);
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void flipImageHorizontally() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.flipImageHorizontally();
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void flipImageVertically() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.flipImageVertically();
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void rgbCombine() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    List<String> filePaths = Arrays.asList(new String[]{
        "test-res/images/dog-red.ppm",
        "test-res/images/dog-green.ppm",
        "test-res/images/dog-blue.ppm"
    });
    try {
      features.rgbCombine(filePaths);
    } catch (IOException ex) {
      fail("One of the files for combine operation could not be loaded properly.");
    }
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void sharpenImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.sharpenImage();
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void blurImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.blurImage();
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void ditherImage() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.ditherImage();
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void sepiaVersion() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      features.loadImage("test-res/images/dog.jpg");
    } catch (IOException ex) {
      fail("dog.jpg file could not be loaded properly.");
    }
    features.sepiaVersion();
    modelManager.has(defaultImgName);
    assertTrue(repaintImageCalled);
    assertTrue(repaintHistogramCalled);
  }

  @Test
  public void testErrorDialogShown() {
    UIFeatures features = new IMEControllerUltraImpl(modelManager, view);
    try {
      // loading a file that is not present.
      features.loadImage("test-res/images/not-present.jpg");
    } catch (IOException ex) {
      // just catching the exception.
    }
    assertTrue(showErrorDialogCalled);
  }

  class GUIViewStub implements GUIView {

    private UIFeatures features;


    @Override
    public void repaintImage(BufferedImage image) {
      repaintImageCalled = true;
    }

    @Override
    public void repaintHistogram(int[][] data) {
      repaintHistogramCalled = true;
    }

    @Override
    public void addFeatures(UIFeatures features) {
      this.features = features;
    }

    @Override
    public void addActionListener(ActionListener e) {
      // implementation not needed.
    }

    @Override
    public GreyscaleType getSelectedGSType() {
      return null;
    }

    @Override
    public int getBrightenValue() {
      return 0;
    }

    @Override
    public void showRgbCombineDialog() {
      // implementation not needed.
    }

    @Override
    public void paintSplit(List<BufferedImage> images) {
      // implementation not needed.
    }

    @Override
    public void showErrorDialog(String errorMessage) {
      showErrorDialogCalled = true;
    }

    @Override
    public void showOperationSuccessDialog(String title, String message) {
      showSuccessDialog = true;
    }
  }
}