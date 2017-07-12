package com.sapient.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sapient.entities.Fill;
@Service
public class BrokerSendsFillsImpl implements BrokerSendsFill {

	public BrokerSendsFillsImpl(){}
	
	private  JmsTemplate jmsTemplate;
	
	    @Autowired
	    public BrokerSendsFillsImpl(JmsTemplate jmsTemplate) {
	        this.jmsTemplate = jmsTemplate;
	    }
		public void send_Object(Fill fill) {
			// TODO Auto-generated method stub
	    	System.out.println(fill);
			jmsTemplate.convertAndSend("Fills", fill);
			System.out.println("Fill Sent");
		}

		@Override
		public void send_Ack(int blockID) {
			jmsTemplate.convertAndSend("Ack_Broker", blockID);
			
		}

		@Override
		public void send_Start(String start) {
			jmsTemplate.convertAndSend("Exchange", start);
			
		}

		@Override
		public void send_Stop(String stop) {
			jmsTemplate.convertAndSend("Exchange", stop);
			
		}

	

	
	
	
	
	
}
