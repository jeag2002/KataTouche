package com.kata.utils;

/**
 * StringUtils.class
 */
public class StringUtils {

	/** separated values. */
	private static final String comma = ",";

	/**
	 * Obtain data from parameters.
	 * 
	 * @param inputData  inputData
	 * @param inputToken token assigned
	 * @return value
	 */
	public static String obtainDataFromParameter(String inputData, String inputToken) {
		
		String returnValue = "";
		
		if (isEmpty(inputData) || isEmpty(inputToken)) {
			returnValue = "";
		} else if (inputData.length() < inputToken.length()) {
			returnValue = "";
		} else {
			returnValue = inputData.substring(inputToken.length());
		}
		return returnValue;
	}

	/**
	 * Separate a list of parameters in array of strings.
	 * 
	 * @param inputData inputData as string
	 * @return list of strings
	 */
	public static String[] splitData(String inputData) {

		String[] listData = null;

		if (isEmpty(inputData)) {
			listData = new String[] {};
		} else {
			listData = inputData.split(comma);
		}

		return listData;
	}

	/**
	 * Check status inputArray.
	 * 
	 * @param inputArray list of Strings[]
	 * @return list inputArray.
	 */
	public static String[] checkStringArray(String[] inputArray) {

		if (inputArray == null) {
			inputArray = new String[] {};
		}

		return inputArray;
	}

	/**
	 * Evaluate if inputData is empty or not.
	 * 
	 * @param inputData
	 * @return true if is empty,false in other case
	 */
	public static boolean isEmpty(String inputData) {

		boolean isEmpty = false;

		if (inputData == null) {
			isEmpty = true;
		} else if (inputData.isEmpty()) {
			isEmpty = true;
		} else if (inputData.trim().isEmpty()) {
			isEmpty = true;
		} else if (inputData.trim().equals("")) {
			isEmpty = true;
		} else {
			isEmpty = false;
		}

		return isEmpty;
	}

}
