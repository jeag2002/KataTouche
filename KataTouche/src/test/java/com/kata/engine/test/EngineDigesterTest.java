package com.kata.engine.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.kata.engine.EngineDigester;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.InputListenerInterface;
import com.kata.listeners.OutputListenerInterface;

/** EngineDigesterTest.class. */
public class EngineDigesterTest {
	
	/** ZERO. */
	private static final int ZERO = 0;
	/** ONE. */
	private static final int ONE = 1;
	
	/** EngineDigester. */
	private EngineDigester digester;
	
	/** Init Digester Engine. */
	@Before
	public void init() {
		digester = new EngineDigester();
	}
	
	
	/** ProcessInputDigestersSuccessfully class. */
	@Test 
	public void processInputDigestersSuccessfully() {
		
		Properties prop = new Properties();
		prop.put("i1.type","FILE");
		prop.put("i1.file","input.txt");
		prop.put("i1.header","true");
		prop.put("i1.output","o1");
		
		String[] activeInputs = new String[] {"i1"};
		
		List<InputListenerInterface> lists = digester.processInputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ONE);
		assertEquals(lists.get(ZERO).getId(),"i1");
	}
	
	/** ProcessOutputDigestersSuccessfully class. */
	@Test 
	public void processOutputDigestersSuccessfully() {
		
		Properties prop = new Properties();
		prop.put("o1.type","MAIL");
		prop.put("o1.host","smtp.mailtrap.io");
		prop.put("o1.port","25");
		prop.put("o1.from","robot@foobar.com");
		prop.put("o1.subject","Happy birthday");
		prop.put("o1.message","Happy birthday, dear {0}!");
		
		String[] activeInputs = new String[] {"o1"};
		
		List<OutputListenerInterface> lists = digester.processOutputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ONE);
		assertEquals(lists.get(ZERO).getId(),"o1");
	}
	
	/** ProcessInputDigestersNotFound class. */
	@Test 
	public void processInputDigestersNotFound() {
		
		Properties prop = new Properties();
		prop.put("i1.type","FILE");
		prop.put("i1.file","input.txt");
		prop.put("i1.header","true");
		prop.put("i1.output","o1");
		
		String[] activeInputs = new String[] {"i2"};
		
		List<InputListenerInterface> lists = digester.processInputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	
	/** ProcessOutputDigestersNotFound class. */
	@Test 
	public void processOutputDigestersNotFound() {
		
		Properties prop = new Properties();
		prop.put("o1.type","MAIL");
		prop.put("o1.host","smtp.mailtrap.io");
		prop.put("o1.port","25");
		prop.put("o1.from","robot@foobar.com");
		prop.put("o1.subject","Happy birthday");
		prop.put("o1.message","Happy birthday, dear {0}!");

		
		String[] activeInputs = new String[] {"o2"};
		
		List<OutputListenerInterface> lists = digester.processOutputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	
	/** ProcessInputDigestersTypeNotFound class. */ 
	@Test 
	public void processInputDigestersTypeNotFound() {
		
		Properties prop = new Properties();
		prop.put("i1.type","DB");		
		String[] activeInputs = new String[] {"i1"};
		
		List<InputListenerInterface> lists = digester.processInputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	/** ProcessOutputDigestersTypeNotFound class. */ 
	@Test 
	public void processOutputDigestersTypeNotFound() {
		
		Properties prop = new Properties();
		prop.put("o1.type","FACEBOOK");		
		String[] activeInputs = new String[] {"o1"};
		
		List<OutputListenerInterface> lists = digester.processOutputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	
	/** ProcessInputDigestersEmptyProperty class. */ 
	@Test 
	public void processInputDigestersEmptyProperty() {
		
		Properties prop = new Properties();
		String[] activeInputs = new String[] {"i1"};
		
		List<InputListenerInterface> lists = digester.processInputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	
	/** ProcessInputDigestersEmptyProperty class. */ 
	@Test 
	public void processOutputDigestersEmptyProperty() {
		
		Properties prop = new Properties();
		String[] activeInputs = new String[] {"o1"};
		
		List<OutputListenerInterface> lists = digester.processOutputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	/** ProcessInputDigestersNullProperty. */
	@Test(expected = InvalidPropertyFileException.class)
	public void processInputDigestersNullProperty() {
		Properties prop = null;
		String[] activeInputs = new String[] {"i1"};
		List<InputListenerInterface> lists = digester.processInputListeners(activeInputs, prop);
	}
	
	/** ProcessOutputDigestersNullProperty. */
	@Test(expected = InvalidPropertyFileException.class)
	public void processOutputDigestersNullProperty() {
		Properties prop = null;
		String[] activeInputs = new String[] {"o1"};
		List<OutputListenerInterface> lists = digester.processOutputListeners(activeInputs, prop);
	}
	
	/** ProcessInputDigesterNullActiveInputs. */
	@Test
	public void processInputDigestersNullActiveInputs() {
		
		Properties prop = new Properties();
		prop.put("i1.type","FILE");
		prop.put("i1.file","input.txt");
		prop.put("i1.header","true");
		prop.put("i1.output","o1");
		
		String[] activeInputs = null;
		List<InputListenerInterface> lists = digester.processInputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}
	
	/** ProcessOutputDigesterNullActiveInputs. */
	@Test
	public void processOutputDigestersNullActiveInputs() {
		
		Properties prop = new Properties();
		prop.put("o1.type","MAIL");
		prop.put("o1.host","smtp.mailtrap.io");
		prop.put("o1.port","25");
		prop.put("o1.from","robot@foobar.com");
		prop.put("o1.subject","Happy birthday");
		prop.put("o1.message","Happy birthday, dear {0}!");

		
		String[] activeInputs = null;
		List<OutputListenerInterface> lists = digester.processOutputListeners(activeInputs, prop);
		
		assertNotNull(lists);
		assertEquals(lists.size(),ZERO);
	}



}
