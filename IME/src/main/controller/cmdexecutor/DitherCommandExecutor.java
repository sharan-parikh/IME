package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerPro;

/**
 * This command executor is solely responsible for the dither operation that can be performed on
 * images.
 */
public class DitherCommandExecutor implements CommandExecutor<ModelManagerPro> {

  @Override
  public void execute(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    try {
      modelManager.dither(args[0], args[1]);
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }

  protected void validateArgs(String[] args) throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("dither operation requires 2 arguments, but "
              + args.length + " were received.");
    }
  }
}
