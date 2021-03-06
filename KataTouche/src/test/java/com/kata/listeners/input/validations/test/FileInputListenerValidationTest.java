package com.kata.listeners.input.validations.test;

import static org.junit.Assert.assertEquals;

import com.kata.listeners.input.validations.FileInputListenerValidation;
import org.junit.Test;

/** Test file input listener validation test .*/
public class FileInputListenerValidationTest {

  /** testFileInputValidationSuccessful. */
  @Test
  public void testFileInputValidationSuccessful() {
    final String[] values = {
      "Doe", "John", "1982/10/08", "john.doe@foobar.com"};
    final String date = "2022/10/08";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, true);
  }

  /** testFileInputValidationValidNotMatch. */
  @Test
  public void testFileInputValidationValidNotMatch() {
    final String[] values = {
      "Doe", "John", "1982/10/08", "john.doe@foobar.com"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationDateNoWellFormatted. */
  @Test
  public void testFileInputValidationDateNoWellFormatted() {
    final String[] values = {
      "Doe", "John", "1982/10/08", "john.doe@foobar.com"};
    final String date = "01/01/2022";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationInvalidDateFormatted. */
  @Test
  public void testFileInputValidationInvalidDateFormatted() {
    final String[] values = {
      "Doe", "John", "1982/10/08", "john.doe@foobar.com"};
    final String date = "0000/000";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationEmptyListFormatted. */
  @Test
  public void testFileInputValidationEmptyListFormatted() {
    final String[] values = {
        };
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationNotEnoughValues. */
  @Test
  public void testFileInputValidationNotEnoughValues() {
    final String[] values = {
      "Doe", "John", "1982/10/08"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** TestFileInputValidationUnexpectedInput. */
  @Test
  public void testFileInputValidationUnexpectedInput() {
    final String[] values = {
      "DoeJohn1982/10/08"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationTooMuchValues. */
  @Test
  public void testFileInputValidationTooMuchValues() {
    final String[] values = {
      "Doe", "John", "1982/10/08", "test@test.com", "Rue Percebe Avenue s/n"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }
  
  /** testFileInputValidationInvalidEmail. */
  @Test
  public void testFileInputValidationInvalidEmail() {
    final String[] values = {
      "Doe", "John", "1982/10/08", "test@test@com"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationRecordInvalidDate. */
  @Test
  public void testFileInputValidationRecordInvalidDate() {
    final String[] values = {
      "Doe", "John", "00000000", "test@test.com"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }

  /** testFileInputValidationRecordDateNoWellFormated. */
  @Test
  public void testFileInputValidationRecordDateNoWellFormated() {
    final String[] values = {
      "Doe", "John", "08/10/1982", "test@test.com"};
    final String date = "2022/01/01";
    boolean result = FileInputListenerValidation.validateLine(values, date);
    assertEquals(result, false);
  }
  
}
