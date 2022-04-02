package com.kata.engine.test;

import org.junit.Before;
import org.junit.Test;

import com.kata.engine.Engine;
import com.kata.engine.beans.EngineInputBean;
import com.kata.exceptions.InitListenerException;

/** EngineInitTest class. */
public class EngineInitTest {
	
	/** Engine library. */
	private Engine engine;
	
	/** init. */
	@Before
	public void init() {
		engine = new Engine();
	}
	
	/** listenerSuccessfully. */
	@Test
	public void iniListenersSuccessfully() {
		
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "props.properties";
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = new String[] {"i1"};
		inputBean.outputListeners = new String[] {"o1"};
		engine.digesterListener(inputBean);
		engine.init();
	}
	
	/** UnexpectedInputBadData. */
	@Test(expected=InitListenerException.class)
	public void iniListenersPropertyFileUnexpectedInputBadData() {
		
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "test_bad.properties";
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = new String[] {"i1"};
		inputBean.outputListeners = new String[] {"o1"};
		engine.digesterListener(inputBean);
		engine.init();
	}
	
	/** UnexpectedOutputBadData. */
	@Test(expected=InitListenerException.class)
	public void iniListenersPropertyFileUnexpectedOutputBadData() {
		
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "test_bad_1.properties";
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = new String[] {"i1"};
		inputBean.outputListeners = new String[] {"o1"};
		engine.digesterListener(inputBean);
		engine.init();
	}
}
