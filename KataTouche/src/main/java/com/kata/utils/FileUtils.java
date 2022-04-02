package com.kata.utils;

import java.io.InputStream;

import com.kata.exceptions.InvalidInputStreamException;

/**
 * FileUtils.class
 */
public class FileUtils {

	/**
	 * Load property from jar
	 * 
	 * @param filePath
	 * @return InputStream
	 */
	public static InputStream getDefaultFileAsResource(String filePath) throws InvalidInputStreamException {
		InputStream stream = FileUtils.class.getClassLoader().getResourceAsStream(filePath);
		if (stream == null) {
			throw new InvalidInputStreamException("something happened while trying to load file " + filePath);
		}
		return stream;
	}

}
