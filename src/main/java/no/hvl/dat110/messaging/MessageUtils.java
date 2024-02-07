package no.hvl.dat110.messaging;

//import java.util.Arrays;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment;
		byte[] data;
		
		segment = new byte[SEGMENTSIZE];
		
		data = message.getData();

		if(data.length > SEGMENTSIZE - 1) {
			throw new IllegalArgumentException("Message data er for stor!");
		}

		segment[0] = (byte) data.length;

		System.arraycopy(data, 0, segment, 1, data.length);
			
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		int size = segment[0];

		byte[] data = new byte[size];
		System.arraycopy(segment, 1, data, 0, size);
		
		message = new Message(data);
		
		return message;
		
	}
	
}
