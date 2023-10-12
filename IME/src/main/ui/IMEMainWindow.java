package main.ui;

import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import main.controller.UIFeatures;
import main.model.GreyscaleType;
import main.view.GUIView;

/**
 * A class that represents the main GUI window and defines it behaviour.
 */
public class IMEMainWindow extends JFrame implements GUIView {

  private UIFeatures features;


  private JPanel imagePanel;


  private HistogramPanel histPanel;

  private final JPanel mainPanel;

  private List<JButton> nonManipulationButtons;

  private List<JButton> manipulationButtons;

  private JComboBox<GreyscaleType> comboBox;


  private final String[][] nonManipulationBtnProps = {
          {"Load Image", ActionCommands.LOADBTNCLICKED},
          {"Save Image", ActionCommands.SAVEBTNCLICKED}
  };

  private final String[][] manipulationBtnPropsWithoutInputs = {
          {"Horizontal flip", ActionCommands.HORFLIPBTNCLICKED},
          {"Vertical flip", ActionCommands.VERFLIPBTNCLICKED},
          {"Rgb-split", ActionCommands.RGBSPLITBTNCLICKED},
          {"Rgb-combine", ActionCommands.RGBCOMBINEBTNCLICKED},
          {"Blur", ActionCommands.BLURBTNCLICKED},
          {"Dither", ActionCommands.DITHERBTNCLICKED},
          {"Sharpen", ActionCommands.SHARPENBTNCLICKED},
          {"Sepia", ActionCommands.SEPIABTNCLICKED}
  };

  private JButton gsBtn;

  private JButton brightenBtn;

  private JTextField brightenInput;

  /**
   * Constructor that initializes all components.
   */
  public IMEMainWindow() {
    setTitle("IME Application (Ultra Version)");
    mainPanel = new JPanel();
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
    JScrollPane scrollPane = new JScrollPane(mainPanel);
    add(scrollPane);
    createButtons();
    addGridContainer();
    addToolBar();
    addMorFeaturesToolbar();
    add(mainPanel);
    setLocationRelativeTo(null);
    pack();
  }

  private void addGridContainer() {
    JPanel grid = new JPanel();
    grid.setLayout(new GridLayout(1, 0, 10, 10));
    mainPanel.add(grid);
    imagePanel = new JPanel();
    JScrollPane imageScrollPane = new JScrollPane(imagePanel);
    imageScrollPane.setBorder(BorderFactory.createTitledBorder("Image"));
    imageScrollPane.setPreferredSize(new Dimension(100, 600));

    histPanel = new HistogramPanel(null);
    JScrollPane histScrollPane = new JScrollPane(histPanel);
    histPanel.setPreferredSize(new Dimension(100, 600));
    histScrollPane.setBorder(BorderFactory.createTitledBorder("Histogram"));
    grid.add(imageScrollPane);
    grid.add(histScrollPane);
    mainPanel.add(grid);
  }

  private void addToolBar() {
    JPanel nonManipulationsPanel = new JPanel();
    nonManipulationsPanel.setLayout(new FlowLayout());
    for (JButton btn : nonManipulationButtons) {
      nonManipulationsPanel.add(btn);
    }
    JScrollPane scrollPane1 = new JScrollPane(nonManipulationsPanel);
    scrollPane1.setBorder(BorderFactory.createTitledBorder("Non Manipulation Features"));
    mainPanel.add(scrollPane1);

    JPanel manipulationsPanel = new JPanel();
    manipulationsPanel.setLayout(new FlowLayout());
    for (JButton btn : manipulationButtons) {
      manipulationsPanel.add(btn);
    }
    JScrollPane scrollPane2 = new JScrollPane(manipulationsPanel);
    scrollPane2.setBorder(BorderFactory.createTitledBorder("Manipulation Features"));
    mainPanel.add(scrollPane2);
  }

  private void createButtons() {
    nonManipulationButtons = new ArrayList<>();
    for (String[] props : nonManipulationBtnProps) {
      JButton btn = new JButton(props[0]);
      btn.setActionCommand(props[1]);
      nonManipulationButtons.add(btn);
    }
    manipulationButtons = new ArrayList<>();
    for (String[] props : manipulationBtnPropsWithoutInputs) {
      JButton btn = new JButton(props[0]);
      btn.setActionCommand(props[1]);
      manipulationButtons.add(btn);
    }
  }

  private void addMorFeaturesToolbar() {
    JPanel gsPanel = new JPanel();
    gsPanel.setLayout(new FlowLayout());
    gsPanel.setBorder(BorderFactory.createTitledBorder("More Features"));
    comboBox = new JComboBox<>(GreyscaleType.values());
    comboBox.setSelectedItem(0);
    gsPanel.add(comboBox);
    gsBtn = new JButton("Visualize(Greyscale)");
    gsBtn.setActionCommand(ActionCommands.GREYSCALEBTNCLICKED);
    gsPanel.add(gsBtn);
    mainPanel.add(gsPanel);

    JPanel brightenPanel = new JPanel(new FlowLayout());
    JPanel panel = new JPanel(new GridLayout(0, 1));
    brightenInput = new JTextField(10);
    JLabel brightenInputError = new JLabel("Invalid Input");
    brightenInputError.setForeground(Color.RED);
    panel.add(brightenInput);
    panel.add(brightenInputError);
    brightenBtn = new JButton("Brighten/Darken");
    brightenBtn.setActionCommand(ActionCommands.BRIGHTENBTNCLICKED);
    brightenBtn.setEnabled(false);
    brightenInput.getDocument().addDocumentListener(
            new BrightenInputValidator(brightenInput, brightenInputError, brightenBtn, this));
    brightenPanel.add(panel);
    brightenPanel.add(brightenBtn);
    brightenPanel.add(new JLabel("To darken, enter a negative value"));
    JPanel errIndicatorPanel = new JPanel();
    errIndicatorPanel.add(brightenInputError);
    mainPanel.add(brightenPanel);
    mainPanel.add(errIndicatorPanel);
  }

  @Override
  public void repaintImage(BufferedImage image) {
    imagePanel.removeAll();
    imagePanel.add(new JLabel(new ImageIcon(image)));
    imagePanel.revalidate();
  }

  @Override
  public void paintSplit(List<BufferedImage> images) {
    new RgbSplitDialog(this, features, images);
  }

  @Override
  public void repaintHistogram(int[][] bins) {
    histPanel.updateHistogram(bins);
  }

  @Override
  public void addFeatures(UIFeatures features) {
    this.features = features;
  }

  @Override
  public void addActionListener(ActionListener e) {
    for (JButton btn : nonManipulationButtons) {
      btn.addActionListener(e);
    }
    for (JButton btn : manipulationButtons) {
      btn.addActionListener(e);
    }
    gsBtn.addActionListener(e);
    brightenBtn.addActionListener(e);
  }

  @Override
  public GreyscaleType getSelectedGSType() {
    return (GreyscaleType) comboBox.getSelectedItem();
  }


  @Override
  public int getBrightenValue() {
    return Integer.parseInt(brightenInput.getText());
  }

  @Override
  public void showRgbCombineDialog() {
    new RgbCombineDialog(this, features);
  }

  @Override
  public void showErrorDialog(String errorMessage) {
    JOptionPane.showMessageDialog(null,
            errorMessage,
            "Error",
            JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void showOperationSuccessDialog(String title, String message) {
    JOptionPane.showMessageDialog(null,
            message,
            title,
            JOptionPane.INFORMATION_MESSAGE);
  }
}
