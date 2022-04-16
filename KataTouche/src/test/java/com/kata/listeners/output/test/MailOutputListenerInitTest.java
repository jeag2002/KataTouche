package com.kata.listeners.output.test;

import com.kata.exceptions.InitListenerException;
import com.kata.listeners.output.MailOutputListener;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;



/** MailOutputListenerInitTest.class. */
public class MailOutputListenerInitTest {
  /** Mail output listener. */
  private MailOutputListener listener;

  /** initEngine. */
  @Before
  public void initEngine() {
    listener = new MailOutputListener();
  }
  
  /** mailOutputListenerInitSuccessfully. */
  @Test
  public void mailOutputListenerInitSuccessfully() {
    final String index = "o1";
    Properties property = new Properties();
    property.put("o1.type", "MAIL");
    property.put("o1.host", "smtp.mailtrap.io");
    property.put("o1.port", "25");
    property.put("o1.from", "robot@foobar.com");
    property.put("o1.subject", "Happy birthday");
    property.put("o1.message", "Happy birthday, dear {0}!");
    listener.createListener(index, property);
    listener.init();
  }

  @Test(expected = InitListenerException.class)
  public void mailOutputListenerNoSmtpServer() {
    final String index = "o1";
    Properties property = new Properties();
    property.put("o1.type", "MAIL");
    property.put("o1.port", "25");
    property.put("o1.from", "robot@foobar.com");
    property.put("o1.subject", "Happy birthday");
    property.put("o1.message", "Happy birthday, dear {0}!");
    listener.createListener(index, property);
    listener.init();
  }

  @Test(expected = InitListenerException.class)
  public void mailOutputListenerIncorrectSmtpServer() {
    final String index = "o1";
    Properties property = new Properties();
    property.put("o1.type", "MAIL");
    property.put("o1.port", "25");
    property.put("o1.host", 25);
    property.put("o1.from", "robot@foobar.com");
    property.put("o1.subject", "Happy birthday");
    property.put("o1.message", "Happy birthday, dear {0}!");
    listener.createListener(index, property);
    listener.init();
  }

}
