package com.kata.listeners.input;


import com.kata.exceptions.BusinessListenerException;
import com.kata.exceptions.InitListenerException;
import com.kata.exceptions.InvalidInputStreamException;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.InputListenerInterface;
import com.kata.listeners.beans.InputBean;
import com.kata.listeners.beans.InputBeanEmail;
import com.kata.listeners.input.validations.FileInputListenerValidation;
import com.kata.utils.FileUtils;
import com.kata.utils.StringUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * FileInputListener.class.
 */
public class FileInputListener implements InputListenerInterface {

  /** file. */
  private static final String FILE = "file";
  /** output. */
  private static final String OUTPUT = "output";
  /** header. */
  private static final String HEADER = "header";

  /** ZERO. */
  private static final int ZERO = 0;
  /** ONE. */
  private static final int ONE = 1;
  /** TWO. */
  private static final int TWO = 2;
  /** THREE. */
  private static final int THREE = 3;

  /** index. */
  private String index;

  /** fileinput. */
  private String fileInput;

  /** headerData. */
  private Boolean headerData;

  /** outputListener. */
  private String[] outputListener;

  /** reader. */
  private Reader reader;

  @Override
  public void createListener(String index, Properties property) 
         throws InvalidPropertyFileException {
    try {
      this.index = index;
      this.fileInput = property.getProperty(index + "." + FILE);
      this.headerData = Boolean.valueOf(property.getProperty(index + "." + HEADER));
      this.outputListener = StringUtils.splitData(property.getProperty(index + "." + OUTPUT));
    } catch (NullPointerException uex) {
      throw new InvalidPropertyFileException(
         "something happened while trying to process properties : " + uex.getMessage());
    }
  }

  @Override
  public List<InputBean> getInformation(String date) 
         throws BusinessListenerException {
    List<InputBean> response = new ArrayList<InputBean>();
    try {
      BufferedReader br = new BufferedReader(reader);
      if ((headerData) && (br.readLine() == null)) {
        throw new IOException(" input file " + fileInput + " has not expected header ");
      }

      for (String line; (line = br.readLine()) != null;) {
        String[] values = StringUtils.splitData(line);
        if (FileInputListenerValidation.validateLine(values, date)) {
          InputBeanEmail validData = new InputBeanEmail();
          validData.surname = values[ZERO].trim();
          validData.name = values[ONE].trim();
          validData.date = values[TWO].trim();
          validData.email = values[THREE].trim();
          System.out.println("is the birthday of " + validData.name + " " + validData.surname
                             + "!! Happy BirthDay! :) ");
          response.add((InputBean) validData);
        }
      }
      if (response.isEmpty()) {
        System.out.println("is the birthday of nobody :( ");
      }
      br.close();
      reader.close();
    } catch (NullPointerException nex) {
      throw new BusinessListenerException(
                "Something happened while processing input listener " + index + " : "
                + nex.getMessage());
    } catch (IOException ioex) {
      throw new BusinessListenerException(
                "Something happened while processing input listener " + index + " : " 
                + ioex.getMessage());
    }
    return response;
  }

  @Override
  public void init() throws InitListenerException {

    try {
      File fileInputData = new File(this.fileInput);
      if (fileInputData.exists()) {
        FileInputStream fis = new FileInputStream(fileInputData);
        reader = new InputStreamReader(fis);
      } else {
        InputStream is = FileUtils.getDefaultFileAsResource(this.fileInput);
        reader = new InputStreamReader(is);
      }
    } catch (InvalidInputStreamException iiseex) {
      throw new InitListenerException(
                "Something happened while initialize input listener " + index + " : "
                + iiseex.getMessage());
    } catch (NullPointerException nullex) {
      throw new InitListenerException(
                "Something happened while initialize input listener " + index + " : " 
                + nullex.getMessage());
    } catch (IOException ioex) {
      throw new InitListenerException(
                "Something happened while initialize input listener " + index + " : "
                + ioex.getMessage());
    }
  }

  @Override
  public String getId() {
    return index;
  }

  @Override
  public String[] getOutputChannels() {
    return this.outputListener;
  }

}
