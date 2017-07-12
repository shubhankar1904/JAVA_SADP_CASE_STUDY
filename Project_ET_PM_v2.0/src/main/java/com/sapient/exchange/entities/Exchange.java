package com.sapient.exchange.entities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;

import com.sapient.dbHandler.*;
import com.sapient.controller.*;

import com.sapient.entities.*;

import com.sapient.jms.BrokerSendsFill;
import com.sapient.jms.BrokerSendsFillsImpl;
import com.sapient.moneytree.executionTrader.domain.*;
import com.sapient.services.*;



public class Exchange extends ExchangePrototype {

	// ________________________________________________________________________________________

	

	private void marketBuyExecute(Block a, String symbol, int iterations, FillServices fill, SecurityService sec,JmsTemplate jms) {

		System.out.println("Hello from Buy ");
		int i = 0;
		double[] arrayOfValues = new double[2];
		ArrayList<Fill> list = new ArrayList<Fill>();// Creating a list of
														// Fill type for
														// committing it
														// into Fill table
														// in database
		boolean isNextIteration = true; // Next Iteration decider is set to
		Security comp1 = sec.filterBySymbol(symbol); // true by default

		if (a.getStopPrice() > comp1.getLastPrice()) {

			StopBuyExecute(a, symbol, iterations, fill, sec,jms); // see

		}

		else {
			System.out.println("iterations"+iterations);
			while (i < iterations && a.getTotalQty() > 0 && isNextIteration == true) {

				System.out.println("In the main loop"+i);
				Security comp = sec.filterBySymbol(symbol);
				int askQuantity = a.getTotalQty();
				int quantity = quantityCalculator(askQuantity);
				double positivePriceChange = comp.getLastPrice() + ((comp.getLastPrice() * comp.getSpread()) / 100);// Calculating
				// maximum
				// minimum
				// deviation
				// a
				// security
				// can
				// go
				// through
				double negativePriceChange = comp.getLastPrice() - ((comp.getLastPrice() * comp.getSpread()) / 100);
				Random myRandom = new Random();
				arrayOfValues[0] = positivePriceChange;
				arrayOfValues[1] = negativePriceChange;

				int rdm = myRandom.nextInt(arrayOfValues.length);
				double newMarketPrice = arrayOfValues[rdm];
				comp.setLastPrice(newMarketPrice);
				double lastPrice = comp.getLastPrice();
				sec.UpdateLastPrice(lastPrice, symbol);// Updating database with
														// new Price
				int update;
				update = a.getTotalQty() - quantity;
				a.setTotalQty(update);
				if (a.getTotalQty() == 0) {
					String updateStatus = "FULL";
					System.out.println("full");
					a.setStatus(updateStatus);

					Fill f = new Fill();
					list.add(f);
					list.get(i).blockId = a.getBlockId();
					list.get(i).executedQuantity = quantity;

					list.get(i).openQuantity = update;

					list.get(i).executionPrice = newMarketPrice;
					list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
					list.get(i).side = a.getSide();
					list.get(i).symbol = a.getSymbol();
					fill.save(list.get(i));
					jms.convertAndSend("Fills", f);
					// send message to trader here
					break;
				}

				Fill f = new Fill();

				list.add(f);

				// list.get(i).blockId = a.blockId;
				list.get(i).blockId = a.getBlockId();
				list.get(i).executedQuantity = quantity;

				list.get(i).openQuantity = update;

				list.get(i).executionPrice = newMarketPrice;
				list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
				list.get(i).side = a.getSide();
				list.get(i).symbol = a.getSymbol();
				fill.save(list.get(i));
				System.out.println(f);
				
				isNextIteration = isNextIterationTrue(comp);
				System.out.println(isNextIteration);
				System.out.println("EXECUTION HAS HAPPENED");
				jms.convertAndSend("Fills", f);
				i++;

			}

		}

	}

	// __________________________________________________________________________________________

