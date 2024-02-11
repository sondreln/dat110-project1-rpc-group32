package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class SensorStub extends RPCLocalStub {

	public SensorStub(RPCClient rpcclient) {
		super(rpcclient);
	}

	public int read() {
		
		int temp = 0;
		
		byte[] BytetoSend = RPCUtils.marshallInteger(temp);
		
		byte[] BytetoRecive = rpcclient.call((byte)Common.READ_RPCID, BytetoSend);
		
		RPCUtils.unmarshallInteger(BytetoRecive);
		
		
		return temp;
	}
}
