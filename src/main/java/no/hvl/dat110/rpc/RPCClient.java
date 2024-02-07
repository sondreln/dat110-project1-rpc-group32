package no.hvl.dat110.rpc;

import no.hvl.dat110.messaging.*;

public class RPCClient {

	// underlying messaging client used for RPC communication
	private MessagingClient msgclient;

	// underlying messaging connection used for RPC communication
	private MessageConnection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void connect() {
		
		// TODO - START :: OK?
		// connect using the RPC client
		
		try {
			connection = msgclient.connect();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Feil på oppkobling til RPC server");
		}
	}
	
	public void disconnect() {
		
		// TODO - START
		// disconnect by closing the underlying messaging connection
		
		if (connection != null){
			try {
				connection.close();
				connection = null;
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Feil på frakobling til RPC server");
			}
		}
	}

	/*
	 Make a remote call om the method on the RPC server by sending an RPC request message and receive an RPC reply message

	 rpcid is the identifier on the server side of the method to be called
	 param is the marshalled parameter of the method to be called
	 */

	public byte[] call(byte rpcid, byte[] param) {
		
		byte[] returnval = null;
		byte[] rpcReq = RPCUtils.encapsulate(rpcid, param);


		// TODO - START

		/*

		The rpcid and param must be encapsulated according to the RPC message format

		The return value from the RPC call must be decapsulated according to the RPC message format

		*/
				
		try {
			connection.send(new Message(rpcReq));

			Message reply = connection.receive();

			byte[] rpcReply = reply.getData();

			returnval = RPCUtils.decapsulate(rpcReply);

			return returnval;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("RPC-call feilet");
		}
	}
}
