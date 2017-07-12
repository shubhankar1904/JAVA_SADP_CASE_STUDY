package com.sapient.ETtest;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sapient.moneytree.portfolioManager.domain.Orders;

public class OrderTest {

	@Test
	public void test() {
		Orders order = new Orders();
		double qty = 100;
		order.setQty(qty);
		assertEquals(qty, order.getQty(), 0);
		
	}

}
