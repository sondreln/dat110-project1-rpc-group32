package no.hvl.dat110.messaging;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.MessageUtils.*;


public class MessageConnection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection
	
	public MessageConnection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream (socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		
		// TODO - START :: OK?
		// encapsulate the data contained in the Message and write to the output stream
		
		//data = MessageUtils.encapsulate(message);
		try {
			byte[] data = MessageUtils.encapsulate(message);
			this.outStream.write(data);
			this.outStream.flush();
			

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Feil med sending av melding");
		}
		
		
		if (true)
			throw new UnsupportedOperationException(TODO.method());
			
		// TODO - END

	}

	public Message receive() {

		Message message = null;
		byte[] data;
		
		// TODO - START :: OK?
		// read a segment from the input stream and decapsulate data into a Message
		
		// if (true)
		// 	throw new UnsupportedOperationException(TODO.method());

		try {
			data = new byte[127];
			int read = inStream.read(data);

			if(read == -1)
				throw new IOException("Du har nådd slutten på streamen");

			message = MessageUtils.decapsulate(data);

		} catch (IOException ex) {
			ex.printStackTrace();
			throw new RuntimeException("Feil oppstod når vi henta melding");
		}
		
		// TODO - END
		
		return message;
		
	}

	// close the connection by closing streams and the underlying socket	
	public void close() {

		try {
			
			outStream.close();
			inStream.close();

			socket.close();
			
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}