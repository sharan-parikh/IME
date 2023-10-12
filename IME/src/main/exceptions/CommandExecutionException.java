package main.exceptions;

/**
 * This exception is thrown whenever any kind of error/exception occurs while executing a script
 * command.
 */
public class CommandExecutionException extends Exception {

  /**
   * Constructs the CommandExecutionException object.
   *
   * @param message the exception message.
   */
  public CommandExecutionException(String message) {
    super(message);
  }
}
