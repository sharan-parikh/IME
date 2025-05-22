package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class IMEControllerBasic implements IMEController {

  private InputStream in;

  private OutputStream out;

  public IMEControllerBasic(InputStream in, OutputStream out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void go() throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
    String line;

    while(true) {
      line = reader.readLine();
      if (line != null) {
        String[] tokens = line.split("\\s");
        // TODO: add code for executors according to command
      }
    }
  }
}
