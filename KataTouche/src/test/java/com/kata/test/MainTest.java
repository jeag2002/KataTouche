package com.kata.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.internal.util.reflection.Whitebox;

import com.kata.Main;
import com.kata.engine.beans.EngineInputBean;

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
		
		String args[] = {"-inputs=i1","-outputs=o1","-property=prop.properties","-date=2022/10/08"};
		main.process(args);
		
		EngineInputBean eIB = (EngineInputBean)Whitebox.getInternalState(main, "engineBean");
		
		assertEquals(new String[] {"i1"}, eIB.inputListeners);
		assertEquals(new String[] {"o1"}, eIB.outputListeners);
		assertEquals("2022/10/08", eIB.date);
		assertEquals("prop.properties", eIB.pathToPropertyFile);
	}
	
	/** testInputEmpty. */
	@Test
	public void testInputEmpty() {
		
		String args[] = {};
		main.process(args);
		
		EngineInputBean eIB = (EngineInputBean)Whitebox.getInternalState(main, "engineBean");
		
		assertEquals(null, eIB.inputListeners);
		assertEquals(null, eIB.outputListeners);
		assertEquals(null, eIB.date);
		assertEquals(null, eIB.pathToPropertyFile);
	}
	
	/** testInputParametersWithNoData. */
	@Test
	public void testInputParametersWithNoData() {
		
		String args[] = {"-inputs=","-outputs=","-property=","-date="};
		main.process(args);
		
		EngineInputBean eIB = (EngineInputBean)Whitebox.getInternalState(main, "engineBean");
		
		assertEquals(new String[] {}, eIB.inputListeners);
		assertEquals(new String[] {}, eIB.outputListeners);
		assertEquals("", eIB.date);
		assertEquals("", eIB.pathToPropertyFile);
	}

}