	private void marketSellExecute(Block a, String symbol, int iterations, FillServices fill,
			SecurityService sec, JmsTemplate jms) {

		int i = 0;
		double[] arrayOfValues = new double[2];
		ArrayList<Fill> list = new ArrayList<Fill>();// Creating a list of
														// Fill type for
														// committing it
														// into Fill table
														// in database
		boolean isNextIteration = true; // Next Iteration decider is set to
		Security comp1 = sec.filterBySymbol(symbol); // true by default
		System.out.println(a.getStopPrice());
		if (a.getStopPrice() < comp1.getLastPrice()) {
			

			StopSellExecute(a, symbol, iterations, fill, sec,jms); // see

		}

		else {

			while (i < iterations && a.getTotalQty() > 0 && isNextIteration == true) {

				System.out.println("In the main loop1");
				Security comp = sec.filterBySymbol(symbol);
				int askQuantity = a.getTotalQty();
				int quantity = quantityCalculator(askQuantity);
				double positivePriceChange = comp.getLastPrice() + ((comp.getLastPrice() * comp.getSpread()) / 100);// Calculating
				// maximum
				// minimum
				// deviation
				// a
				// security
				// can
				// go
				// through
				double negativePriceChange = comp.getLastPrice() - ((comp.getLastPrice() * comp.getSpread()) / 100);
				Random myRandom = new Random();
				arrayOfValues[0] = positivePriceChange;
				arrayOfValues[1] = negativePriceChange;

				int rdm = myRandom.nextInt(arrayOfValues.length);
				double newMarketPrice = arrayOfValues[rdm];
				comp.setLastPrice(newMarketPrice);
				double lastPrice = comp.getLastPrice();
				sec.UpdateLastPrice(lastPrice, symbol);// Updating database with
														// new Price
				int update;
				update = a.getTotalQty() - quantity;
				a.setTotalQty(update);
				if (a.getTotalQty() == 0) {
					String updateStatus = "FULL";

					a.setStatus(updateStatus);

					Fill f = new Fill();
					list.add(i, f);
					list.get(i).blockId = a.getBlockId();
					list.get(i).executedQuantity = quantity;

					list.get(i).openQuantity = update;

					list.get(i).executionPrice = newMarketPrice;
					list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
					list.get(i).side = a.getSide();
					list.get(i).symbol = a.getSymbol();
					fill.save(list.get(i));
					jms.convertAndSend("Fills", f);
					break;

				}

				Fill f = new Fill();

				list.add(f);

				list.get(i).blockId = a.getBlockId();
				// list.get(i).blockId = a.blockId;
				list.get(i).executedQuantity = quantity;

				list.get(i).openQuantity = update;

				list.get(i).executionPrice = newMarketPrice;
				list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
				list.get(i).side = a.getSide();
				list.get(i).symbol = a.getSymbol();

				fill.save(list.get(i));

				// commit it into database here
				// send message to trader here
				jms.convertAndSend("Fills", f);
				isNextIteration = isNextIterationTrue(comp);

				i++;

			}

		}

	}

