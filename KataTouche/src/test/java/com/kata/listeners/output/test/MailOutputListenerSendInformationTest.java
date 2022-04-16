package com.kata.listeners.output.test;

import com.kata.exceptions.BusinessListenerException;
import com.kata.listeners.beans.InputBean;
import com.kata.listeners.beans.InputBeanEmail;
import com.kata.listeners.output.MailOutputListener;
import com.kata.listeners.output.helper.MailOutputTransport;
import java.util.Properties;
import javax.mail.Session;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;

/** MailOutputListenerSendInformationTest. */
public class MailOutputListenerSendInformationTest {

  /** MailOutputListener. */
  @InjectMocks
  private MailOutputListener listener;

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
    
    Whitebox.setInternalState(listener, "session", session);
    Whitebox.setInternalState(listener, "fromSmtp", "robot@foobar.com");
    Whitebox.setInternalState(listener, "subjectSmtp", "Happy BirthDay!");
    Whitebox.setInternalState(listener, "messageSmtp", "Happy birthday, dear {0}!");
    
    InputBeanEmail ibe = new InputBeanEmail();
    ibe.name = "John";
    ibe.surname = "Doe";
    ibe.date = "1982/10/08";
    ibe.email = "john.doe@foobar.com";
    Mockito.doNothing().when(transport).send(Mockito.any());
    listener.sendInformation(ibe);
  }

  /** Send info session  null. */
  @Test(expected = BusinessListenerException.class)
  public void sendInformationSessionNull() throws Exception {
    Whitebox.setInternalState(listener, "session", null);
    Whitebox.setInternalState(listener, "fromSmtp", null);
    Whitebox.setInternalState(listener, "subjectSmtp", null);
    Whitebox.setInternalState(listener, "messageSmtp", null);

    InputBeanEmail ibe = new InputBeanEmail();
    ibe.name = "John";
    ibe.surname = "Doe";
    ibe.date = "1982/10/08";
    ibe.email = "john.doe@foobar.com";
    listener.sendInformation(ibe);
  }
  
  /** Send info input bean null. */
  @Test(expected = BusinessListenerException.class)
  public void sendInformationInputBeanNull() throws Exception {
    final InputBean bE = null;
    listener.sendInformation(bE);
  }

  /** Send info bad input Bean. */
  @Test(expected = BusinessListenerException.class)
  public void sendInformationInputBeanUnexpected() throws Exception {
    InputBean be = new InputBean();
    be.name = "John";
    be.surname = "Doe";
    be.date = "1982/10/08";
    listener.sendInformation(be);
  }
}
