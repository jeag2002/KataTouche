package com.kata.engine.test;


import com.kata.engine.Engine;
import com.kata.engine.beans.EngineInputBean;
import org.junit.Before;
import org.junit.Test;

/** class EngineRun. */
public class EngineRunTest {
  /** Engine library. */
  private Engine engine;

  /** init. */
  @Before
  public void init() {
    engine = new Engine();
  }

  /** engineRunSuccessfully. */
  @Test
  public void engineRunSuccessfully() {
    EngineInputBean inputBean = new EngineInputBean();
    inputBean.pathToPropertyFile = "props.properties";
    inputBean.date = "2022/10/08";
    inputBean.inputListeners = new String[] {"i1"};
    inputBean.outputListeners = new String[] {"o1"};
    
    engine.digesterListener(inputBean);
    engine.init();
    engine.run();
  }
}
