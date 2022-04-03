package com.kata;

import com.kata.engine.Engine;
import com.kata.engine.beans.EngineInputBean;
import com.kata.exceptions.BusinessListenerException;
import com.kata.exceptions.InitListenerException;
import com.kata.exceptions.InputBeanNotValidException;
import com.kata.exceptions.InvalidDateException;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.utils.StringUtils;

/**
 * MAIN class executable.
 */
public final class Main {
    /** inputs. */
    private static final String INPUTS = "-inputs=";
    /** outputs. */
    private static final String OUTPUTS = "-outputs=";
    /** propertyFile. */
    private static final String PROPERTYFILE = "-property=";
    /** date. */
    private static final String DATE = "-date=";
    /** main. */
    private static Main main;
    /** engine. */
    private Engine engine;
    /** engineBean. */
    private EngineInputBean engineBean;
    
    /**
     * Main contructor.
     */
    public Main() {
        engine = new Engine();
        engineBean = new EngineInputBean();
    }
    
	/**
	 * process Input Parameters.
	 * @param args input params
	 */
	public void process(String[] args) {
		for (String arg : args) {
			if (arg.startsWith(INPUTS) ) {
				engineBean.inputListeners = StringUtils.splitData(StringUtils
						.obtainDataFromParameter(arg, INPUTS));
			} else if (arg.startsWith(OUTPUTS) ) {
				engineBean.outputListeners = StringUtils.splitData(StringUtils
						.obtainDataFromParameter(arg, OUTPUTS));
			} else if (arg.startsWith(PROPERTYFILE) ) {
				engineBean.pathToPropertyFile = StringUtils.obtainDataFromParameter(arg, PROPERTYFILE);
			} else if (arg.startsWith(DATE) ) {
				engineBean.date = StringUtils.obtainDataFromParameter(arg, DATE);
			}
		}
	}

	/**
	 * Configure and launch listeners.
	 * @return Main object
	 * @throws InputBeanNotValidException
	 * @throws InvalidPropertyFileException 
	 * @throws InvalidDateException 
	 */
	public void init() throws InputBeanNotValidException, InvalidPropertyFileException, InvalidDateException {
		engine.digesterListener(engineBean);
	}

	/**
	 * Launch Batch.
	 */
	public void run() throws InitListenerException, BusinessListenerException {
		engine.init();
		engine.run();
	}

	/**
	 * Method main
	 * @param args input Exceptions
	 * @throws InputBeanNotValidException error in inputBean
	 * @throws InvalidPropertyFileException error in property file
	 * @throws InvalidDateException error in Date
	 * @throws InitListenerException error processing initListener
	 * @throws BusinessListenerException internal error engine
	 */
	public static void main(String[] args) throws 
	                                       InputBeanNotValidException,
	                                       InvalidPropertyFileException, 
	                                       InvalidDateException, 
	                                       InitListenerException, 										  
	                                       BusinessListenerException {
		if (args.length < 4) {
			System.out.println("*** UNEXPECTED INPUT ****");
			System.out.println("EXPECTED -inputs=i1,i2,... -outputs=o1,o2,... -property=props.properties -date=YYYY/MM/DD (D=Day,M=Month,Y=Year)");
			System.out.println("Being:");
			System.out.println("-inputs: inputs listeners");
			System.out.println("-outputs: outputs listeners");
			System.out.println("-property: property file, empty if you want property file as default ");
			System.out.println("-date: YYYY/MM/DD (D=Day, M=Month, Y=Year), empty if you want today  ");
			System.out.println("************************");
		} else {
			Main main = new Main();
			main.process(args);
			main.init();
			main.run();
		}		
		System.exit(0);
	}
}
