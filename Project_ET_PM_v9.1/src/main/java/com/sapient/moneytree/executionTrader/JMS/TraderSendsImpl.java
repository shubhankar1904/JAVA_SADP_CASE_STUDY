package com.sapient.moneytree.executionTrader.JMS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import com.sapient.moneytree.executionTrader.domain.Block;

/**
 * 
 * @author aaga50
 *
 */
@Service
public class TraderSendsImpl implements TraderSends {

	private JmsTemplate jmsTemplate;

	/**
	 * 
	 * @param jmsTemplate
	 */
	@Autowired
	public TraderSendsImpl(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	@Override
	public void send_Object(Block block) {
		jmsTemplate.convertAndSend("Blocks", block);

	}

	/**
	 * 
	 */
	@Override
	public void send_Ack(long executedid) {
		jmsTemplate.convertAndSend("Ack_Trader", executedid);
	}

}
