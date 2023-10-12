package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerPro;

/**
 * A class to execute the sepia transformation.
 */
public class SepiaCommandExecutor implements CommandExecutor<ModelManagerPro> {

  @Override
  public void execute(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    try {
      modelManager.sepiaVersion(args[0], args[1]);
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }

  protected void validateArgs(String[] args) throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("sepia operation requires 2 arguments, but "
              + args.length + " were received.");
    }
  }
}
