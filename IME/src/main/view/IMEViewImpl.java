package main.view;

import java.io.IOException;

/**
 * This class is an implementation of IMEImageView and represents a view that can be used to print
 * necessary data to an output stream.
 */
public class IMEViewImpl implements IMEView {

  private Appendable appendable;

  /**
   * Constructs the IMEImageViewImpl object.
   *
   * @param appendable the preferred output stream.
   */
  public IMEViewImpl(Appendable appendable) {
    this.appendable = appendable;
  }

  @Override
  public void print(String data) throws IOException {
    appendable.append(data);
  }

  @Override
  public void println(String data) throws IOException {
    appendable.append(data).append(System.lineSeparator());
  }
}
