package no.hvl.dat110.messaging;

//import java.util.Arrays;

public class MessageUtils {

	public static final int SEGMENTSIZE = 128;

	public static int MESSAGINGPORT = 8080;
	public static String MESSAGINGHOST = "localhost";

	public static byte[] encapsulate(Message message) {
		
		byte[] segment = null;
		byte[] data = null;
		
		data = message.getData();
		segment = new byte [SEGMENTSIZE];
		
	
		segment[0] = (byte) message.getData().length;
		for (int i = 1; i <= message.getData().length; i++) {
			segment[i] = data[i - 1];
		}
	
		return segment;
		
	}

	public static Message decapsulate(byte[] segment) {

		Message message = null;
		
		int lengde = segment[0];
		
		byte[] data = new byte[lengde];
		
		for(int i = 0; i < lengde; i++) {
			data[i] = segment[i + 1];
		}
		message = new Message(data);
		
		return message;
		
	}
	
}
