package main.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


/**
 * This is a utility class and contains helpful methods related to file operations.
 */
public final class FileUtil {

  /**
   * Reads a .ppm file and gets the text content.
   *
   * @param filePath the file path with the file name.
   * @return text content of the ppm file.
   * @throws FileNotFoundException this exception is thrown in case the file could not be located.
   */
  public static String readPPMFile(String filePath) throws FileNotFoundException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + filePath + " not found!");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s).append(System.lineSeparator());
      }
    }
    sc.close();
    return builder.toString();
  }

  /**
   * Writes the text content to a file.
   *
   * @param content  the text content that needs to be written.
   * @param filePath the file path where the file is located with the file name.
   * @throws IOException this exception is thrown in case a problem occurs during the file writing
   *                     process.
   */
  public static void writeToFile(String content,
                                 String filePath) throws IOException {
    FileWriter writer = new FileWriter(filePath, false);
    writer.write(content);
    writer.close();
  }

  /**
   * Reads the context as a String from a file.
   *
   * @param filePath the file path with the file name.
   * @return the file content as a String.
   * @throws IOException this exception is thrown in case the file is not present or when a problem
   *                     occurs during the file reading process.
   */
  public static String readFromFile(String filePath) throws IOException {
    Scanner sc;
    String text = "";
    try {
      sc = new Scanner(new FileInputStream(filePath));
    } catch (FileNotFoundException e) {
      throw new FileNotFoundException("File " + filePath + " not found!");
    }

    while (sc.hasNextLine()) {
      text = sc.nextLine();
    }

    return text;
  }

}
