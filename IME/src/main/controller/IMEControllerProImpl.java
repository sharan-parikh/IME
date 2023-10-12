package main.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerPro;
import main.view.IMEView;

/**
 * This controller supports the new image enhancement and manipulation features as well as the old
 * features, i.e. it is backwards compatible.
 */
public class IMEControllerProImpl extends IMEControllerBasicImpl {

  /**
   * Constructs the IMEControllerProImpl object and initializes the necessary variables.
   *
   * @param commandInput the input handle from where the commands will be read.
   * @param view         the view where the user will be shown necessary messages.
   * @param modelManager the model manager instance.
   */
  public IMEControllerProImpl(InputStream commandInput,
                              IMEView view,
                              ModelManagerPro modelManager) {
    super(commandInput, view, modelManager);
  }

  @Override
  public void control() throws IOException {
    showWelcomeMessage();
    try {
      execCommandsInteractive();
    } catch (CommandExecutionException ex) {
      view.println("An error occurred during the execution of one of the commands:");
      view.println(ex.getMessage());
    } catch (Exception ex) {
      view.println("An error occurred: " + ex.getMessage());
    }
    showExitMessage();
  }

  protected void execCommandsInteractive() throws CommandExecutionException {
    Scanner sc = new Scanner(commandInput);
    while (true) {
      if (sc.hasNextLine()) {
        String line = sc.nextLine().trim();
        if (line.equals("q") || line.equals("quit")) {
          break;
        } else {
          execLine(line);
        }
      }
    }
  }

  @Override
  protected void showWelcomeMessage() throws IOException {
    view.println("Welcome to IME Application(Pro version)...");
  }

  protected void showExitMessage() throws IOException {
    view.println("Thank you for using IME Application(Pro version). Exiting now...");
  }
}
