package com.kata.listeners.input.validations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import com.kata.constants.GlobalConstants;

/**
 * FileInputListenerValidation class.
 */
public class FileInputListenerValidation {

	/** TWO. */
	private static final Integer TWO = 2;
	/** THREE. */
	private static final Integer THREE = 3;
	/** FOUR. */
	private static final Integer FOUR = 4;

	/**
	 * Validation of a line of the file.
	 * 
	 * @param values values of the line
	 * @param date   date
	 * @return true/false
	 */
	public static boolean validateLine(String[] values, String date) {

		return (values.length == FOUR) && validateDate(values[TWO], date) && // same day && month
				validateEmail(values[THREE]); // email
	}

	/**
	 * Validation email.
	 * 
	 * @param email input email
	 * @return true/false
	 */

	private static boolean validateEmail(String email) {
		boolean emailValidation = false;
		try {
			emailValidation = Pattern.compile(GlobalConstants.EMAIL_FORMAT).matcher(email).matches();
		} catch (PatternSyntaxException e) {
			emailValidation = false;
		}
		return emailValidation;
	}

	/**
	 * Validation date.
	 * 
	 * @param dateLine  date of the line
	 * @param dateInput date input
	 * @return true/false
	 */
	private static boolean validateDate(String dateLine, String dateInput) {

		DateTimeFormatter sDF = DateTimeFormatter.ofPattern(GlobalConstants.DATE_FORMAT);
		boolean dateValidation = false;

		try {

			LocalDate datLine = LocalDate.parse(dateLine.trim(), sDF);
			LocalDate datInput = LocalDate.parse(dateInput.trim(), sDF);

			if ((datLine.getDayOfMonth() == datInput.getDayOfMonth()) && (datLine.getMonth() == datInput.getMonth())) {
				dateValidation = true;
			}

		} catch (DateTimeParseException e) {
			dateValidation = false;
		}

		return dateValidation;
	}

}
