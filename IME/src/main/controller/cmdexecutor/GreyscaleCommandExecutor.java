package main.controller.cmdexecutor;

import java.util.NoSuchElementException;

import main.exceptions.CommandExecutionException;
import main.model.GreyscaleType;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "greyscale" command.
 */
public class GreyscaleCommandExecutor implements CommandExecutor<ModelManagerBasic> {

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    if (args.length != 3) {
      throw new CommandExecutionException("Number of arguments should be 3 for greyscale command, "
              + "but received " + args.length);
    }

    String commandType = args[0];
    commandType = commandType.split("-")[0];

    try {
      modelManager.visualize(args[1], args[2], GreyscaleType.valueOf(commandType.toUpperCase()));
    } catch (NoSuchElementException ex) {
      throw new CommandExecutionException(ex.getMessage());
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException("greyscale command cannot be carried out on "
              + commandType + " component");
    }
  }
}
