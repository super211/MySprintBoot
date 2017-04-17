package com.my.dao;

import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void send(String to, String from, String host, String title, String mail) {

		// Get the session object
		Properties properties = System.getProperties();
		Authenticator authenticator = new Authenticator();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.host", host);
		// properties.setProperty("mail.smtp.port", "25");//No need because the
		// default is 25
		// Session session = Session.getDefaultInstance(properties);//For the
		// scenario of no need authentication
		Session session = Session.getInstance(properties, authenticator);

		// compose the message
		try {
			StringTokenizer st = new StringTokenizer(to, ",");
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress(to));//For adding one email address
			while (st.hasMoreTokens()) {
				// message.addRecipients(Message.RecipientType.CC,
				// InternetAddress.parse(st.nextToken(),false));
				message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(st.nextToken(), false));
			}
			message.setSubject(title);
			message.setText(mail);

			// Send message
			Transport.send(message);
			System.out.println("message sent successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	private static class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = "lgp211";
			String password = "wy.8i2ii0.";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}
}
