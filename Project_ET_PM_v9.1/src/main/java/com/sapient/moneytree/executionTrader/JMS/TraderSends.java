package com.sapient.moneytree.executionTrader.JMS;

import com.sapient.moneytree.executionTrader.domain.Block;

/**
 * 
 * @author aaga50
 *
 */
public interface TraderSends {

	public void send_Object(Block block);

	public void send_Ack(long executedid);
}
