package com.sapient.ETtest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.portfolioManager.domain.Orders;

public class BlockTest {

	@Test
	public void test() {
		Orders order1 = new Orders();
		Orders order2 = new Orders();
		Orders order3 = new Orders();
		double qty = 100;
		order1.setQty(qty);
		order2.setQty(qty);
		order3.setQty(qty);
		Collection<Orders> orderCol = new ArrayList<Orders>();
		orderCol.add(order1);
		orderCol.add(order2);
		orderCol.add(order3);
		Block block = new Block();
		block.setOrderList((List<Orders>) orderCol);
	    double totalquantity;
	    totalquantity=(double)(block.getTotalQty());
	    double expected = 300.00;
	    double delta = 0.0;
		assertEquals(totalquantity, expected, delta);


	}

}
