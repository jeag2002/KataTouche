package com.kata.engine.test;

import org.junit.Before;
import org.junit.Test;

import com.kata.engine.Engine;
import com.kata.engine.beans.EngineInputBean;
import com.kata.exceptions.InputBeanNotValidException;
import com.kata.exceptions.InvalidDateException;
import com.kata.exceptions.InvalidPropertyFileException;

/** EngineTest class. */
public class EngineDigesterListenerTest {
	
	private Engine engine;
	
	/** init. */
	@Before
	public void init() {
		engine = new Engine();
	}
	
	/** digesterListenerSuccesFull. */
	@Test
	public void digesterListenerSuccessFull() {	
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "props.properties";
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = new String[] {"i1"};
		inputBean.outputListeners = new String[] {"o1"};	
		engine.digesterListener(inputBean);	
	}
	
	/** digesterListenerInputBeanNull. */
	@Test(expected = InputBeanNotValidException.class)
	public void digesterListenerInputBeanNull() {
		EngineInputBean inputBean = null;
		engine.digesterListener(inputBean);
	}
	
	/** digesterListenerPropNotFound. */
	@Test(expected = InvalidPropertyFileException.class)
	public void digesterListenerPropNotFound() {
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "test.properties";
		engine.digesterListener(inputBean);
	}
	
	/** digesterListenerPropNull. */
	@Test
	public void digesterListenerPropNull() {
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = null;
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = new String[] {"i1"};
		inputBean.outputListeners = new String[] {"o1"};	
		engine.digesterListener(inputBean);
	}

	/** digesterListenerPropNull. */
	@Test
	public void digesterListenerDateNull() {
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "props.properties";
		inputBean.date = null;
		inputBean.inputListeners = new String[] {"i1"};
		inputBean.outputListeners = new String[] {"o1"};	
		engine.digesterListener(inputBean);
	}
	
	/** digesterDateMalformatted. */
	@Test(expected = InvalidDateException.class)
	public void digesterListenerDateMalformatted() {
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "props.properties";
		inputBean.date = "xxxxxxxxxxxxxx";
		engine.digesterListener(inputBean);
	}
	
	/** digesterEmptyActiveListeners. */
	@Test
	public void digesterListenerEmptyActiveListeners() {
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "props.properties";
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = new String[] {};
		inputBean.outputListeners = new String[] {};
		engine.digesterListener(inputBean);
	}
	
	/** digesterNullActiveListeners. */
	@Test
	public void digesterNullActiveListeners() {
		EngineInputBean inputBean = new EngineInputBean();
		inputBean.pathToPropertyFile = "props.properties";
		inputBean.date = "2022/10/08";
		inputBean.inputListeners = null;
		inputBean.outputListeners = null;
		engine.digesterListener(inputBean);
	}
	


	

	
		
		
	

}
