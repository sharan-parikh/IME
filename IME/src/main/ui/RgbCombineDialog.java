package main.ui;

import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

import main.controller.UIFeatures;

/**
 * A dialog that opens up upon clicking combine operation.
 */
public class RgbCombineDialog extends JDialog implements ActionListener {

  private UIFeatures features;
  private JButton redCompFileBtn;

  private JButton greenCompFileBtn;

  private JButton blueCompFileBtn;

  private JButton okayButton;

  private String redCompFilePath;

  private String greenCompFilePath;

  private String blueCompFilePath;

  /**
   * A constructor to initialize the dialog components.
   *
   * @param parent   parent component.
   * @param features features object.
   */
  public RgbCombineDialog(JFrame parent, UIFeatures features) {
    super(parent);
    this.features = features;
    setModalityType(ModalityType.APPLICATION_MODAL);
    setLocationRelativeTo(parent);
    setSize(400, 200);
    setLayout(new FlowLayout());
    setTitle("Rgb Combine");
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
    redCompFileBtn = new JButton("Select red component file");
    redCompFileBtn.setActionCommand(ActionCommands.REDCOMPFILESELECTED);
    greenCompFileBtn = new JButton("Select green component file");
    greenCompFileBtn.setActionCommand(ActionCommands.GREENCOMPFILESELECTED);
    blueCompFileBtn = new JButton("Select blue component file");
    blueCompFileBtn.setActionCommand(ActionCommands.BLUECOMPFILESELECTED);
    okayButton = new JButton("OK");
    okayButton.setEnabled(false);
    panel.add(redCompFileBtn);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(greenCompFileBtn);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(blueCompFileBtn);
    panel.add(Box.createRigidArea(new Dimension(0, 10)));
    panel.add(okayButton);
    addActionListeners();
    add(panel);
    setVisible(true);
  }

  private void addActionListeners() {
    redCompFileBtn.addActionListener(this);
    greenCompFileBtn.addActionListener(this);
    blueCompFileBtn.addActionListener(this);
    okayButton.addActionListener((ActionEvent e) -> {
      List<String> filePaths = new ArrayList<>();
      filePaths.add(redCompFilePath);
      filePaths.add(greenCompFilePath);
      filePaths.add(blueCompFilePath);
      try {
        features.rgbCombine(filePaths);
      } catch (IOException ex) {
        JOptionPane.showMessageDialog(null,
                "An I/O error occurred while loading one of the images",
                "Error",
                JOptionPane.ERROR_MESSAGE);
      }
      dispose();
    });
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JFileChooser fileChooser =
            new JFileChooser(FileSystemView.getFileSystemView().getDefaultDirectory());
    fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
    int state = fileChooser.showOpenDialog(null);

    if (state == JFileChooser.APPROVE_OPTION) {
      if (e.getActionCommand().equals(ActionCommands.REDCOMPFILESELECTED)) {
        redCompFilePath = fileChooser.getSelectedFile().getAbsolutePath();
      } else if (e.getActionCommand().equals(ActionCommands.BLUECOMPFILESELECTED)) {
        blueCompFilePath = fileChooser.getSelectedFile().getAbsolutePath();
      } else {
        greenCompFilePath = fileChooser.getSelectedFile().getAbsolutePath();
      }
    }
    if (redCompFilePath != null && blueCompFilePath != null && greenCompFilePath != null) {
      okayButton.setEnabled(true);
    }
  }
}
