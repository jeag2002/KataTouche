package com.kata.listeners;

import java.util.List;
import java.util.Properties;

import com.kata.exceptions.BusinessListenerException;
import com.kata.exceptions.InitListenerException;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.beans.InputBean;

/**
 * InputListenerInterface.class
 */
public interface InputListenerInterface {
	
	/**
	 * Get Index.
	 * @return index
	 */
	String getId();
	
	/**
	 * Get channel.
	 * @return list output channels
	 */
	String[] getOutputChannels();
	
	
	/**
	 * Create Listener.
	 * @param index value
	 * @param property properties value
	 * @throws InvalidPropertyFileException expection
	 */
	void createListener(String index, Properties property) throws InvalidPropertyFileException;
	
	/**
	 * Initialize the listener.
	 * @throws InitListenerException exception
	 */
	void init() throws InitListenerException;
	
	/**
	 * Get information.
	 * @param date inputDate
	 * @throws BusinessListenerException
	 * @return date List.
	 */
	List<InputBean> getInformation(String date) throws BusinessListenerException;
	

}
