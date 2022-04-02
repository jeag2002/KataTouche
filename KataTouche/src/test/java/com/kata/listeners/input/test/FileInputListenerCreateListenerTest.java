package com.kata.listeners.input.test;

import static org.junit.Assert.assertEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.input.FileInputListener;

/** FileInputListenerCreateListenerTest.class. */
public class FileInputListenerCreateListenerTest {

	/** FileInputListener. */
	private FileInputListener iListener;
	
	/**
	 * initEngine.
	 */
	@Before
	public void initEngine() {
		iListener = new FileInputListener();
	}
	
	/**
	 * LoadPropertyFileDataSuccessfully.
	 */
	@Test
	public void loadPropertyFileDataSuccessfully() {
		
		String index = "i1";
		Properties property = new Properties();
		property.put("i1.type","FILE");
		property.put("i1.file","text.txt");
		property.put("i1.header","false");
		property.put("i1.output","o1");
		
		iListener.createListener(index, property);
		
		assertEquals(iListener.getId(), index);
		assertEquals(iListener.getOutputChannels(),new String[] {"o1"});
	}
	
	/**
	 * loadPropertyFileDataIncorrectIndex.
	 */
	@Test
	public void loadPropertyFileDataIncorrectIndex() {
		String index = "i2";
		
		Properties property = new Properties();
		property.put("i1.type","FILE");
		property.put("i1.file","text.txt");
		property.put("i1.header","false");
		property.put("i1.output","o1");
		
		iListener.createListener(index, property);
		
		assertEquals(iListener.getId(), index);
		assertEquals(iListener.getOutputChannels(),new String[] {});		
	}
	
	
	/**
	 * loadPropertyFileDataIncorrectIndex.
	 */
	@Test
	public void loadPropertyFileDataIncorrectData() {
		String index = "i1";
		
		Properties property = new Properties();
		property.put("i1.type", 25);
		property.put("i1.file", 25);
		property.put("i1.header", 25);
		property.put("i1.output", 25);
		
		iListener.createListener(index, property);
		
		assertEquals(iListener.getId(), index);
		assertEquals(iListener.getOutputChannels(),new String[] {});		
	}
	
	
	
	/**
	 * loadPropertyFileDataNullIndex.
	 */
	@Test
	public void loadPropertyFileDataNullIndex() {
		String index = null;
		
		Properties property = new Properties();
		property.put("i1.type","FILE");
		property.put("i1.file","text.txt");
		property.put("i1.header","false");
		property.put("i1.output","o1");
		
		iListener.createListener(index, property);
		
		assertEquals(iListener.getId(), index);
		assertEquals(iListener.getOutputChannels(),new String[] {});		
	}
	
	/**
	 * loadPropertyFileDataEmptyProperties.
	 */
	@Test 
	public void loadPropertyFileDataEmptyProperties() {
		
		String index = "i1";
		Properties property = new Properties();
		
		iListener.createListener(index, property);
		
		assertEquals(iListener.getId(), index);
		assertEquals(iListener.getOutputChannels(),new String[] {});
	}
	
	/**
	 * loadPropertyFileDataNullProperties.
	 */	
	@Test(expected = InvalidPropertyFileException.class) 
	public void loadPropertyFileDataNullProperties() {
		
		String index = "i1";
		Properties property = null;
		
		iListener.createListener(index, property);
		
	}
	
	
	
	
}
