package main.controller;

import java.io.IOException;

/**
 * This interface contains the methods that are required to be implemented by all the concrete
 * controller classes.
 */
public interface IMEControllerBasic {

  /**
   * Gives the controller the go ahead and execute the text based commands.
   *
   * @throws IOException if any I/O error occurs during the lifetime of the controller.
   */
  void control() throws IOException;

}
