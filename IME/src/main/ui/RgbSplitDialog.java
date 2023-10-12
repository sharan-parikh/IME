package main.ui;

import java.awt.GridLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import java.util.List;

import javax.swing.JPanel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

import main.controller.UIFeatures;
import main.model.GreyscaleType;

/**
 * A dialog that opens up upon clicking split operation.
 */
public class RgbSplitDialog extends JDialog {

  private UIFeatures features;
  JButton selectBtn;
  JComboBox<GreyscaleType> selectComboBox;

  /**
   * A constructor to initialize the dialog components.
   *
   * @param parent   parent component.
   * @param features object.
   * @param images   split images.
   */
  public RgbSplitDialog(JFrame parent, UIFeatures features, List<BufferedImage> images) {
    super(parent);
    this.features = features;
    setModalityType(ModalityType.APPLICATION_MODAL);
    setTitle("Rgb Split");
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));

    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(1, 0, 10, 10));
    JLabel image1 = new JLabel(new ImageIcon(images.get(0)));
    image1.setBorder(BorderFactory.createTitledBorder("Red Component"));
    JLabel image2 = new JLabel(new ImageIcon(images.get(1)));
    image2.setBorder(BorderFactory.createTitledBorder("Green Component"));
    JLabel image3 = new JLabel(new ImageIcon(images.get(2)));
    image3.setBorder(BorderFactory.createTitledBorder("Blue Component"));

    grid.add(image1);
    grid.add(image2);
    grid.add(image3);

    GreyscaleType[] values =
            new GreyscaleType[]{GreyscaleType.RED, GreyscaleType.GREEN, GreyscaleType.BLUE};
    selectComboBox = new JComboBox<>(values);
    selectComboBox.setSelectedItem(0);

    selectBtn = new JButton("Select");

    JLabel text = new JLabel("Select the image you want to use from the split");
    JScrollPane imageScrollPane = new JScrollPane(grid);

    container.add(imageScrollPane);
    JPanel selectionPanel = new JPanel(new FlowLayout());
    selectionPanel.add(selectComboBox);
    selectionPanel.add(selectBtn);
    selectionPanel.add(text);
    container.add(selectionPanel);
    add(container);
    addActionListeners();
    pack();
    setVisible(true);
  }

  private void addActionListeners() {
    selectBtn.addActionListener((ActionEvent e) -> {
      features.rgbSplit((GreyscaleType) selectComboBox.getSelectedItem());
      dispose();
    });
  }
}
