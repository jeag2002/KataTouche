package com.kata.listeners.input.test;

import static org.junit.Assert.assertNotEquals;

import com.kata.exceptions.InitListenerException;
import com.kata.listeners.input.FileInputListener;
import java.util.Properties;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;



/** FileInputListenerInitTest. */
public class FileInputListenerInitTest {
  /** FileInputListener. */
  private FileInputListener listener;

  /** initEngine. */
  @Before
  public void initEngine() {
    listener = new FileInputListener();
  }

  /** FileInputListenerLoadFileSuccessfully. */
  @Test
  public void fileInputListenerLoadFileSuccessfully() {
    final String index = "i1";
    Properties property = new Properties();
    property.put("i1.type", "FILE");
    property.put("i1.file", "input.txt");
    property.put("i1.header", "false");
    property.put("i1.output", "o1");
    listener.createListener(index, property);
    listener.init();
    assertNotEquals(Whitebox.getInternalState(listener, "reader"), null);
  }


  /** FileInputListenerLoadFilePathNotFound. */
  @Test(expected = InitListenerException.class)
  public void fileInputListenerLoadFilePathNotFound() {
    final String index = "i1";
    Properties property = new Properties();
    property.put("i1.type", "FILE");
    property.put("i1.file", "test.txt");
    property.put("i1.header", "false");
    property.put("i1.output", "o1");
    listener.createListener(index, property);
    listener.init();
  }

  /** FileInputListenerLoadFilePathNull. */
  @Test(expected = InitListenerException.class)
  public void fileInputListenerLoadFilePathNull() {
    final String index = "i1";
    Properties property = new Properties();
    property.put("i1.type", "FILE");
    property.put("i1.header", "false");
    property.put("i1.output", "o1");
    listener.createListener(index, property);
    listener.init();
  }

}
