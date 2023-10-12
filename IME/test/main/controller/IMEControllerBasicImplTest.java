package main.controller;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import main.model.ModelManagerBasic;
import main.model.ModelManagerProImpl;
import main.view.IMEView;
import main.view.IMEViewImpl;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class contains the unit tests for the IMEControllerImpl class.
 */
public class IMEControllerBasicImplTest {

  private Appendable appendable;

  private IMEView view;

  private String loadCommand = "load test-res/images/dog.ppm dog" + System.lineSeparator();

  private ModelManagerBasic modelManager;

  /**
   * Constructor.
   *
   * @throws FileNotFoundException if file not found.
   */
  public IMEControllerBasicImplTest() throws FileNotFoundException {
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
  public void testLoadImagePPM() throws IOException {
    InputStream commandInput = new ByteArrayInputStream(loadCommand.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(commandInput, view, modelManager);
    controller.control();
    try {
      assertTrue(modelManager.has("dog"));
    } catch (NoSuchElementException ex) {
      fail("Failed to load image with name dog");
    }
  }

  @Test
  public void saveImage() throws IOException {
    String commands = new StringBuilder().append(loadCommand)
            .append("save test-res/images/dog-1.ppm dog").toString();
    InputStream commandInput = new ByteArrayInputStream(commands.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(commandInput, view, modelManager);
    controller.control();
    try {
      InputStream fileInput = new FileInputStream("test-res/images/dog-1.ppm");
    } catch (FileNotFoundException ex) {
      fail("File could not be saved successfully.");
    }
  }

  @Test
  public void testExecScript() throws IOException {
    InputStream fileInput = new FileInputStream("test-res/test-script");
    IMEControllerBasic controller = new IMEControllerBasicImpl(fileInput, view, modelManager);
    controller.control();
    List<String> expectedImageNames = Arrays.asList("dog", "dog-brighter", "dog-vertical",
            "dog-horizontal", "dog-gs-value",
            "dog-red", "dog-green", "dog-blue", "dog-darker", "dog-red-tint",
            "dog", "dog-dithered", "dog-blurred",
            "dog-sharpened", "dog-sepia");

    for (String imgName : expectedImageNames) {
      assertTrue(modelManager.has(imgName));
    }
  }

  @Test
  public void testLoadCommandWhenArgumentsAreIllegal() throws IOException {
    String command = "load resources/images/dog.ppm";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    String output = appendable.toString();
    assertTrue(appendable.toString().contains("error"));
  }

  @Test
  public void testGreyscaleCommand() throws IOException {
    String command = loadCommand + "greyscale value-component dog dog-greyscale";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog"));
    assertTrue(modelManager.has("dog-greyscale"));
  }

  @Test
  public void testHorizontalFlipCommand() throws IOException {
    String command = loadCommand + "horizontal-flip dog dog-horizontal";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog"));
    assertTrue(modelManager.has("dog-horizontal"));
  }

  @Test
  public void testVerticalFlipCommand() throws IOException {
    String command = loadCommand + "vertical-flip dog dog-vertical";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog"));
    assertTrue(modelManager.has("dog-vertical"));
  }

  @Test
  public void testBrightenCommand() throws IOException {
    String command = loadCommand + "brighten 10 dog dog-brighter";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog"));
    assertTrue(modelManager.has("dog-brighter"));
  }

  @Test
  public void testDarkenCommand() throws IOException {
    String command = loadCommand + "darken 10 dog dog-darker";
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    assertTrue(modelManager.has("dog"));
    assertTrue(modelManager.has("dog-darker"));
  }

  @Test
  public void testRgbCombineCommand() throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(loadCommand)
            .append("rgb-split dog dog-red dog-blue dog-green")
            .append(System.lineSeparator())
            .append("rgb-combine dog-combined dog-red dog-blue dog-green");
    String command = sb.toString();
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    List<String> expectedImageNames = Arrays.asList("dog", "dog-red",
            "dog-blue", "dog-green", "dog-combined");

    for (String imgName : expectedImageNames) {
      assertTrue(modelManager.has(imgName));
    }
  }

  @Test
  public void testRgbSplitCommand() throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append(loadCommand)
            .append("rgb-split dog dog-red dog-blue dog-green");
    String command = sb.toString();
    InputStream in = new ByteArrayInputStream(command.getBytes());
    IMEControllerBasic controller = new IMEControllerBasicImpl(in, view, modelManager);
    controller.control();
    List<String> expectedImageNames = Arrays.asList("dog",
            "dog-red", "dog-blue", "dog-green");

    for (String imgName : expectedImageNames) {
      assertTrue(modelManager.has(imgName));
    }
  }
}