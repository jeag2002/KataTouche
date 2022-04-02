package com.kata.listeners.input.validations.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.kata.listeners.input.validations.FileInputListenerValidation;

/** Test file input listener validation test .*/
public class FileInputListenerValidationTest {
	
	/** testFileInputValidationSuccessful. */
	@Test
	public void testFileInputValidationSuccessful() {
		
		String values[] = {"Doe", "John", "1982/10/08", "john.doe@foobar.com"};
		String date = "2022/10/08";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, true);
	}
	
	/** testFileInputValidationValidNotMatch. */
	@Test
	public void testFileInputValidationValidNotMatch() {
		String values[] = {"Doe", "John", "1982/10/08", "john.doe@foobar.com"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	
	/** testFileInputValidationDateNoWellFormatted. */
	@Test
	public void testFileInputValidationDateNoWellFormatted() {
		String values[] = {"Doe", "John", "1982/10/08", "john.doe@foobar.com"};
		String date = "01/01/2022";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationInvalidDateFormatted. */
	@Test
	public void testFileInputValidationInvalidDateFormatted() {
		String values[] = {"Doe", "John", "1982/10/08", "john.doe@foobar.com"};
		String date = "0000/000";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationEmptyListFormatted. */
	@Test
	public void testFileInputValidationEmptyListFormatted() {
		String values[] = {};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationNotEnoughValues. */
	@Test
	public void testFileInputValidationNotEnoughValues() {
		String values[] = {"Doe", "John", "1982/10/08"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** TestFileInputValidationUnexpectedInput. */
	@Test
	public void testFileInputValidationUnexpectedInput() {
		String values[] = {"DoeJohn1982/10/08"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationTooMuchValues. */
	@Test
	public void testFileInputValidationTooMuchValues() {
		String values[] = {"Doe", "John", "1982/10/08","test@test.com","Rue Percebe Avenue s/n"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationInvalidEmail. */
	@Test
	public void testFileInputValidationInvalidEmail() {
		String values[] = {"Doe", "John", "1982/10/08","test@test@com"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationRecordInvalidDate. */
	@Test
	public void testFileInputValidationRecordInvalidDate() {
		String values[] = {"Doe", "John", "00000000","test@test.com"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}
	
	/** testFileInputValidationRecordDateNoWellFormated. */
	@Test
	public void testFileInputValidationRecordDateNoWellFormated() {
		String values[] = {"Doe", "John", "08/10/1982","test@test.com"};
		String date = "2022/01/01";
		
		boolean result = FileInputListenerValidation.validateLine(values, date);
		assertEquals(result, false);
	}



}
