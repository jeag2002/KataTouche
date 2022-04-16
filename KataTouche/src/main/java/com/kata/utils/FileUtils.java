package com.kata.utils;

import com.kata.exceptions.InvalidInputStreamException;
import java.io.InputStream;

/**
 * FileUtils.class
 */
public class FileUtils {

  /**
  * Load property from jar.

  * @param filePath filepath
  * @return InputStream stream
  */
  public static InputStream getDefaultFileAsResource(String filePath) 
      throws InvalidInputStreamException {
    InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
    if (stream == null) {
      throw new
           InvalidInputStreamException("something happened while trying to load file " + filePath);
    }
    return stream;
  }

}
