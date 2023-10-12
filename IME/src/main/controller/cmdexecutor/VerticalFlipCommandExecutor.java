package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "vertical-split"
 * command.
 */
public class VerticalFlipCommandExecutor implements CommandExecutor<ModelManagerBasic> {

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("Number of arguments should be equal to 2 for" +
              " vertical-flip command, but received " + args.length);
    }
    modelManager.flipVertically(args[0], args[1]);
  }
}
