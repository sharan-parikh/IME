package main.ui.runnables;

import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

import main.controller.UIFeatures;

/**
 * A class to define load button operation.
 */
public class LoadActionRunnable implements Runnable {

  private final UIFeatures features;

  public LoadActionRunnable(UIFeatures features) {
    this.features = features;
  }

  @Override
  public void run() {
    JFileChooser fileChooser =
            new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    int state = fileChooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      try {
        features.loadImage(fileChooser.getSelectedFile().getAbsolutePath());
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
      }
    }
  }
}
