package main.ui.runnables;

import java.io.IOException;
import java.util.NoSuchElementException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import main.controller.UIFeatures;

/**
 * A class to define save button operation.
 */
public class SaveButtonRunnable implements Runnable {

  private UIFeatures features;

  public SaveButtonRunnable(UIFeatures features) {
    this.features = features;
  }

  @Override
  public void run() {
    JFileChooser fileChooser =
            new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int state = fileChooser.showSaveDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      try {
        features.saveImage(fileChooser.getSelectedFile().getAbsolutePath());
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null,
                "An I/O error occurred while loading the image.",
                "Error",
                JOptionPane.ERROR_MESSAGE);
      } catch (IllegalArgumentException ex) {
        JOptionPane.showMessageDialog(null,
                ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
      } catch (NoSuchElementException ex) {
        JOptionPane.showMessageDialog(null,
                "There is no image to operate on!",
                "Error",
                JOptionPane.ERROR_MESSAGE);
      }
    }
  }
}
