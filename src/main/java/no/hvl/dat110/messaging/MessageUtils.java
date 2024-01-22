package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data;
		
		// TODO - START
		
		// encapulate/encode the payload data of the message and form a segment
		// according to the segment format for the messaging layer
		
		// if (true)
		// 	throw new UnsupportedOperationException(TODO.method());

		segment = new byte[SEGMENTSIZE];
		
		data = message.getData();

		// Blir og egentlig håndtert i Message? 
		if(data.length > SEGMENTSIZE - 1) {
			throw new IllegalArgumentException("Message data er for stor!");
		}

		segment[0] = (byte) data.length;

		System.arraycopy(data, 0, segment, 1, data.length);
			
		// TODO - END
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		// TODO - START
		// decapsulate segment and put received payload data into a message
		
		int size = segment[0];

		byte[] data = new byte[size];
		System.arraycopy(segment, 1, data, 0, size);
		
		message = new Message(data);

		// if (true)
		// 	throw new UnsupportedOperationException(TODO.method());
		
		// TODO - END
		
		return message;
		
	}
	
}
