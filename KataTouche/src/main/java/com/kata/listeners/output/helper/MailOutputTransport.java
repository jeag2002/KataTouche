package com.kata.listeners.output.helper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/** MailOutputTransport. */
public class MailOutputTransport {
  public void send(MimeMessage msg) throws MessagingException {
    javax.mail.Transport.send(msg);
  }
}
