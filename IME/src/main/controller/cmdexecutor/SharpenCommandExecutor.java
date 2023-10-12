package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerPro;

/**
 * A class to execute the sharpen transformation.
 */
public class SharpenCommandExecutor implements CommandExecutor<ModelManagerPro> {

  @Override
  public void execute(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    try {
      modelManager.sharpen(args[0], args[1]);
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }

  protected void validateArgs(String[] args) throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("sharpen operation requires 2 arguments, but "
              + args.length + " were received.");
    }
  }
}
