package main.controller.cmdexecutor;

import java.util.Arrays;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "rgb-split" command.
 */
public class RgbSplitCommandExecutor implements CommandExecutor<ModelManagerBasic> {

  /**
   * A method to execute the split.
   *
   * @param args         arguments.
   * @param modelManager object.
   * @throws CommandExecutionException if any exception arises.
   */
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    if (args.length != 4) {
      throw new CommandExecutionException("Number of arguments have to be equal to 4 for rgb-split "
              + "command, but received " + args.length);
    }
    String[] imageNames = Arrays.copyOfRange(args, 1, args.length);
    modelManager.rgbSplit(args[0], Arrays.asList(imageNames));
  }
}
