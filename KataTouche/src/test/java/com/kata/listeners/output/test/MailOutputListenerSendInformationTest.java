package com.kata.listeners.output.test;

import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

import com.kata.exceptions.BusinessListenerException;
import com.kata.listeners.beans.InputBean;
import com.kata.listeners.beans.InputBeanEmail;
import com.kata.listeners.output.MailOutputListener;
import com.kata.listeners.output.helper.MailOutputTransport;

/** MailOutputListenerSendInformationTest. */
public class MailOutputListenerSendInformationTest {
	
	/** MailOutputListener. */
	@InjectMocks
	private MailOutputListener oListener;
	
	/** Transport. */
	@Mock
	private MailOutputTransport transport;
	
	/** Init engine. */
	@Before
	public void initEngine() {
		MockitoAnnotations.initMocks(this);
	}
	
	/** Send info by email correctly. */
	@Test
	public void sendInformationSuccessFully() throws Exception {
		
		Properties properties = System.getProperties();  
		properties.setProperty("mail.smtp.host", "smtp.mailtrap.io");  
		Session session = Session.getDefaultInstance(properties);  
		
		Whitebox.setInternalState(oListener, "session", session);
		Whitebox.setInternalState(oListener, "fromSMTP", "robot@foobar.com");
	    Whitebox.setInternalState(oListener, "subjectSMTP", "Happy BirthDay!");
	    Whitebox.setInternalState(oListener, "messageSMTP", "Happy birthday, dear {0}!");
	    
		InputBeanEmail iBE = new InputBeanEmail();
		iBE.name = "John";
		iBE.surname = "Doe";
		iBE.date = "1982/10/08";
		iBE.email = "john.doe@foobar.com";
		
		Mockito.doNothing().when(transport).send(Mockito.any());
		oListener.sendInformation(iBE);
	}
	
	/** Send info session  null. */
	@Test(expected = BusinessListenerException.class)
	public void sendInformationSessionNull() throws Exception {
		

		Whitebox.setInternalState(oListener, "session", null);
		Whitebox.setInternalState(oListener, "fromSMTP", null);
	    Whitebox.setInternalState(oListener, "subjectSMTP", null);
	    Whitebox.setInternalState(oListener, "messageSMTP", null);
	    
		InputBeanEmail iBE = new InputBeanEmail();
		iBE.name = "John";
		iBE.surname = "Doe";
		iBE.date = "1982/10/08";
		iBE.email = "john.doe@foobar.com";
		oListener.sendInformation(iBE);
	}
	
	
	/** Send info input bean null. */
	@Test(expected = BusinessListenerException.class)
	public void sendInformationInputBeanNull() throws Exception {
		InputBean bE = null;
		oListener.sendInformation(bE);
	}
	
	/** Send info bad input Bean. */
	@Test(expected = BusinessListenerException.class)
	public void sendInformationInputBeanUnexpected() throws Exception {
		InputBean bE = new InputBean();
		bE.name = "John";
		bE.surname = "Doe";
		bE.date = "1982/10/08";
		oListener.sendInformation(bE);
	}
	
	

}
