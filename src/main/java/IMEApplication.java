import java.io.IOException;

import controller.IMEController;
import controller.IMEControllerBasic;
import service.ImageModelStorage;
import service.ImageProcessorBasicImpl;
import service.PpmImageLoader;

public class IMEApplication {

  public static void main(String args[]) {
    System.out.println("Welcome to IME application!");
    IMEController controller = new IMEControllerBasic(System.in, System.out);
    try {
      controller.go();
    } catch (IOException e) {
      System.out.println("An I/O error occurred!");
    } catch (Exception e) {
      System.out.println("An unexpected error occurred!");
    }
    System.out.println("Exiting...");
  }

}
