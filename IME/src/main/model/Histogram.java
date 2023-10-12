package main.model;


/**
 * A class to generate the data for a histogram from an image model object.
 */
public class Histogram {
  private final int SIZE = 256;
  // Red, Green, Blue
  private final int NUMBER_OF_COLOURS = 4;

  public final int RED = 0;
  public final int GREEN = 1;
  public final int BLUE = 2;
  public final int INTENSITY = 3;

  private final int[][] colourBins;


  /**
   * A constructor to initialize the value-frequency array.
   */
  public Histogram() {
    colourBins = new int[NUMBER_OF_COLOURS][];

    for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
      colourBins[i] = new int[SIZE];
    }

  }

  /**
   * A method that generates the value-frequency array.
   *
   * @param obj image model object.
   * @return array of value-frequency.
   */
  public int[][] freqVals(IMEImageModelPro obj) {


    // Reset all the bins
    for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
      for (int j = 0; j < SIZE; j++) {
        colourBins[i][j] = 0;
      }
    }

    for (int x = 0; x < obj.getWidth(); x++) {
      for (int y = 0; y < obj.getHeight(); y++) {
        RGBPixel c = obj.getPixels().get(y).get(x);

        int avg = (c.getRedComp() + c.getGreenComp() + c.getBlueComp()) / 3;
        colourBins[RED][c.getRedComp()]++;
        colourBins[GREEN][c.getGreenComp()]++;
        colourBins[BLUE][c.getBlueComp()]++;
        colourBins[INTENSITY][avg]++;
      }
    }

    int maxY = 0;

    for (int i = 0; i < NUMBER_OF_COLOURS; i++) {
      for (int j = 0; j < SIZE; j++) {
        if (maxY < colourBins[i][j]) {
          maxY = colourBins[i][j];
        }
      }
    }


    return colourBins;
  }

}