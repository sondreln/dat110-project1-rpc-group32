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
		Socket clientSocket;
		
		// TODO - START :: OK?
		// connect to messaging server using a TCP socket
		// create and return a corresponding messaging connection

		try {
			clientSocket = new Socket(this.server, this.port);
			connection = new MessageConnection(clientSocket);
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host " + this.server);
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to " +
							   this.server);
			e.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return connection;
	}
}
