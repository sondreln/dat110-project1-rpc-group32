package no.hvl.dat110.messaging;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
public class MessagingClient {

	// name/IP address of the messaging server
	private String server;

	// server port on which the messaging server is listening
	private int port;
	
	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}
	
	// setup of a messaging connection to a messaging server
	public MessageConnection connect () {

		// client-side socket for underlying TCP connection to messaging server
		MessageConnection connection = null;
		
		// TODO - START :: Skal være ok?
		// connect to messaging server using a TCP socket
		// create and return a corresponding messaging connection

		try {
			Socket clientSocket = new Socket(server, port);
			connection = new MessageConnection(clientSocket);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return connection;
	}
}