	// __________________________________________________________________________________________
	private void limitBuyExecute(Block a, String symbol, int iterations, FillServices fill, SecurityService sec, JmsTemplate jms) {
		int i = 0;
		System.out.println("hello1");
		double newMarketPrice = 0;
		long[] arrayOfValues = new long[2];
		ArrayList<Fill> list = new ArrayList<Fill>();// Creating a list of
														// Fill type for
														// committing it
														// into Fill table
														// in database
		boolean isNextIteration = true;// Next Iteration decider is set to
										// true by default

		Security comp1 = sec.filterBySymbol(symbol);

		// Checking
		// to
		// see
		// Security comp=sec.filterBySymbol(symbol); // if
		// it
		// is
		// a
		// stop
		// order
		// and
		// trying
		// to
		// calculate
		// it

		if (a.getLimitPrice() < comp1.getLastPrice()) {

			while (i < iterations && a.getTotalQty() > 0 && isNextIteration == true) {
				System.out.println(i + "jasad");
				int quantity = quantityCalculator(a.getTotalQty());
				/*
				 * newMarketPrice = comp1.getLastPrice() -
				 * ((comp1.getLastPrice() * comp1.getSpread()) / 100);
				 * 
				 * comp1.setLastPrice(newMarketPrice); double
				 * lastPrice=comp1.getLastPrice();
				 * 
				 * sec.UpdateLastPrice(lastPrice,symbol);
				 */// Updating database with new Price
				double lastPrice = comp1.getLastPrice();
				int update;
				update = a.getTotalQty() - quantity;
				System.out.println(i + "sad");
				System.out.println(lastPrice);

				if (a.getLimitPrice() == lastPrice) {
					a.setTotalQty(update);
					System.out.println("sad");
					Fill f = new Fill();
					list.add(i, f);
					list.get(i).blockId = a.getBlockId();
					// list.get(i).blockId = a.blockId;
					list.get(i).executedQuantity = quantity;
					list.get(i).openQuantity = update;
					list.get(i).executionPrice = lastPrice;
					list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
					list.get(i).side = a.getSide();
					list.get(i).symbol = a.getSymbol();

					fill.save(list.get(i));
					jms.convertAndSend("Fills", f);
					i++;
					isNextIteration = isNextIterationTrue(comp1);

					newMarketPrice = comp1.getLastPrice() - ((comp1.getLastPrice() * comp1.getSpread()) / 100);

					comp1.setLastPrice(newMarketPrice);
					lastPrice = comp1.getLastPrice();

					sec.UpdateLastPrice(lastPrice, symbol);
				}

				else {

					i++;
					isNextIteration = isNextIterationTrue(comp1);
					newMarketPrice = comp1.getLastPrice() - ((comp1.getLastPrice() * comp1.getSpread()) / 100);

					comp1.setLastPrice(newMarketPrice);
					lastPrice = comp1.getLastPrice();

					sec.UpdateLastPrice(lastPrice, symbol);

				}

			}

		} else if (a.getLimitPrice() >= comp1.getLastPrice()) {

			while (i < iterations && a.getTotalQty() > 0 && isNextIteration == true) {
				int quantity = quantityCalculator(a.getTotalQty());
				/*
				 * newMarketPrice = comp1.getLastPrice() +
				 * ((comp1.getLastPrice() * comp1.getSpread()) / 100);
				 * 
				 * comp1.setLastPrice(newMarketPrice); double
				 * lastPrice=comp1.getLastPrice();
				 * 
				 * System.out.println(lastPrice+"LastPriceFromExchange"+" "
				 * +a.getLimitPrice()); sec.UpdateLastPrice(lastPrice,symbol);
				 */// Updating database with new Price
				double lastPrice = comp1.getLastPrice();
				int update;
				update = a.getTotalQty() - quantity;

				System.out.println(i + "ja");
				if (a.getLimitPrice() == lastPrice) {
					System.out.println("Fill from execution");
					a.setTotalQty(update);
					Fill f = new Fill();
					list.add(i, f);
					list.get(i).blockId = a.getBlockId();
					// list.get(i).blockId = a.blockId;
					list.get(i).executedQuantity = quantity;
					list.get(i).openQuantity = update;
					list.get(i).executionPrice = lastPrice;
					list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
					list.get(i).side = a.getSide();
					list.get(i).symbol = a.getSymbol();

					fill.save(list.get(i));
					i++;
					isNextIteration = isNextIterationTrue(comp1);
					newMarketPrice = comp1.getLastPrice() + ((comp1.getLastPrice() * comp1.getSpread()) / 100);

					comp1.setLastPrice(newMarketPrice);
					lastPrice = comp1.getLastPrice();

					System.out.println(lastPrice + "LastPriceFromExchange" + "    " + "  " + a.getLimitPrice());
					sec.UpdateLastPrice(lastPrice, symbol);
					jms.convertAndSend("Fills", f);
				}

				else {

					i++;
					isNextIteration = isNextIterationTrue(comp1);
					newMarketPrice = comp1.getLastPrice() + ((comp1.getLastPrice() * comp1.getSpread()) / 100);

					comp1.setLastPrice(newMarketPrice);
					lastPrice = comp1.getLastPrice();

					System.out.println(lastPrice + "LastPriceFromExchange" + " " + a.getLimitPrice());
					sec.UpdateLastPrice(lastPrice, symbol);

				}

			}

		}

	}
	// __________________________________________________________________________________________

	private int iterationCount(Security a) {
		int min = 1;
		int max = a.getExecNo();

		Random r = new Random();
		int count = r.nextInt(max - min) + min;

		return count;
	}

	private int quantityCalculator(int a) {

		Random r = new Random();
		int quantity = r.nextInt(a);

		return quantity;

	}

