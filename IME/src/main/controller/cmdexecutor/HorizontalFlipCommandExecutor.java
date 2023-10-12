package main.controller.cmdexecutor;


import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "horizontal-flip"
 * command.
 */
public class HorizontalFlipCommandExecutor implements CommandExecutor<ModelManagerBasic> {

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("Number of arguments should be equal to 2 for " +
              "horizontal-flip command, but received " + args.length);
    }
    modelManager.flipHorizontally(args[0], args[1]);
  }
}
