package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class DisplayStub extends RPCLocalStub {

	public DisplayStub(RPCClient rpcclient) {
		super(rpcclient);
	}
	
	public void write (String message) {
		
		// TODO - START :: OK?
		
		// implement marshalling, call and unmarshalling for write RPC method
		byte[] request = RPCUtils.marshallString(message);

		byte[] response = rpcclient.call((byte)Common.READ_RPCID, request);

		RPCUtils.unmarshallString(response);
		
	}
}
