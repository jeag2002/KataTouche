package com.kata.listeners.input.test;

import static org.junit.Assert.assertNotEquals;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.kata.exceptions.InitListenerException;
import com.kata.listeners.input.FileInputListener;

/** FileInputListenerInitTest. */
public class FileInputListenerInitTest {
	
	/** FileInputListener. */
	private FileInputListener iListener;
	
	/** initEngine. */
	@Before
	public void initEngine() {
		iListener = new FileInputListener();
	}
	
	/** FileInputListenerLoadFileSuccessfully. */
	@Test
	public void fileInputListenerLoadFileSuccessfully() {
		
		String index = "i1";
		Properties property = new Properties();
		property.put("i1.type","FILE");
		property.put("i1.file","input.txt");
		property.put("i1.header","false");
		property.put("i1.output","o1");
		
		iListener.createListener(index, property);
		iListener.init();
		
		assertNotEquals(Whitebox.getInternalState(iListener, "reader"), null);
	}
	
	/** FileInputListenerLoadFilePathNotFound. */
	@Test(expected = InitListenerException.class)
	public void fileInputListenerLoadFilePathNotFound() {
		
		String index = "i1";
		Properties property = new Properties();
		property.put("i1.type","FILE");
		property.put("i1.file","test.txt");
		property.put("i1.header","false");
		property.put("i1.output","o1");
		
		iListener.createListener(index, property);
		iListener.init();
	}
	
	/** FileInputListenerLoadFilePathNull. */
	@Test(expected = InitListenerException.class)
	public void fileInputListenerLoadFilePathNull() {
		
		String index = "i1";
		Properties property = new Properties();
		property.put("i1.type","FILE");
		property.put("i1.header","false");
		property.put("i1.output","o1");
		
		iListener.createListener(index, property);
		iListener.init();
	}
	
	
	
	

}