	private boolean isNextIterationTrue(Security a) {

		Random r = new Random();
		int prob = r.nextInt(100);

		if (prob > (100 - a.getProb())) {

			return true;

		}

		return false;
	}

	public void executeOrder(Block a, FillServices fill, SecurityService sec,JmsTemplate jms) {

		// Block b = new Block();
		// b = a;
		System.out.println(a.toString());

		// Security comp = sec.SearchBySymbol(symbol);
		// Security comp=sec.findSecurityBySymbol(symbol);
		String symbol = a.getSymbol();
		System.out.println(symbol);
		System.out.println(sec);
		Security comp = sec.filterBySymbol(symbol);
		System.out.println(comp);
		// System.out.println("null check"+comp==null);
		// search for the symbol in the database and return an object of
		// Security table
		int iteration = iterationCount(comp);
		System.out.println(iteration);
		String market = "MARKET";
		String buy = "BUY";
		String limit = "LIMIT";
		String sell = "SELL";
		System.out.println(a.getBlockOrderType()+a.getBlockOrderType().equals(market));
		if (a.getBlockOrderType().equals(market) && a.getSide().equals(buy)) // Check
																				// for
																				// type
		// of order,4
		// different
		// functions for
		// market order
		// and limit
		// order
		// respectively
		{
			System.out.println("Market ");
			marketBuyExecute(a, symbol, iteration, fill, sec,jms);

		}
		if (a.getBlockOrderType().equals(market) && a.getSide().equals(sell)) {
			marketSellExecute(a, symbol, iteration, fill, sec,jms);

		}

		if (a.getBlockOrderType().equals(limit)) {
			limitBuyExecute(a, symbol, iteration, fill, sec,jms);

		}

	}

	// __________________________________________________________________________________________

	private void StopBuyExecute(Block a, String symbol, int iterations, FillServices fill, SecurityService sec, JmsTemplate jms) {

		System.out.println("StopBuyExecute");
		System.out.println(iterations);

		int i = 0;

		ArrayList<Fill> list = new ArrayList<Fill>();// Creating a list of
														// Fill type for
														// committing it
														// into Fill table
														// in database
		boolean isNextIteration = true; // Next Iteration decider is set to
		Security comp1 = sec.filterBySymbol(symbol); // true by default

		while (a.getStopPrice() > comp1.getLastPrice() && i <= iterations && isNextIteration == true) { // Checking
			// to
			Security comp = sec.filterBySymbol(symbol); // see
			// if
			// it
			// is
			// a
			// stop
			// order
			// and
			// trying
			// to
			// calculate
			// it
			double newMarketPrice = comp.getLastPrice() + ((comp.getLastPrice() * comp.getSpread()) / 100);

			comp.setLastPrice(newMarketPrice);
			comp1.setLastPrice(newMarketPrice);
			double lastPrice = comp.getLastPrice();
			System.out.println(newMarketPrice);
			System.out.println(a.getStopPrice());
			sec.UpdateLastPrice(lastPrice, symbol);// Updating database with new
													// Price
			i++;
			isNextIteration = isNextIterationTrue(comp1);
			if (isNextIteration == true) {
				i = 0;
			}
			System.out.println(isNextIteration);
			System.out.println(i);
		}

		while (i < iterations && a.getTotalQty() > 0 && isNextIteration == true) {

			System.out.println("In the main loop of StopBuy");

			Security comp = sec.filterBySymbol(symbol);
			int askQuantity = a.getTotalQty();
			int quantity = quantityCalculator(askQuantity);
			// Calculating
			// maximum
			double lastPrice = comp.getLastPrice(); // minimum
			System.out.println(lastPrice); // deviation
			// a
			// security
			// ca // through
			sec.UpdateLastPrice(lastPrice, symbol);// Updating database with new
													// Price
			int update;
			update = a.getTotalQty() - quantity;
			a.setTotalQty(update);
			if (a.getTotalQty() == 0) {
				String updateStatus = "FULL";

				a.setStatus(updateStatus);

				Fill f = new Fill();
				list.add(i, f);

				list.get(i).blockId = a.getBlockId();
				// list.get(i).blockId = a.blockId;
				list.get(i).executedQuantity = quantity;

				list.get(i).openQuantity = update;

				list.get(i).executionPrice = lastPrice;

				list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
				list.get(i).side = a.getSide();
				list.get(i).symbol = a.getSymbol();
				fill.save(list.get(i));
				jms.convertAndSend("Fills", f);
				// send message to trader here
				break;
			}

			Fill f = new Fill();

			list.add(f);
			System.out.println(i + "value of i");
			list.get(i).blockId = a.getBlockId();
			// list.get(i).blockId = a.blockId;
			list.get(i).executedQuantity = quantity;

			list.get(i).openQuantity = update;

			list.get(i).executionPrice = lastPrice;
			System.out.println(lastPrice);
			list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
			list.get(i).side = a.getSide();
			list.get(i).symbol = a.getSymbol();

			fill.save(list.get(i));
			jms.convertAndSend("Fills", f);
			// commit it into database here
			// send message to trader here
			isNextIteration = isNextIterationTrue(comp);

			i++;

		}

	}

