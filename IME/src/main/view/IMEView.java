package main.view;

import java.io.IOException;

/**
 * This class represents a view for an image model.
 */
public interface IMEView {

  /**
   * Prints the necessary data to the preferred output stream.
   *
   * @param data the data that needs to be printed.
   * @throws IOException if any error occurs while printing data to the underlying output handle.
   */
  void print(String data) throws IOException;

  /**
   * Prints the necessary data to the preferred output stream and shifts the control to a new line.
   *
   * @param data the data that needs to be printed.
   * @throws IOException if any error occurs while printing data to the underlying output handle.
   */
  void println(String data) throws IOException;
}
