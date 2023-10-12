package main.controller.cmdexecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import main.exceptions.CommandExecutionException;
import main.model.ModelManagerBasic;

/**
 * This is a factory class that takes that supplies the necessary concrete class of CommandExecutor
 * interface. A new instance of the factory should be created every time some command needs to be
 * executed on a different image model.
 */
public class CommandExecutorFactory {
  protected final Map<String, Supplier<CommandExecutor<? extends ModelManagerBasic>>> commands;

  /**
   * Creates the instance of the CommandExecutorFactory class and initializes the required
   * CommandExecutor concrete class objects.
   */
  public CommandExecutorFactory() {
    commands = new HashMap<>();
  }

  /**
   * Gets the appropriate instance of the concrete class implementing the CommandExecutor interface.
   *
   * @param command the command type, like "load", "brighten" etc.
   * @return instance of the concrete class implementing the CommandExecutor interface
   * @throws CommandExecutionException this exception is thrown if any error occurs during the
   *                                   command execution logic.
   */
  public CommandExecutor<? extends ModelManagerBasic> getCommandExecutor(String command)
          throws CommandExecutionException {
    Supplier<CommandExecutor<? extends ModelManagerBasic>> supplier = commands.get(command);
    if (supplier == null) {
      throw new CommandExecutionException("Command: " + command + " is not supported.");
    }
    return supplier.get();
  }

  public void registerCommandExecutor(String command,
                                      Supplier<CommandExecutor<? extends ModelManagerBasic>>
                                              executor) {
    commands.put(command, executor);
  }
}
