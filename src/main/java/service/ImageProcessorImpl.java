package service;

import constant.Component;
import model.Image;
import model.PPMImage;
import model.RGBPixel;

public class ImageProcessorImpl implements ImageProcessor {

  @Override
  public Image verticalFlip(Image srcImage) {
    if (srcImage == null) {
      throw new IllegalArgumentException("Source image cannot be null for vertical flip operation.");
    }

    Image flippedImg = new PPMImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getMaxValue());

    // Iterate through each pixel and place it in the vertically mirrored position.
    for (int r = 0; r < srcImage.getHeight(); r++) {
      for (int c = 0; c < srcImage.getWidth(); c++) {
        RGBPixel originalPixel = srcImage.getPixel(r, c);
        flippedImg.setPixel(srcImage.getHeight() - 1 - r, c, new RGBPixel(
                originalPixel.getRedComp(), originalPixel.getGreenComp(), originalPixel.getBlueComp())
        );
      }
    }
    return flippedImg;
  }

  @Override
  public Image horizontalFlip(Image srcImage) {
    if (srcImage == null) {
      throw new IllegalArgumentException("Source image cannot be null for vertical flip operation.");
    }

    Image flippedImg = new PPMImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getMaxValue());

    for (int r = 0; r < srcImage.getHeight(); r++) {
      for (int c = 0; c < srcImage.getWidth(); c++) {
        RGBPixel originalPixel = srcImage.getPixel(r, c);
        flippedImg.setPixel(r, srcImage.getWidth() - 1 - c, new RGBPixel(
                originalPixel.getRedComp(), originalPixel.getGreenComp(), originalPixel.getBlueComp())
        );
      }
    }
    return flippedImg;
  }

  @Override
  public Image visualize(Image srcImage, Component component) {
    if (srcImage == null) {
      throw new IllegalArgumentException("Source image cannot be null for brighten operation.");
    }
    Image greyscaleImage = new PPMImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getMaxValue());

    // Iterate through each pixel of the source image.
    for (int r = 0; r < srcImage.getHeight(); r++) {
      for (int c = 0; c < srcImage.getWidth(); c++) {
        RGBPixel originalPixel = srcImage.getPixel(r, c);
        int greyscaleValue;

        // Determine the greyscale value based on the specified component type.
        switch (component) {
          case RED:
            greyscaleValue = originalPixel.getRedComp();
            break;
          case GREEN:
            greyscaleValue = originalPixel.getGreenComp();
            break;
          case BLUE:
            greyscaleValue = originalPixel.getBlueComp();
            break;
          case VALUE:
            greyscaleValue = originalPixel.getValue();
            break;
          case INTENSITY:
            greyscaleValue = originalPixel.getIntensity();
            break;
          case LUMA:
            greyscaleValue = originalPixel.getLuma();
            break;
          default:
            // This case should ideally not be reached if enum is fully covered.
            throw new IllegalArgumentException("Unknown greyscale component type: " + component);
        }
        // Create a new pixel where R, G, B are all set to the calculated greyscale value.
        greyscaleImage.setPixel(r, c, new RGBPixel(greyscaleValue, greyscaleValue, greyscaleValue));
      }
    }
    return greyscaleImage;
  }

  @Override
  public Image brighten(Image srcImage, int increment) {
    if (srcImage == null) {
      throw new IllegalArgumentException("Source image cannot be null for brighten operation.");
    }
    Image modified = srcImage.copy();
    for (int r = 0; r < srcImage.getWidth(); r++) {
      for (int c = 0; c < srcImage.getHeight(); c++) {
        RGBPixel pixel = srcImage.getPixel(r, c);
        modified.setPixel(r, c, new RGBPixel(
                pixel.getRedComp() + increment,
                pixel.getGreenComp() + increment,
                pixel.getBlueComp() + increment)
        );
      }
    }
    return modified;
  }

  @Override
  public Image[] rgbSplit(Image srcImage) {
    if (srcImage == null) {
      throw new IllegalArgumentException("Source image cannot be null for brighten operation.");
    }
    Image redComponentImage = new PPMImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getMaxValue());
    Image greenComponentImage = new PPMImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getMaxValue());
    Image blueComponentImage = new PPMImage(srcImage.getWidth(), srcImage.getHeight(), srcImage.getMaxValue());

    for (int r = 0; r < srcImage.getHeight(); r++) {
      for (int c = 0; c < srcImage.getWidth(); c++) {
        RGBPixel originalPixel = srcImage.getPixel(r, c);
        
        redComponentImage.setPixel(r, c, new RGBPixel(originalPixel.getRedComp(), originalPixel.getRedComp(), originalPixel.getRedComp()));
        greenComponentImage.setPixel(r, c, new RGBPixel(originalPixel.getGreenComp(), originalPixel.getGreenComp(), originalPixel.getGreenComp()));
        blueComponentImage.setPixel(r, c, new RGBPixel(originalPixel.getBlueComp(), originalPixel.getBlueComp(), originalPixel.getBlueComp()));
      }
    }
    return new Image[]{redComponentImage, greenComponentImage, blueComponentImage};
  }

  @Override
  public Image rgbCombine(Image redImage, Image greenImage, Image blueImage) {
    if (redImage == null || greenImage == null || blueImage == null) {
      throw new IllegalArgumentException("All three component images must be non-null for RGB combine operation.");
    }

    if (redImage.getWidth() != greenImage.getWidth() || redImage.getWidth() != blueImage.getWidth() ||
            redImage.getHeight() != greenImage.getHeight() || redImage.getHeight() != blueImage.getHeight()) {
      throw new IllegalArgumentException("All three component images must have the same dimensions to combine.");
    }

    // Create a new image for the combined color result.
    Image combinedImage = new PPMImage(redImage.getWidth(), redImage.getHeight(), redImage.getMaxValue());

    for (int r = 0; r < redImage.getHeight(); r++) {
      for (int c = 0; c < redImage.getWidth(); c++) {
        // Get the red, green, and blue components from their respective images.
        int red = redImage.getPixel(r, c).getRedComp();
        int green = greenImage.getPixel(r, c).getGreenComp();
        int blue = blueImage.getPixel(r, c).getBlueComp();

        // Create a new color pixel and set it in the combined image.
        combinedImage.setPixel(r, c, new RGBPixel(red, green, blue));
      }
    }
    return combinedImage;
  }
}
