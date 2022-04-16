package com.kata.test;

import static org.junit.Assert.assertEquals;

import com.kata.Main;
import com.kata.engine.beans.EngineInputBean;
import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;


/** Main test. */
public class MainTest {

  /** main object. */
  private Main main;
  
  /** init. */
  @Before
  public void init() {
    main = new Main();
  }
  
  /** testInputParametersSuccessfully. */
  @Test
  public void testInputParametersSuccessfully() {
    String[] args = 
      {"-inputs=i1", "-outputs=o1", "-property=prop.properties", "-date=2022/10/08"};
    main.process(args);
    EngineInputBean eib = (EngineInputBean) Whitebox.getInternalState(main, "engineBean");
    assertEquals(new String[] {"i1"}, eib.inputListeners);
    assertEquals(new String[] {"o1"}, eib.outputListeners);
    assertEquals("2022/10/08", eib.date);
    assertEquals("prop.properties", eib.pathToPropertyFile);
  }
    
  /** testInputEmpty. */
  @Test
  public void testInputEmpty() {
    String[] args = 
      {};
    main.process(args);
    EngineInputBean eib = (EngineInputBean) Whitebox.getInternalState(main, "engineBean");
    assertEquals(null, eib.inputListeners);
    assertEquals(null, eib.outputListeners);
    assertEquals(null, eib.date);
    assertEquals(null, eib.pathToPropertyFile);
  }
    
  /** testInputParametersWithNoData. */
  @Test
  public void testInputParametersWithNoData() {
    String[] args =
      {"-inputs=", "-outputs=", "-property=", "-date="};
    main.process(args);
    EngineInputBean eib = (EngineInputBean) Whitebox.getInternalState(main, "engineBean");
    assertEquals(new String[] {}, eib.inputListeners);
    assertEquals(new String[] {}, eib.outputListeners);
    assertEquals("", eib.date);
    assertEquals("", eib.pathToPropertyFile);
  }
}
