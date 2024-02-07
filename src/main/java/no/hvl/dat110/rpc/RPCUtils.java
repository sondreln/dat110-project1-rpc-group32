package no.hvl.dat110.rpc;

//import java.nio.ByteBuffer;
//import java.util.Arrays;
import no.hvl.dat110.TODO;

public class RPCUtils {
	
	public static byte[] encapsulate(byte rpcid, byte[] payload) {
		
		byte[] rpcmsg = null;
		
		// Encapsulate the rpcid and payload in a byte array according to the RPC message syntax / format
		
		if (payload == null)
			throw new UnsupportedOperationException(TODO.method());
		
		rpcmsg = new byte[payload.length +1];

		rpcmsg[0] = rpcid;

		System.arraycopy(payload, 0, rpcmsg, 1, payload.length);
		
		return rpcmsg;
	}
	
	public static byte[] decapsulate(byte[] rpcmsg) {
		
		byte[] payload = null;
		
		// Decapsulate the rpcid and payload in a byte array according to the RPC message syntax
		
		if (rpcmsg == null)
			throw new UnsupportedOperationException(TODO.method());

		payload = new byte[rpcmsg.length - 1];

		System.arraycopy(rpcmsg, 1, payload, 0, rpcmsg.length -1);
		
		return payload;
	}

	// convert String to byte array
	public static byte[] marshallString(String str) {
		
		byte[] encoded = null;
		
		if (str == null)
			throw new UnsupportedOperationException(TODO.method());
		
		encoded = str.getBytes();

		return encoded;
	}

	// convert byte array to a String
	public static String unmarshallString(byte[] data) {
		
		String decoded = null; 
		
		if (data == null)
			throw new UnsupportedOperationException(TODO.method());
		
		decoded = new String(data);
		
		return decoded;
	}
	
	public static byte[] marshallVoid() {
		
		byte[] encoded = null;

		encoded = new byte[1];
		
		return encoded;
		
	}
	
	public static void unmarshallVoid(byte[] data) {
		
		if(data == null || data.length != 0)
			throw new IllegalArgumentException("Forventa en tom data til umarshall av void");
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

		//  **Hint** Remember that an integer in Java is 4 bytes
		encoded = new byte[4];
		encoded[0] = (byte) (x >> 24);
		encoded[1] = (byte) (x >> 16);
		encoded[2] = (byte) (x >> 8);
		encoded[3] = (byte) (x);
		
		return encoded;
	}
	
	// byte array representation to integer
	public static int unmarshallInteger(byte[] data) {
		
		int decoded = 0;

		// |= er det samme som A = A | B; 

		decoded |= (data[0] & 0xFF) << 24;
		decoded |= (data[1] & 0xFF) << 16;
		decoded |= (data[2] & 0xFF) << 8;
		decoded |= (data[3] & 0xFF);

		return decoded;
		
	}
}
