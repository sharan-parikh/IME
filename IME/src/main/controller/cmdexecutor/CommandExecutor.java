package main.controller.cmdexecutor;


import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This interface contains the methods required for the execution of the image manipulation
 * commands.
 *
 * @param <T> the model manager type.
 */
public interface CommandExecutor<T extends ModelManagerBasic> {

  /**
   * Executes the necessary logic according to the command given and gives the image name.
   *
   * @param args the arguments required for the command execution.
   * @throws CommandExecutionException this exception is thrown in case any problem occurs during
   *                                   command execution logic.
   */
  void execute(String[] args, T modelManager) throws CommandExecutionException;
}
