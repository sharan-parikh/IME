package main.controller.cmdexecutor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.NoSuchElementException;

import main.exceptions.CommandExecutionException;
import main.model.ImageFormat;
import main.model.ModelManagerPro;

/**
 * A class to load command for pro version of the model.
 */
public class LoadCommandExecutorPro extends AbstractLoadCommandExecutor implements
        CommandExecutor<ModelManagerPro> {

  @Override
  public void execute(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    load(args, modelManager);
  }

  protected void load(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    String filePath = args[0];
    String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
    try {
      modelManager.load(args[1], new FileInputStream(filePath),
              ImageFormat.valueOf(fileExtension.toUpperCase()));
    } catch (IOException ex) {
      throw new CommandExecutionException(ex.getMessage());
    } catch (NoSuchElementException ex) {
      throw new CommandExecutionException(ex.getMessage());
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException("Supplied file format: " + fileExtension
              + " is not supported.");
    }
  }

}
