package no.hvl.dat110.messaging;

public class Message {

	// the up to 127 bytes of data (payload) that a message can hold
	private byte[] data;

	// construction a Message with the data provided
	public Message(byte[] data) {

		if(data.length <= 127) {
			this.data = data;
		}else {
			this.data = null;
		}
	}

	public byte[] getData() {
		return this.data; 
	}

}
