package com.kata.listeners.output.test;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.kata.exceptions.InitListenerException;
import com.kata.listeners.output.MailOutputListener;

/** MailOutputListenerInitTest.class. */
public class MailOutputListenerInitTest {
	
	/** Mail output listener. */
	private MailOutputListener outputListener;
	
	/** initEngine. */
	@Before
	public void initEngine() {
		outputListener = new MailOutputListener();
	}
	
	/** mailOutputListenerInitSuccessfully. */
	@Test
	public void mailOutputListenerInitSuccessfully() {
		
		String index = "o1";
		Properties property = new Properties();
		property.put("o1.type","MAIL");
		property.put("o1.host","smtp.mailtrap.io");
		property.put("o1.port","25");
		property.put("o1.from","robot@foobar.com");
		property.put("o1.subject","Happy birthday");
		property.put("o1.message","Happy birthday, dear {0}!");
		
		outputListener.createListener(index, property);
		outputListener.init();
		
	}
	
	@Test(expected = InitListenerException.class)
	public void mailOutputListenerNoSMTPServer() {
		
		String index = "o1";
		Properties property = new Properties();
		property.put("o1.type","MAIL");
		property.put("o1.port","25");
		property.put("o1.from","robot@foobar.com");
		property.put("o1.subject","Happy birthday");
		property.put("o1.message","Happy birthday, dear {0}!");
		
		outputListener.createListener(index, property);
		outputListener.init();
		
	}
	
	@Test(expected = InitListenerException.class)
	public void mailOutputListenerIncorrectSMTPServer() {
		
		String index = "o1";
		Properties property = new Properties();
		property.put("o1.type","MAIL");
		property.put("o1.port","25");
		property.put("o1.host", 25);
		property.put("o1.from","robot@foobar.com");
		property.put("o1.subject","Happy birthday");
		property.put("o1.message","Happy birthday, dear {0}!");
		
		outputListener.createListener(index, property);
		outputListener.init();
		
		
	}

}
