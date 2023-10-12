package main.controller.cmdexecutor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This implementation of CommandExecutor is responsible for the executing the "save" operation on
 * a ppm file. A new class SaveCommandExecutorPro has been created and should be used in place of
 * this deprecated class.
 */
public class SaveCommandExecutor extends AbstractSaveCommandExecutor
        implements CommandExecutor<ModelManagerBasic> {

  public SaveCommandExecutor() {
    // empty constructor
  }

  @Override
  public void execute(String[] args, ModelManagerBasic modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    try {
      String filePath = args[1];
      OutputStream out = new FileOutputStream(filePath);
      modelManager.save(args[1], out);
    } catch (IOException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }
}
