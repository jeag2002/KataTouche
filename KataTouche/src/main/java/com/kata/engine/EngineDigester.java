package com.kata.engine;

import com.kata.constants.InputTypeListenersEnum;
import com.kata.constants.OutputTypeListenersEnum;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.InputListenerInterface;
import com.kata.listeners.OutputListenerInterface;
import com.kata.listeners.input.FileInputListener;
import com.kata.listeners.output.MailOutputListener;
import com.kata.listeners.output.SecureMailOutputListener;
import com.kata.utils.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/** EngineDigester.class */
public class EngineDigester {

  /** TYPE. */
  private static final String TYPE = "type";

  /**
  * Process inputListeners.

  * @param inputData Properties
  * @param prop PropertyFile.
  * @return List of inputListeners.
  * @throws InvalidPropertyFileException exceptions
  */
  public List<InputListenerInterface> processInputListeners(String[] inputData, Properties prop) 
         throws InvalidPropertyFileException {
    List<InputListenerInterface> inputs = new ArrayList<InputListenerInterface>();
    inputData = StringUtils.checkStringArray(inputData);
    if (prop == null) {
      throw new InvalidPropertyFileException("Property file is null ");
    }
    for (String id : inputData) {
      String type = prop.getProperty(id + "." + TYPE);
      if (!StringUtils.isEmpty(type)) {
        processInputListener(type, id, prop, inputs);
      }
    }
    return inputs;
  }

  /**
  * Process outputListeners.

  * @param outputData properties
  * @param prop propertyFile.
  * @return List of outputListeners
  * @throws InvalidPropertyFileException exceptions
  */
  public List<OutputListenerInterface> processOutputListeners(String[] outputData, Properties prop) 
         throws InvalidPropertyFileException {
    List<OutputListenerInterface> outputs = new ArrayList<OutputListenerInterface>();
    outputData = StringUtils.checkStringArray(outputData);

    if (prop == null) {
      throw new InvalidPropertyFileException("Property file is null ");
    }

    for (String id : outputData) {
      String type = prop.getProperty(id + "." + TYPE);
      if (!StringUtils.isEmpty(type)) {
        processOutputListener(type, id, prop, outputs);
      }
    }
    return outputs;
  }

  /**
  * Process input listener.

  * @param type input type listener
  * @param id id type listener
  * @param props properties
  * @return input listener interface
  * @throws InvalidPropertyFileException exceptions
  */
  private InputListenerInterface processInputListener(String type, String id, Properties props, 
         List<InputListenerInterface> inputs)
         throws InvalidPropertyFileException {
    InputTypeListenersEnum typeEnum = InputTypeListenersEnum.valueOf(type); 
    InputListenerInterface inputInterface = null;

    switch (typeEnum) {
      case FILE: inputInterface = processFileInputListener(id, props); 
                 break;
      default: break;
    }
    
    if (inputInterface != null) {
      inputs.add(inputInterface);
    }

    return inputInterface;
  }

  /**
  * Process output listener.

  * @param type output type listener
  * @param id id type listener
  * @param props properties
  * @return output listener interface
  * @throws InvalidPropertyFileException exceptions
  */
  private OutputListenerInterface processOutputListener(String type, String id, Properties props, 
         List<OutputListenerInterface> outputs) 
         throws InvalidPropertyFileException {
    OutputTypeListenersEnum typeEnum = OutputTypeListenersEnum.valueOf(type); 
    OutputListenerInterface outputInterface = null;
    
    switch (typeEnum) {
      case MAIL: outputInterface = processMailOutputListener(id, props); 
                 break;
      case SMAIL: outputInterface = processSecureMailOutputListener(id, props); 
                 break;
      default: break;
    }

    if (outputInterface != null) {
      outputs.add(outputInterface);
    }
    return outputInterface;
  }

  /**
  * Process file input Listener.

  * @param id identificator listener
  * @param props property data
  * @return FileInputListener
  * @throws InvalidPropertyFileException exceptions
  */
  private FileInputListener processFileInputListener(String id, Properties props) 
         throws InvalidPropertyFileException {
    FileInputListener input = new FileInputListener();
    input.createListener(id, props);
    return input;
  }

  /**
  * Process mail output Listener.

  * @param id identificator listener
  * @param props property data
  * @return MailOutputListener
  * @throws InvalidPropertyFileException exceptions
  */
  private MailOutputListener processMailOutputListener(String id, Properties props) 
         throws InvalidPropertyFileException {
    MailOutputListener output = new MailOutputListener();
    output.createListener(id, props);
    return output;
  }

  /**
  * Process sMail output Listener.

  * @param id identificator listener
  * @param props property data
  * @return MailOutputListener
  * @throws InvalidPropertyFileException exceptions
  */
  private SecureMailOutputListener processSecureMailOutputListener(String id, Properties props) 
         throws InvalidPropertyFileException {
    SecureMailOutputListener output = new SecureMailOutputListener();
    output.createListener(id, props);
    return output;
  }

}