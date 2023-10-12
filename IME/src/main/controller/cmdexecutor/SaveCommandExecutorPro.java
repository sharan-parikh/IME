package main.controller.cmdexecutor;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import main.exceptions.CommandExecutionException;
import main.model.ImageFormat;
import main.model.ModelManagerPro;

/**
 * This command executor is responsible for saving the images and has an improvement over the
 * older SaveCommandExecutor as it can save images in more formats and not just ppm.
 */
public class SaveCommandExecutorPro extends AbstractSaveCommandExecutor
        implements CommandExecutor<ModelManagerPro> {

  @Override
  public void execute(String[] args, ModelManagerPro modelManager)
          throws CommandExecutionException {
    validateArgs(args);
    String filePath = args[0];
    try {
      OutputStream out = new FileOutputStream(filePath);
      String fileExtension = filePath.substring(filePath.lastIndexOf(".") + 1);
      switch (fileExtension) {
        case "ppm":
          modelManager.save(args[1], out, ImageFormat.PPM);
          break;
        case "bmp":
          modelManager.save(args[1], out, ImageFormat.BMP);
          break;
        case "png":
          modelManager.save(args[1], out, ImageFormat.PNG);
          break;
        case "jpg":
          modelManager.save(args[1], out, ImageFormat.JPG);
          break;
        default:
          throw new CommandExecutionException("Image format: " + fileExtension
                  + " is not supported.");
      }
    } catch (IOException ex) {
      throw new CommandExecutionException(ex.getMessage());
    }
  }
}