	// __________________________________________________________________________________________

	private void StopSellExecute(Block a, String symbol, int iterations, FillServices fill, SecurityService sec, JmsTemplate jms) {

		int i = 0;

		ArrayList<Fill> list = new ArrayList<Fill>();// Creating a list of
														// Fill type for
														// committing it
														// into Fill table
														// in database
		boolean isNextIteration = true; // Next Iteration decider is set to
		Security comp1 = sec.filterBySymbol(symbol); // true by default

		while (a.getStopPrice() < comp1.getLastPrice() && i <= iterations && isNextIteration == true) { // Checking
			// to
			Security comp = sec.filterBySymbol(symbol); // see
			// if
			// it
			// is
			// a
			// stop
			// order
			// and
			// trying
			// to
			// calculate
			// it
			double newMarketPrice = comp.getLastPrice() - ((comp.getLastPrice() * comp.getSpread()) / 100);

			comp.setLastPrice(newMarketPrice);
			comp1.setLastPrice(newMarketPrice);
			double lastPrice = comp.getLastPrice();
			System.out.println(newMarketPrice);
			System.out.println(a.getStopPrice() + "hello2");
			sec.UpdateLastPrice(lastPrice, symbol);// Updating database with new
													// Price
			i++;
			isNextIteration = isNextIterationTrue(comp1);
			if (isNextIteration == false && i >= iterations) {
				i = 0;
			}
		}

		while (i < iterations && a.getTotalQty() > 0 && isNextIteration == true) {

			System.out.println("In the main loop");

			Security comp = sec.filterBySymbol(symbol);
			int askQuantity = a.getTotalQty();
			int quantity = quantityCalculator(askQuantity);
			// Calculating
			// maximum
			double lastPrice = comp.getLastPrice(); // minimum
			System.out.println(lastPrice); // deviation
			// a
			// security
			// ca // through
			sec.UpdateLastPrice(lastPrice, symbol);// Updating database with new
													// Price
			int update;
			update = a.getTotalQty() - quantity;
			a.setTotalQty(update);
			if (a.getTotalQty() == 0) {
				String updateStatus = "FULL";

				a.setStatus(updateStatus);

				Fill f = new Fill();
				list.add(i, f);

				list.get(i).executionId = i + 1;
				// list.get(i).blockId = a.blockId;
				list.get(i).blockId = a.getBlockId();
				list.get(i).executedQuantity = quantity;

				list.get(i).openQuantity = a.getTotalQty() - quantity;
				System.out.println(lastPrice);
				list.get(i).executionPrice = lastPrice;

				list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());

				fill.save(list.get(i));
				jms.convertAndSend("Fills", f);
				// send message to trader here
				break;
			}

			Fill f = new Fill();

			list.add(f);

			list.get(i).blockId = a.getBlockId();
			// list.get(i).blockId = a.blockId;
			list.get(i).executedQuantity = quantity;

			list.get(i).openQuantity = update;

			list.get(i).executionPrice = lastPrice;
			System.out.println(lastPrice);
			list.get(i).timestamp = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
			list.get(i).side = a.getSide();
			list.get(i).symbol = a.getSymbol();

			fill.save(list.get(i));
			jms.convertAndSend("Fills", f);
			// commit it into database here
			// send message to trader here
			isNextIteration = isNextIterationTrue(comp);

			i++;

		}

	}

}
