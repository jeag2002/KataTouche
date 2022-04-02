package com.kata.listeners.output;

import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.kata.exceptions.BusinessListenerException;
import com.kata.exceptions.InitListenerException;
import com.kata.exceptions.InvalidPropertyFileException;
import com.kata.listeners.OutputListenerInterface;
import com.kata.listeners.beans.InputBean;
import com.kata.listeners.beans.InputBeanEmail;
import com.kata.listeners.output.helper.MailOutputTransport;

/**
 * MailOutputListener class.
 */
public class MailOutputListener implements OutputListenerInterface {

	/** index. */
	private String index;

	/** HOST. */
	private static final String HOST = "host";
	/** FROM. */
	private static final String FROM = "from";
	/** SUBJECT. */
	private static final String SUBJECT = "subject";
	/** MESSAGE. */
	private static final String MESSAGE = "message";

	/** hostSMTP. */
	private String hostSMTP;
	/** fromSMTP. */
	private String fromSMTP;
	/** subjectSMTP. */
	private String subjectSMTP;
	/** messageSMTP. */
	private String messageSMTP;
	
	/** session. */
	private Session session;
	
	/** transport wrapper. */
	private MailOutputTransport transport;


	@Override
	public void createListener(String index, Properties property) throws InvalidPropertyFileException {
		try {
			this.index = index;
			this.hostSMTP = property.getProperty(index + "." + HOST);
			this.fromSMTP = property.getProperty(index + "." + FROM);
			this.subjectSMTP = property.getProperty(index+"."+ SUBJECT);
			this.messageSMTP = property.getProperty(index + "." + MESSAGE);
		} catch (NullPointerException uEx) {
			 throw new InvalidPropertyFileException("something happened while trying to process properties : " + uEx.getMessage());
		}
	}

	@Override
	public void sendInformation(InputBean bean) throws BusinessListenerException {

		InputBeanEmail inputBeanEmail;
		
		if (bean instanceof InputBeanEmail) {
			inputBeanEmail = (InputBeanEmail)bean;
		} else {
			throw new BusinessListenerException("Something happened while processing output listener Input unexpected!");
		}

		try{  
			MimeMessage message = new MimeMessage(session);  
			message.setFrom(new InternetAddress(fromSMTP));  
			message.addRecipient(Message.RecipientType.TO,new InternetAddress(inputBeanEmail.email));  
			message.setSubject(subjectSMTP);  
			String text = MessageFormat.format(messageSMTP, inputBeanEmail.name);
			message.setText(text);  

			System.out.println("send MAIL message to " + inputBeanEmail.name);
			
			// Send message  
			//Transport.send(message);  
			transport.send(message);
			
		} catch (NullPointerException nEx) {
			throw new BusinessListenerException("Something happened while processing output listener " + index + " : " + nEx.getMessage());
		} catch (MessagingException mex) {
			throw new BusinessListenerException("Something happened while processing output listener " + index + " : " + mex.getMessage());
		}  
	}  

	@Override
	public void init() throws InitListenerException {
		try {
			Properties properties = System.getProperties();  
			properties.setProperty("mail.smtp.host", hostSMTP);  
			session = Session.getDefaultInstance(properties);
			transport = new MailOutputTransport();
			
		} catch (NullPointerException nEx) {
			throw new InitListenerException(
					"Something happened while initialize output listener " + index + " : " + nEx.getMessage());

		}
	}

	@Override
	public String getId() {
		return index;
	}

}
