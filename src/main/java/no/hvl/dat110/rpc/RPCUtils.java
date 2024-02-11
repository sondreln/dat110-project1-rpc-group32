package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.Arrays;

//import java.nio.ByteBuffer;
//import java.util.Arrays;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		
		rpcmsg = new byte[payload.length+1];
		rpcmsg[0] = rpcid;

		for (int i = 1; i < payload.length + 1; i++) {
			rpcmsg[i] = payload[i-1];
		}

		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;

		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		
		payload = new byte[rpcmsg.length-1];
		
		for (int i = 0; i < payload.length; i++) {
			payload[i] = rpcmsg[i+1];
		}

		return payload;
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = new byte[2];

		encoded = str.getBytes();

		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null;

		decoded = new String(data);
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = new byte[1];
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		return;
	}

	// convert boolean to a byte array representation
	public static byte[] marshallBoolean(boolean b) {
		
		byte[] encoded = new byte[1];
				
		if (b) {
			encoded[0] = 1;
		} else
		{
			encoded[0] = 0;
		}
		
		return encoded;
	}

	// convert byte array to a boolean representation
	public static boolean unmarshallBoolean(byte[] data) {
		
		return (data[0] > 0);
		
	}

	// integer to byte array representation
	public static byte[] marshallInteger(int x) {
		
		byte[] encoded = null;

		encoded = new byte[4];

		encoded = ByteBuffer.allocate(4).putInt(x).array();

		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;

		for (byte b : data) {
			decoded = (decoded << 8) + (b & 0xFF);
		}

		return decoded;
		
	}
}
