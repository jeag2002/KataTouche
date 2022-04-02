package com.kata.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import com.kata.constants.GlobalConstants;
import com.kata.engine.beans.EngineInputBean;
import com.kata.exceptions.BusinessListenerException;
import com.kata.exceptions.InitListenerException;
import com.kata.exceptions.InputBeanNotValidException;
import com.kata.exceptions.InvalidDateException;
import com.kata.exceptions.InvalidInputStreamException;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.InputListenerInterface;
import com.kata.listeners.OutputListenerInterface;
import com.kata.listeners.beans.InputBean;
import com.kata.utils.FileUtils;
import com.kata.utils.StringUtils;

/**
 * Engine class. 
 */
public class Engine {
	
	/** default_property_file. */
	private static final String DEFAULT_PROPERTY_FILE = "props.properties";

	/** EngineDigester. */
	EngineDigester data;
	
	/** Input Listener Interface list. */
	List<InputListenerInterface> inputs;
	
	/** Output Listener Interface list. */
	List<OutputListenerInterface> outputs;
	
	/** Simple Date format. */
	SimpleDateFormat formatDate;
	
	/** Date. */
	String date;

	/** 
	 * Engine.
	 */
	public Engine() {
		data = new EngineDigester();
		formatDate = new SimpleDateFormat(GlobalConstants.DATE_FORMAT);
	}
	
	/**
	 * Process listener. 
	 * @param bean input data.
	 * @throws InvalidPropertyFileException
	 * @throws InvalidDateException
	 */
	public void digesterListener(EngineInputBean bean) 
			throws InputBeanNotValidException, InvalidPropertyFileException, InvalidDateException {
		
		if (bean == null) {
			throw new InputBeanNotValidException("EngineInputBean is null");
		}
		
		Properties property = processPropertyFile(bean.pathToPropertyFile);
		date = processDate(bean.date);
		inputs = data.processInputListeners(bean.inputListeners, property);
		outputs = data.processOutputListeners(bean.outputListeners, property);	
	}
	
	/**
	 * Load propertyFile into a File.
	 * @param propertyFile filePath
	 * @return property file
	 * @throws InvalidPropertyFileException error while processing the property file.
	 */
	private Properties processPropertyFile(String propertyFile) throws InvalidPropertyFileException {
		
		Properties prop;
		InputStream input = null;
		prop = new Properties();
		
		if (StringUtils.isEmpty(propertyFile)) {
			propertyFile = DEFAULT_PROPERTY_FILE;
		}
		
		try {
			input = new FileInputStream(propertyFile);
        } catch (FileNotFoundException ex) {
        	try {
        		input = FileUtils.getDefaultFileAsResource(propertyFile);
        	} catch (InvalidInputStreamException iISEex) {
        		throw new InvalidPropertyFileException("something happened while trying to load file " + propertyFile + " : " + iISEex.getMessage());
        	}
        } finally {
        	try {
        		prop.load(input);
        	} catch (IOException ioex) {
        		throw new InvalidPropertyFileException("something happened while trying to load file " + propertyFile + " : " + ioex.getMessage());
        	} catch (NullPointerException nEx) {
        		throw new InvalidPropertyFileException("something happened while trying to load file " + propertyFile + " : " + nEx.getMessage());
        	}
        }
		
		
		return prop;
	}
	
	/**
	 * Process input date
	 * @param dateInput date as parameter
	 * @return date string well formatted.
	 * @throws InvalidDateException
	 */
	
	private String processDate(String dateInput) throws InvalidDateException {
		
		String outputDate;
		//Today
		if (StringUtils.isEmpty(dateInput)) {
		   outputDate = formatDate.format(new Date());
		} else {
			try {
				Date dat = formatDate.parse(dateInput);
				outputDate = formatDate.format(dat);
			} catch (ParseException e) {
				throw new InvalidDateException("something happened while trying to process input date " + dateInput + " : " + e.getMessage());
			}
		}
		return outputDate;
	}
	
	/**
	 * Building the listeners.
	 * @throws InitListenerException
	 */
	public void init() throws InitListenerException {
		inputs.stream().forEach(e->e.init());
		outputs.stream().forEach(e->e.init());
	}
	
	/**
	 * Send input data to the output.
	 * @throws BusinessListenerException
	 */
	public void run() throws BusinessListenerException {
		
		inputs
			.stream()
			.forEach(e->
			sendDataToOutputListener(e.getInformation(date),
					filterOutputListeners(e.getOutputChannels())));
		
	}
	
	/**
	 * Process output listeners.
	 * @param filteredOutput selected outputs 
	 * @return list of outputs
	 * @throws BusinessListenerException
	 */
	
	private List<OutputListenerInterface> filterOutputListeners(String[] filteredOutput) throws BusinessListenerException {
		
		List<OutputListenerInterface> outputFiltered = 
				new ArrayList<OutputListenerInterface>(outputs);
		
		if (filteredOutput.length > 0) {
			
			List<String> filters = Arrays.asList(filteredOutput);
			
			outputFiltered =
					outputFiltered
					.stream()
					.filter(e->filters.contains(e.getId()))
					.collect(Collectors.toList());
		}	
		
		return outputFiltered;
	}

	/**
	 * Send data to output.
	 * @param beans inputBean.
	 * @param outputsFiltered
	 * @throws BusinessListenerException
	 */
	private void sendDataToOutputListener(final List<InputBean> beans, 
										  final List<OutputListenerInterface> outputsFiltered) throws BusinessListenerException {
		beans.stream().forEach(e->{
			outputsFiltered.stream().forEach(f->processOutInformation(e,f));
		});	
	}
	
	/**
	 * Process every output bean with every active output listener
	 * @param bean inputBean
	 * @param outputStream outputStream
	 */
	private void processOutInformation(InputBean bean, OutputListenerInterface outputStream) {
		try {
			outputStream.sendInformation(bean);
		} catch (BusinessListenerException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
