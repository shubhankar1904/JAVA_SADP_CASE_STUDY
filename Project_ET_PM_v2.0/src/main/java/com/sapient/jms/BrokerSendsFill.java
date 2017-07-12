package com.sapient.jms;

import com.sapient.entities.Fill;

public interface BrokerSendsFill {

	public void send_Object(Fill fill);
	
	public void send_Ack(int blockID);
	
	
	public void send_Start(String start);
	public void send_Stop(String stop);
	

}
