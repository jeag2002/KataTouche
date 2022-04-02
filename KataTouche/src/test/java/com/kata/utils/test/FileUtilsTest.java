package com.kata.utils.test;

import static org.junit.Assert.assertNotNull;

import java.io.InputStream;

import org.junit.Test;

import com.kata.exceptions.InvalidInputStreamException;
import com.kata.utils.FileUtils;

/** File Utils test.*/
public class FileUtilsTest {
	
	/** check if file can be loaded from resourceStream. */
	@Test
	public void checkFileIsFromResourceStreamSuccessfully() {
		String filePath = "input.txt";
		InputStream inputStream = FileUtils.getDefaultFileAsResource(filePath);
		assertNotNull(inputStream);
	}
	
	/** check if file can be loaded from resourceStream. */
	@Test(expected = InvalidInputStreamException.class)
	public void checkFileIsFromResourceStreamError() {
		String filePath = "fault.txt";
		InputStream inputStream = FileUtils.getDefaultFileAsResource(filePath);
	}
	

}
