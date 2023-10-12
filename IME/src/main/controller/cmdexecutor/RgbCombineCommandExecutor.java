package main.controller.cmdexecutor;

import java.util.Arrays;
import java.util.List;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "rgb-combine"
 * command.
 */
public class RgbCombineCommandExecutor implements CommandExecutor<ModelManagerBasic> {

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    if (args.length != 4) {
      throw new CommandExecutionException("Number of arguments have to be equal to 4 for combine "
              + "operation, but received " + args.length);
    }
    List<String> imageNamesToCombine = Arrays.asList(Arrays.copyOfRange(args, 1, args.length));
    modelManager.rgbCombine(imageNamesToCombine, args[0]);
  }
}
