package main.controller.cmdexecutor;

import java.io.FileInputStream;
import java.io.IOException;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "load" command.
 */
public class LoadCommandExecutor extends AbstractLoadCommandExecutor
        implements CommandExecutor<ModelManagerBasic> {

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    try {
      validateArgs(args);
    } catch (IllegalArgumentException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
    load(args, modelManager);
  }

  protected void load(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    try {
      String filePath = args[0];
      String destImgName = args[args.length - 1];
      modelManager.load(destImgName, new FileInputStream(filePath));
    } catch (IOException ex) {
      throw new CommandExecutionException("File with file path: " + args[0]
              + " could not be located.");
    }
  }
}
