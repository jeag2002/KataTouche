package com.kata.listeners.output.test;

import static org.junit.Assert.assertEquals;

import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.output.MailOutputListener;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;



/** MailOutputListenerCreateListenerTest.class. */
public class MailOutputListenerCreateListenerTest {

  /** MailOutputListener. */
  private MailOutputListener listener;
  
  /**
  * initEngine.
  */
  @Before
  public void initEngine() {
    listener = new MailOutputListener();
  }
  
  /**
  * LoadPropertyMailDataSuccessfully.
  */
  @Test
  public void loadPropertyMailDataSuccessfully() {
    final String index = "o1";
    Properties property = new Properties();
    property.put("o1.type", "MAIL");
    property.put("o1.host", "smtp.mailtrap.io");
    property.put("o1.port", "25");
    property.put("o1.from", "Happy birthday");
    property.put("o1.message", "Happy birthday, dear {0}!");
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
  }

  /**
  * loadPropertyFileDataIncorrectIndex.
  */
  @Test
  public void loadPropertyFileDataIncorrectIndex() {
    final String index = "o2";
    Properties property = new Properties();
    property.put("o1.type", "MAIL");
    property.put("o1.host", "smtp.mailtrap.io");
    property.put("o1.port", "25");
    property.put("o1.from", "Happy birthday");
    property.put("o1.message", "Happy birthday, dear {0}!");
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
  }

  /**
  * loadPropertyFileDataIncorrectData.
  */
  @Test
  public void loadPropertyFileDataIncorrectData() {
    final String index = "o1";
    Properties property = new Properties();
    property.put("o1.type", 25);
    property.put("o1.host", 25);
    property.put("o1.port", 25);
    property.put("o1.from", 25);
    property.put("o1.message", 25);
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
  }

  /**
  * loadPropertyFileDataEmptyProperty.
  */
  @Test
  public void loadPropertyFileDataEmptyProperty() {
    final String index = "o1";
    Properties property = new Properties();
    listener.createListener(index, property);
    assertEquals(listener.getId(), index);
  }

  /**
  * loadPropertyFileDataNullProperties.
  */
  @Test(expected = InvalidPropertyFileException.class) 
  public void loadPropertyFileDataNullProperties() {
    final String index = "o1";
    Properties property = null;
    listener.createListener(index, property);
  }
}
