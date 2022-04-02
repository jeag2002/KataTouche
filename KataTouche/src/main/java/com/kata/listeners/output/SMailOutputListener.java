package com.kata.listeners.output;

import java.text.MessageFormat;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
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

/** SMailOutputListener.class. */
public class SMailOutputListener implements OutputListenerInterface {

	/** index. */
	private String index;

	/** HOST. */
	private static final String HOST = "host";
	/** PORT. */
	private static final String PORT = "port";
	/** FROM. */
	private static final String FROM = "from";
	/** AUTH_FLAG. */
	private static final String AUTH_FLAG = "auth_flag";
	/** STARTTLS_ENABLE. */
	private static final String STARTTLS_ENABLE = "starttls_enable";
	/** PASSWORD. */
	private static final String PASSWORD = "password";
	/** SUBJECT. */
	private static final String SUBJECT = "subject";
	/** MESSAGE. */
	private static final String MESSAGE = "message";

	/** hostSMTP. */
	private String hostSMTP;
	/** portSMTP. */
	private String portSMTP;
	/** fromSMTP. */
	private String fromSMTP;
	/** authFlagSMTP. */
	private boolean authFlagSMTP;
	/** startTlsEnableSMTP. */
	private boolean startTlsEnableSMTP;
	/** passwordSMTP. */
	private String passwordSMTP;
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
			this.portSMTP = property.getProperty(index + "." + PORT);
			this.fromSMTP = property.getProperty(index + "." + FROM);
			this.authFlagSMTP = Boolean.parseBoolean(property.getProperty(index + "." + AUTH_FLAG));
			this.startTlsEnableSMTP = Boolean.parseBoolean(property.getProperty(index + "." + STARTTLS_ENABLE));
			this.passwordSMTP = property.getProperty(index + "." + PASSWORD);
			this.subjectSMTP = property.getProperty(index + "." + SUBJECT);
			this.messageSMTP = property.getProperty(index + "." + MESSAGE);
		} catch (NullPointerException uEx) {
			 throw new InvalidPropertyFileException("something happened while trying to process properties : " + uEx.getMessage());
		}
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return index;
	}

	@Override
	public void init() throws InitListenerException {
		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", hostSMTP);
			props.put("mail.smtp.port", portSMTP);
			props.put("mail.smtp.auth", authFlagSMTP);
			props.put("mail.smtp.starttls.enable", startTlsEnableSMTP);
	
			Authenticator auth = new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromSMTP, passwordSMTP);
				}
			};
			session = Session.getInstance(props, auth);
			transport = new MailOutputTransport();
		} catch (NullPointerException nEx) {
			throw new InitListenerException(
					"Something happened while initialize output listener " + index + " : " + nEx.getMessage());
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

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromSMTP));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(inputBeanEmail.email));
			message.setSubject(subjectSMTP);
			String text = MessageFormat.format(messageSMTP, inputBeanEmail.name);
			message.setText(text);

			System.out.println("send SMAIL message to " + inputBeanEmail.name);

			// Send message
			//Transport.send(message);
			transport.send(message);
			
		} catch (NullPointerException nEx) {
			throw new BusinessListenerException("Something happened while processing output listener " + index + " : " + nEx.getMessage());
			
		} catch (MessagingException mex) {
			throw new BusinessListenerException(
					"Something happened while processing output listener " + index + " : " + mex.getMessage());
		}
	}

}
