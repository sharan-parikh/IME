package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "brighten" command.
 */
public class BrightenCommandExecutor implements CommandExecutor {

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    if (args.length != 3) {
      throw new CommandExecutionException("Number of arguments should be 3 for brighten command,"
              + " received " + args.length);
    }
    int constant = 0;
    try {
      constant = Integer.parseInt(args[0]);
      modelManager.brighten(args[1], args[2], constant);
    } catch (NumberFormatException ex) {
      throw new CommandExecutionException("Number provided for brightening is not valid.");
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }
}
