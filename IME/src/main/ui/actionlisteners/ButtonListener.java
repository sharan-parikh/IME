package main.ui.actionlisteners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * An action listener class to handle button events.
 */
public class ButtonListener implements ActionListener {

  private final Map<String, Runnable> buttonClickedActions;

  /**
   * A constructor to initialize runnable map.
   *
   * @param buttonClickedActions String, Runnable map.
   */
  public ButtonListener(Map<String, Runnable> buttonClickedActions) {
    this.buttonClickedActions = buttonClickedActions;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
