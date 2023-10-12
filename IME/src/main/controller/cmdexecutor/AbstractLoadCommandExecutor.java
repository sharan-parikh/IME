package main.controller.cmdexecutor;

/**
 * Base class for load command executors.
 */
public class AbstractLoadCommandExecutor {

  protected void validateArgs(String[] args) throws IllegalArgumentException {
    if (args.length != 2) {
      throw new IllegalArgumentException("Number of arguments for loading a file should be 2 "
              + "for load command, but received " + args.length);
    }
  }
}
