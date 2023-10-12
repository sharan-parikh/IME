package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerPro;

/**
 * This command executor is solely responsible for the blur operation that can be performed on
 * images.
 */
public class BlurCommandExecutor implements CommandExecutor<ModelManagerPro> {

  @Override
  public void execute(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    try {
      modelManager.blur(args[0], args[1]);
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }

  protected void validateArgs(String[] args) throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("blur operation requires 2 arguments, but "
              + args.length + " were received.");
    }
  }
}
