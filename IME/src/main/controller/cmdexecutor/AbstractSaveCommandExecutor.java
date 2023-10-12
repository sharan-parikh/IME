package main.controller.cmdexecutor;

import main.exceptions.CommandExecutionException;

/**
 * Base class for save command executors.
 */
public class AbstractSaveCommandExecutor {

  protected void validateArgs(String[] args) throws CommandExecutionException {
    if (args.length != 2) {
      throw new CommandExecutionException("Number of arguments should be equal to 2 for save"
              + " command but received " + args[args.length - 1]);
    }
  }

}
