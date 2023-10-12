package main.ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;

import javax.swing.JPanel;


/**
 * A class to represent the histogram panel.
 */
public class HistogramPanel extends JPanel {
  private final int SIZE = 256;

  int[][] colourBins;

  /**
   * A constructor to initialise the frequency array.
   *
   * @param colourBins frequency array.
   */
  public HistogramPanel(int[][] colourBins) {
    if (colourBins != null) {
      this.colourBins = colourBins;
    } else {
      this.colourBins = new int[4][SIZE];
    }
  }


  /**
   * A method to update the histogram by repainting it.
   *
   * @param colourBins new frequency array.
   */
  public void updateHistogram(int[][] colourBins) {
    this.colourBins = colourBins;
    repaint();
    revalidate();
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    int height = getHeight();
    int width = getWidth();

    Graphics2D g2 = (Graphics2D) g;

    g2.setColor(Color.white);
    g2.fillRect(0, 0, width, height);

    g2.setStroke(new BasicStroke(2));

    g2.setColor(Color.black);

    int maxY = 0;

    for (int i = 0; i < colourBins.length; i++) {
      for (int j = 0; j < colourBins[0].length; j++) {
        if (maxY < colourBins[i][j]) {
          maxY = colourBins[i][j];
        }
      }
    }

    int xInterval = (int) ((double) width / ((double) SIZE + 1));
    //int xInterval = 30;
    int yInterval = (int) ((double) maxY / ((double) 10));

    g2.drawLine(20, 0, 20, height - 20); //y-axis
    g2.drawLine(20, height - 20, getWidth(), height - 20); //x-axis

    g.setFont(new Font("TimesRoman", Font.PLAIN, 10));
    for (int i = 1; i < (SIZE + 10) / 10; i++) {
      g2.drawString(String.valueOf(i * 10), 20 + (30 * i), height - 10);
    }
    int interval = height / 10;
    for (int j = 11; j > 0; j--) {
      g2.drawString(String.valueOf(j * yInterval), 2, height - (j * interval));
    }

    g2.drawString("X-axis : Values", width - 200, 50);
    g2.drawString("Y-axis : Frequencies", width - 200, 70);
    g2.drawString("Red,green,blue line represent respective component", width - 200, 90);
    g2.drawString("Yellow line represents intensity", width - 200, 110);
    for (int i = 0; i < 4; i++) {

      // Set the graph
      if (i == 0) {
        g2.setColor(Color.red);
      } else if (i == 1) {
        g2.setColor(Color.GREEN);
      } else if (i == 2) {
        g2.setColor(Color.blue);
      } else if (i == 3) {
        g2.setColor(Color.yellow);
      }

      // draw the graph for the specific colour.
      for (int j = 0; j < SIZE - 2; j++) {
        int value = (int) (((double) colourBins[i][j] / (double) maxY) * height);
        int value2 = (int) (((double) colourBins[i][j + 1] / (double) maxY) * height);

        g2.drawLine((j * xInterval) + 20,
                height - value - 20,
                ((j + 1) * xInterval) + 20,
                height - value2 - 20);
      }
    }

  }

}
