package main.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import main.model.ModelManagerPro;
import main.model.ModelManagerProImpl;
import main.view.IMEView;
import main.view.IMEViewImpl;

import static org.junit.Assert.assertTrue;

/**
 * Controller unit tests for pro version.
 */
public class IMEControllerProImplTest {

  private Appendable appendable;

  private IMEView view;

  private String loadCommand = "load test-res/images/dog.ppm dog" + System.lineSeparator();

  private ModelManagerPro modelManager;

  /**
   * Constructor.
   */
  public IMEControllerProImplTest() {
    appendable = new StringBuilder();
    view = new IMEViewImpl(appendable);
    modelManager = new ModelManagerProImpl();
  }

  @Before
  public void reset() {
    appendable = new StringBuilder();
    view = new IMEViewImpl(appendable);
    modelManager.clear();
  }

  @Test
  public void testLoadImageJPG() throws IOException {
    InputStream commandInput =
            new ByteArrayInputStream(("load test-res/images/dog.jpg dog"
                    + System.lineSeparator() + "q").getBytes());
    IMEControllerBasic controller = new IMEControllerProImpl(commandInput, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog"));
  }

  @Test
  public void testDitherCommand() throws IOException {
    String command = loadCommand + "dither dog dog-dithered" + System.lineSeparator() + "q";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerProImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog-dithered"));
  }

  @Test
  public void testBlurCommand() throws IOException {
    String command = loadCommand + "blur dog dog-blurred" + System.lineSeparator() + "q";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerProImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog-blurred"));
  }

  @Test
  public void testSharpenCommand() throws IOException {
    String command = loadCommand + "sharpen dog dog-sharpened" + System.lineSeparator() + "q";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerProImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog-sharpened"));
  }

  @Test
  public void testSepiaCommand() throws IOException {
    String command = loadCommand + "sharpen dog dog-sepia" + System.lineSeparator() + "q";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerProImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog-sepia"));
  }
}
