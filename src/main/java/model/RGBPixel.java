package model;

import lombok.Getter;

@Getter
public class RGBPixel {

  private int redComp;

  private int greenComp;

  private int blueComp;

  public RGBPixel(int redComp, int greenComp, int blueComp) {
    this.redComp = redComp;
    this.blueComp = blueComp;
    this.greenComp = greenComp;
  }
}
