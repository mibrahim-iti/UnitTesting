package com.mibrahim.patientintake.notifier;

import java.util.ArrayList;
import java.util.List;

public class EmailNotifierTestDouble implements EmailNotifier {

	List<Message> receivedMessages = new ArrayList<>();

	@Override
	public void sendNotification(String subject, String body, String toAddress) {
		receivedMessages.add(new Message(toAddress, subject, body));
	}

	class Message {
		public String toAddress;
		public String subject;
		public String body;

		public Message(String toAddress, String subject, String body) {
			this.toAddress = toAddress;
			this.subject = subject;
			this.body = body;
		}
	}

}
