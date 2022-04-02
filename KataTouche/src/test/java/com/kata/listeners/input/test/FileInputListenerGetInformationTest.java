package com.kata.listeners.input.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import com.kata.exceptions.BusinessListenerException;
import com.kata.listeners.beans.InputBean;
import com.kata.listeners.input.FileInputListener;

/** FileInputListenerGetInformationTest. */
public class FileInputListenerGetInformationTest {
	
	/** ZERO. */
	private static final int ZERO = 0;
	
	/** ONE. */
	private static final int ONE = 1;
	
	
	/** FileInputListener. */
	@InjectMocks
	private FileInputListener iListener;
	
	/** Init engine. */
	@Before
	public void initEngine() {
		MockitoAnnotations.initMocks(this);
	}
	
	/** processGetInformationSuccessfully. */
	@Test
	public void processGetInformationSuccessfully() {
		
		String inputData = "last_name, first_name, date_of_birth, email\r\nDoe, John, 1982/10/08, john.doe@foobar.com";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", true);
		Whitebox.setInternalState(iListener, "reader", targetReader);
		
		String currentDate = "2022/10/08";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
		assertNotNull(beanList);
		assertEquals(beanList.size(),ONE);
		
		assertEquals(beanList.get(ZERO).name,"John");
		assertEquals(beanList.get(ZERO).surname,"Doe");
		assertEquals(beanList.get(ZERO).date,"1982/10/08");
		
	}
	
	/** processGetInformationSuccessfullyButNotMatch. */
	
	@Test
	public void processGetInformationSuccessfullyButNotMatch() {
		
		String inputData = "last_name, first_name, date_of_birth, email\r\nDoe, John, 1982/10/08, john.doe@foobar.com";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", true);
		Whitebox.setInternalState(iListener, "reader", targetReader);
		
		String currentDate = "2022/01/01";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
		assertNotNull(beanList);
		assertEquals(beanList.size(),ZERO);		
	}
	
	
	/** processGetInformationReaderNull. */
	@Test(expected = BusinessListenerException.class) 
	public void processGetInformationReaderNull() {
		String inputData = "";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", true);
		Whitebox.setInternalState(iListener, "reader", null);
		
		String currentDate = "2022/01/01";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
	}
	
	
	/** processGetInformationEmptyFile. */
	@Test(expected = BusinessListenerException.class) 
	public void processGetInformationEmptyFile() {
		String inputData = "";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", true);
		Whitebox.setInternalState(iListener, "reader", targetReader);
		
		String currentDate = "2022/01/01";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
	}
	
	/** processGetInformationNoHeader. */
	@Test 
	public void processGetInformationNoHeader() {
		String inputData = "Doe, John, 1982/10/08, john.doe@foobar.com";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", false);
		Whitebox.setInternalState(iListener, "reader", targetReader);
		
		String currentDate = "1982/10/08";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
		assertNotNull(beanList);
		assertEquals(beanList.size(),ONE);
		
		assertEquals(beanList.get(ZERO).name,"John");
		assertEquals(beanList.get(ZERO).surname,"Doe");
		assertEquals(beanList.get(ZERO).date,"1982/10/08");
		
	}
	
	/** processGetInformationOnlyHeader. */
	@Test 
	public void processGetInformationOnlyHeader() {
		String inputData = "last_name, first_name, date_of_birth, email";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", true);
		Whitebox.setInternalState(iListener, "reader", targetReader);
		
		String currentDate = "1982/10/08";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
		assertNotNull(beanList);
		assertEquals(beanList.size(),ZERO);
	
	}
	
	/** processGetInformationUnexpectedHeader. */
	@Test 
	public void processGetInformationUnexpectedHeader() {
		String inputData = "last_name, first_name, date_of_birth, email";
		
		byte[] initialArray = inputData.getBytes();
	    Reader targetReader = new StringReader(new String(initialArray));
		
	    Whitebox.setInternalState(iListener, "headerData", false);
		Whitebox.setInternalState(iListener, "reader", targetReader);
		
		String currentDate = "1982/10/08";
		
		List<InputBean> beanList = iListener.getInformation(currentDate);
		
		assertNotNull(beanList);
		assertEquals(beanList.size(),ZERO);
	
	}
	
	

}
