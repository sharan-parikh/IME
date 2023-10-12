package main.controller;

import java.io.IOException;
import java.io.InputStream;


import java.util.Arrays;
import java.util.Scanner;

import main.controller.cmdexecutor.BrightenCommandExecutor;
import main.controller.cmdexecutor.CommandExecutor;
import main.controller.cmdexecutor.CommandExecutorFactory;
import main.controller.cmdexecutor.DarkenCommandExecutor;
import main.controller.cmdexecutor.DitherCommandExecutor;
import main.controller.cmdexecutor.GreyscaleCommandExecutor;
import main.controller.cmdexecutor.HorizontalFlipCommandExecutor;
import main.controller.cmdexecutor.LoadCommandExecutorPro;
import main.controller.cmdexecutor.RgbCombineCommandExecutor;
import main.controller.cmdexecutor.RgbSplitCommandExecutor;
import main.controller.cmdexecutor.SaveCommandExecutorPro;
import main.controller.cmdexecutor.SepiaCommandExecutor;
import main.controller.cmdexecutor.SharpenCommandExecutor;
import main.controller.cmdexecutor.VerticalFlipCommandExecutor;
import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;
import main.view.IMEView;

/**
 * This is the default implementation of the IMEController interface and contains the necessary
 * method implementations to carry out the different IME operations.
 */
public class IMEControllerBasicImpl implements IMEControllerBasic {

  protected final InputStream commandInput;

  protected final CommandExecutorFactory factory;

  protected final ModelManagerBasic modelManager;

  protected final IMEView view;

  /**
   * Constructs the IMEControllerImpl object and initializes the necessary variables.
   *
   * @param commandInput the input handle from where the commands will be read.
   * @param view         the view where the user will be shown necessary messages.
   * @param modelManager the model manager instance.
   */
  public IMEControllerBasicImpl(InputStream commandInput,
                                IMEView view,
                                ModelManagerBasic modelManager) {
    this.commandInput = commandInput;
    this.modelManager = modelManager;
    this.factory = new CommandExecutorFactory();
    this.view = view;
    registerCommandExecutors();
  }

  protected void registerCommandExecutors() {
    factory.registerCommandExecutor("brighten", () -> new BrightenCommandExecutor());
    factory.registerCommandExecutor("vertical-flip", () ->
            new VerticalFlipCommandExecutor());
    factory.registerCommandExecutor("horizontal-flip", () ->
            new HorizontalFlipCommandExecutor());
    factory.registerCommandExecutor("darken", () -> new DarkenCommandExecutor());
    factory.registerCommandExecutor("greyscale", () -> new GreyscaleCommandExecutor());
    factory.registerCommandExecutor("rgb-split", () -> new RgbSplitCommandExecutor());
    factory.registerCommandExecutor("rgb-combine", () -> new RgbCombineCommandExecutor());
    factory.registerCommandExecutor("load", () -> new LoadCommandExecutorPro());
    factory.registerCommandExecutor("save", () -> new SaveCommandExecutorPro());
    factory.registerCommandExecutor("dither", () -> new DitherCommandExecutor());
    factory.registerCommandExecutor("sharpen", () -> new SharpenCommandExecutor());
    factory.registerCommandExecutor("blur", () -> new SepiaCommandExecutor());
    factory.registerCommandExecutor("sepia", () -> new SepiaCommandExecutor());
  }

  @Override
  public void control() throws IOException {
    try {
      showWelcomeMessage();
      executeScript();
    } catch (IOException ex) {
      view.println("An I/O error occurred during command execution: " + ex.getMessage());
    } catch (CommandExecutionException ex) {
      view.println("An error occurred during script command execution: " + ex.getMessage());
    } catch (Exception ex) {
      view.println("An unexpected error occurred: " + ex.getMessage());
    }
    showExitMessage();
  }

  protected void executeScript() throws CommandExecutionException {
    Scanner sc = new Scanner(commandInput);
    while (sc.hasNextLine()) {
      String line = sc.nextLine();
      execLine(line);
    }
    sc.close();
  }

  protected void execLine(String commandLine) throws CommandExecutionException {
    if (commandLine != null && commandLine.length() != 0 && commandLine.charAt(0) != '#') {
      String[] commandParts = commandLine.trim().split("\\s");
      String command = commandParts[0];
      String[] commandArguments = Arrays.copyOfRange(commandParts, 1,
              commandParts.length);
      CommandExecutor executor = factory.getCommandExecutor(command);
      executor.execute(commandArguments, modelManager);
    }
  }


  protected void showWelcomeMessage() throws IOException {
    view.println("Welcome to IME Application(Basic version)..." + System.lineSeparator());
  }

  protected void showExitMessage() throws IOException {
    view.println("Thank you for using IME Application(Basic version). Exiting now... ");
  }

}
