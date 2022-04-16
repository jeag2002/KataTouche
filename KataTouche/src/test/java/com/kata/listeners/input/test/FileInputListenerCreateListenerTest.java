package com.kata.listeners.input.test;

import static org.junit.Assert.assertEquals;

import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.input.FileInputListener;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;

/** FileInputListenerCreateListenerTest.class. */
public class FileInputListenerCreateListenerTest {
  /** FileInputListener. */
  private FileInputListener listener;

  /**
  * initEngine.
  */
  @Before
  public void initEngine() {
    listener = new FileInputListener();
  }

  /**
  * LoadPropertyFileDataSuccessfully.
  */
  @Test
  public void loadPropertyFileDataSuccessfully() {
    final String index = "i1";
    Properties property = new Properties();
    property.put("i1.type", "FILE");
    property.put("i1.file", "text.txt");
    property.put("i1.header", "false");
    property.put("i1.output", "o1");
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
    assertEquals(listener.getOutputChannels(), new String[] {"o1"});
  }

  /**
  * loadPropertyFileDataIncorrectIndex.
  */
  @Test
  public void loadPropertyFileDataIncorrectIndex() {
    final String index = "i2";
    Properties property = new Properties();
    property.put("i1.type", "FILE");
    property.put("i1.file", "text.txt");
    property.put("i1.header", "false");
    property.put("i1.output", "o1");
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
    assertEquals(listener.getOutputChannels(), new String[] {});
  }

  /**
  * loadPropertyFileDataIncorrectIndex.
  */
  @Test
  public void loadPropertyFileDataIncorrectData() {
    final String index = "i1";
    Properties property = new Properties();
    property.put("i1.type", 25);
    property.put("i1.file", 25);
    property.put("i1.header", 25);
    property.put("i1.output", 25);
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
    assertEquals(listener.getOutputChannels(), new String[] {});
  }

  /**
  * loadPropertyFileDataNullIndex.
  */
  @Test
  public void loadPropertyFileDataNullIndex() {
    final String index = null;
    Properties property = new Properties();
    property.put("i1.type", "FILE");
    property.put("i1.file", "text.txt");
    property.put("i1.header", "false");
    property.put("i1.output", "o1");
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
    assertEquals(listener.getOutputChannels(), new String[] {});
  }

  /**
  * loadPropertyFileDataEmptyProperties.
  */
  @Test 
  public void loadPropertyFileDataEmptyProperties() {
    final String index = "i1";
    Properties property = new Properties();
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
    assertEquals(listener.getOutputChannels(), new String[] {});
  }

  /**
  * loadPropertyFileDataNullProperties.
  */
  @Test(expected = InvalidPropertyFileException.class) 
  public void loadPropertyFileDataNullProperties() {
    final String index = "i1";
    Properties property = null;
    listener.createListener(index, property);
  }
}
