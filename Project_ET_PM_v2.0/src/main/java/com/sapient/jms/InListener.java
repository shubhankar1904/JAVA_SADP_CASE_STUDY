package com.sapient.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import com.sapient.exchange.entities.Exchange;
import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.services.BlockService;
import com.sapient.services.FillServices;
import com.sapient.services.SecurityService;

@Component
public class InListener {
	
	
	private BlockService Blockservice;
	private FillServices Fill;
	private SecurityService Sec;
	private  JmsTemplate jmsTemplate;
	
	@Autowired
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	@Autowired
	public void setBlockservice(BlockService blockservice) {
		Blockservice = blockservice;
	}

	@Autowired
	public void setFill(FillServices fill) {
		Fill = fill;
	}

	@Autowired
	public void setSec(SecurityService sec) {
		Sec = sec;
	}
	
	
	

	@JmsListener(destination = "Blocks")
	public void receiveOrder(Block block) {
		// storeService.registerOrder(block);
		// broker.send_Ack(block.getBlockId());
		System.out.println("block" + block);
		Blockservice.saveBlock(block);
		String side="SELL";
		
		
		
		System.out.println(block.getStopPrice()+"Before Editing");
		System.out.println(block.getSide());
		// Blockservice.saveBlock(block);
		if(side.equals(block.getSide())&&(block.getStopPrice()==0.0)){
			
			block.setStopPrice(999999999);
			double i=block.getStopPrice();
			System.out.println("StopPrice after editing"+i);
		}
		Exchange e = new Exchange();
		e.executeOrder(block, Fill, Sec,jmsTemplate);
//		broker.send_Object(fill);

	}

	/*
	 * @Autowired public void setBroker(BrokerSendsFill broker) { this.broker =
	 * broker; }
	 */

	/*
	 * @JmsListener(destination = "Ack_Trader") public String recieveAck(String
	 * ack) { storeService.recieve_Ack(ack);
	 * 
	 * return ack; }
	 */

}