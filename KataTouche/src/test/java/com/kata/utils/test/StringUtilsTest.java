package com.kata.utils.test;

import static org.junit.Assert.assertEquals;

import com.kata.utils.StringUtils;
import org.junit.Test;


/** String utils test. */
public class StringUtilsTest {

  /** get substring. */
  @Test
  public void obtainDataFromParameterSuccessFully() {
    String input = "-inputs=o1,o2,o3";
    String token = "-inputs=";
    String expectedOutput = "o1,o2,o3";
    String output = StringUtils.obtainDataFromParameter(input, token);
    assertEquals(output, expectedOutput);
  }

  /** get substring from null parameters. */
  @Test
  public void obtainDataFromParametersNull() {
    String output = StringUtils.obtainDataFromParameter(null, null);
    assertEquals(output, "");
  }

  /** get substring from token larger than input. */
  @Test
  public void obtainDataFromParametersMalFormatted() {
    String input = "test";
    String token = "testtesttest";
    String output = StringUtils.obtainDataFromParameter(input, token);
    assertEquals(output, "");
  }

  /** split data successfully. */
  @Test
  public void getSplitDataSuccessfully() {
    String input = "o1,o2,o3";
    String[] output = StringUtils.splitData(input);
    assertEquals(output, new String[] {"o1", "o2", "o3"});
  }

  /** split data null. */
  @Test
  public void getSplitDataNull() {
    String[] output = StringUtils.splitData(null);
    assertEquals(output, new String[] {});
  }

  /** split data malformatted. */
  @Test
  public void getSplitDataMalformatted() {
    String input = "o1,o2;o3,o4";
    String[] output = StringUtils.splitData(input);
    assertEquals(output, new String[] {"o1", "o2;o3", "o4"});
  }

  /** check if ListString is null. */
  @Test
  public void checkListStringIsNull() {
    String[] values = null;
    String[] output = StringUtils.checkStringArray(values);
    assertEquals(output, new String[] {});
  }

  /** check if ListString is empty. */
  @Test
  public void checkListStringIsEmpty() {
    String[] values = 
          {};
    String[] output = StringUtils.checkStringArray(values);
    assertEquals(output, new String[] {});
  }

  /** check if ListString is not empty. */
  @Test
  public void checkListStringIsNotEmpty() {
    String[] values = new String[] {"o1", "o2"};
    String[] output = StringUtils.checkStringArray(values);
    assertEquals(output, new String[] {"o1", "o2"});
  }

  /** check if input string is empty. */
  @Test
  public void checkIfInputStringIsEmpty()  {
    String value = null;
    boolean res = StringUtils.isEmpty(value);
    assertEquals(res, true);

    value = "";
    res = StringUtils.isEmpty(value);
    assertEquals(res, true);

    value = " ";
    res = StringUtils.isEmpty(value);
    assertEquals(res, true);

    value = "test";
    res = StringUtils.isEmpty(value);
    assertEquals(res, false);
  }
}
