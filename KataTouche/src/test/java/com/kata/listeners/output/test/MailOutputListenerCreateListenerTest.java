package com.kata.listeners.output.test;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.output.MailOutputListener;

/** MailOutputListenerCreateListenerTest.class. */
public class MailOutputListenerCreateListenerTest {
	

	/** MailOutputListener. */
	private MailOutputListener oListener;
	
	/**
	 * initEngine.
	 */
	@Before
	public void initEngine() {
		oListener = new MailOutputListener();
	}
	
	/**
	 * LoadPropertyMailDataSuccessfully.
	 */
	@Test
	public void loadPropertyMailDataSuccessfully() {
		
		String index = "o1";
		Properties property = new Properties();
		property.put("o1.type","MAIL");
		property.put("o1.host","smtp.mailtrap.io");
		property.put("o1.port","25");
		property.put("o1.from","Happy birthday");
		property.put("o1.message","Happy birthday, dear {0}!");
		
		oListener.createListener(index, property);
		assertEquals(oListener.getId(), index);
	}

	/**
	 * loadPropertyFileDataIncorrectIndex.
	 */
	@Test
	public void loadPropertyFileDataIncorrectIndex() {
		String index = "o2";
		
		Properties property = new Properties();
		property.put("o1.type","MAIL");
		property.put("o1.host","smtp.mailtrap.io");
		property.put("o1.port","25");
		property.put("o1.from","Happy birthday");
		property.put("o1.message","Happy birthday, dear {0}!");
		
		oListener.createListener(index, property);
		assertEquals(oListener.getId(), index);	
	}
	
	/**
	 * loadPropertyFileDataIncorrectData.
	 */
	@Test
	public void loadPropertyFileDataIncorrectData() {
		String index = "o1";
		
		Properties property = new Properties();
		property.put("o1.type", 25);
		property.put("o1.host", 25);
		property.put("o1.port", 25);
		property.put("o1.from", 25);
		property.put("o1.message", 25);
		
		oListener.createListener(index, property);
		assertEquals(oListener.getId(), index);	
	}
	
	/**
	 * loadPropertyFileDataEmptyProperty.
	 */
	@Test
	public void loadPropertyFileDataEmptyProperty() {
		String index = "o1";
		
		Properties property = new Properties();
		
		oListener.createListener(index, property);
		assertEquals(oListener.getId(), index);	
	}
	
	/**
	 * loadPropertyFileDataNullProperties.
	 */	
	@Test(expected = InvalidPropertyFileException.class) 
	public void loadPropertyFileDataNullProperties() {
		
		String index = "o1";
		Properties property = null;
		
		oListener.createListener(index, property);
		
	}
	
	

}
