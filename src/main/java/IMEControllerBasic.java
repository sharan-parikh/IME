import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import service.ImageProcessorBasic;

public class IMEControllerBasic implements IMEController {

  private InputStream in;

  private OutputStream out;

  private ImageProcessorBasic processor;

  public IMEControllerBasic(InputStream in, OutputStream out, ImageProcessorBasic processor) {
    this.in = in;
    this.out = out;
    this.processor = processor;
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
