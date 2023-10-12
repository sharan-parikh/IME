package main.ui;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * This class is an implementation of DocumentListener used for the input for the "brighten" input
 * in the GUI.
 */
public class BrightenInputValidator implements DocumentListener {

  private final JLabel errorLabel;
  private final JTextField input;
  private final JFrame parent;
  private final JButton btn;

  private Pattern pattern;

  /**
   * Constructs the BrightenInputValidator object.
   *
   * @param input      the input field reference.
   * @param errorLabel the label to show in case the input entered is wrong.
   * @param btn        the button user will click.
   * @param parent     the parent container in which the button will reside.
   */
  public BrightenInputValidator(JTextField input, JLabel errorLabel, JButton btn, JFrame parent) {
    this.input = input;
    this.errorLabel = errorLabel;
    this.parent = parent;
    this.btn = btn;
    pattern = Pattern.compile("\\-?[1-9]{1}[0-9]*");
  }

  private void validateInput() {
    Matcher m = pattern.matcher(input.getText());
    if (m.matches()) {
      errorLabel.setForeground(parent.getBackground());
      btn.setEnabled(true);
    } else {
      errorLabel.setForeground(Color.RED);
      btn.setEnabled(false);
    }
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    validateInput();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    validateInput();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // not needed.
  }
}
