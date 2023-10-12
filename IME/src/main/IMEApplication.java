package main;

import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import main.controller.IMEControllerBasic;
import main.controller.IMEControllerBasicImpl;
import main.controller.IMEControllerProImpl;
import main.controller.IMEControllerUltraImpl;
import main.model.ModelManagerPro;
import main.model.ModelManagerProImpl;
import main.ui.IMEMainWindow;
import main.view.IMEView;
import main.view.IMEViewImpl;

/**
 * This class is the entry point of this application.
 */
public class IMEApplication {

  /**
   * This method is the starting point of the whole application. This where the file that contains
   * the script commands are loaded and then the control is passed to the controller.
   *
   * @param args arguments in the form of String array.
   * @throws IOException if any error occurs while attempting to read the script file, if provided.
   */
  public static void main(String[] args) throws IOException {
    IMEControllerBasic controller = null;
    ModelManagerPro modelManagerPro = new ModelManagerProImpl();
    IMEView view = new IMEViewImpl(System.out);
    if (args.length == 0) {
      try {
        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
               InstantiationException e) {
        throw new RuntimeException(e);
      }
      IMEMainWindow mainWindow = new IMEMainWindow();
      mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainWindow.setSize(400, 400);
      mainWindow.setVisible(true);
      controller = new IMEControllerUltraImpl(modelManagerPro, mainWindow);
    } else if (args[0].equals("-file") && args.length == 2) {
      controller = new IMEControllerBasicImpl(new FileInputStream(args[1]), view, modelManagerPro);
    } else if (args[0].equals("-text") && args.length == 1) {
      controller = new IMEControllerProImpl(System.in, view, modelManagerPro);
    } else {
      throw new UnsupportedOperationException("Illegal command line arguments supplied at "
              + "application start");
    }
    controller.control();
  }
}
