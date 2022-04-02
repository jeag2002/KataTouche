package com.kata.listeners;

import java.util.Properties;

import com.kata.exceptions.BusinessListenerException;
import com.kata.exceptions.InitListenerException;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.beans.InputBean;

/**
 * OutputListenerInterface.class 
 */
public interface OutputListenerInterface {
	
	/**
	 * Get Index.
	 * @return index
	 */
	String getId();
	
	
	/**
	 * Create Listener.
	 * @param index value
	 * @param property properties value
	 * @throws InvalidPropertyFileException exception
	 */
	void createListener(String index, Properties property) throws InvalidPropertyFileException;
	
	/**
	 * Initialize the listener.
	 */
	void init() throws InitListenerException;
	
	/**
	 * Send information to a specific output.
	 * @param bean information.
	 */
	void sendInformation(InputBean bean) throws BusinessListenerException;
	

}
