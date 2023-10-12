package main.view;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.List;

import main.controller.UIFeatures;
import main.model.GreyscaleType;

/**
 * The interface that represents the main GUI view.
 */
public interface GUIView {

  /**
   * A method to repaint an image after an operation.
   *
   * @param image image to repaint it with.
   */
  void repaintImage(BufferedImage image);

  /**
   * A method to repaint the histogram after an operation.
   *
   * @param data new frequency array.
   */
  void repaintHistogram(int[][] data);

  /**
   * A method to add all features.
   *
   * @param features UIFeatures object.
   */
  void addFeatures(UIFeatures features);

  /**
   * A method to add action lister.
   *
   * @param e action.
   */
  void addActionListener(ActionListener e);

  /**
   * A method t get the greyscale type from the dropdown.
   *
   * @return greyscale enum type.
   */
  GreyscaleType getSelectedGSType();


  /**
   * A method to get the bright/dark value.
   *
   * @return value.
   */
  int getBrightenValue();

  /**
   * A method to open a dialog for RGB combine operation.
   */
  void showRgbCombineDialog();

  /**
   * A method to open a dialog for the split operation.
   *
   * @param images list of split images.
   */
  void paintSplit(List<BufferedImage> images);

  /**
   * Shows dialog containing appropriate error message.
   */
  void showErrorDialog(String errorMessage);

  /**
   * Shows dialog containing appropriate success message after image has been saved.
   *
   * @param title   title of the dialog.
   * @param message the message to be displayed in the dialog.
   */
  void showOperationSuccessDialog(String title, String message);
}
