package com.sapient.moneytree.executionTrader.Controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.moneytree.Literals.OrderStatus;
import com.sapient.moneytree.executionTrader.JMS.TraderSends;
import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.executionTrader.service.FetchBlock;
import com.sapient.moneytree.executionTrader.service.FetchOrder;
import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;

/**
 * Interface for maintaining Trader's Orders and building appropriate model and
 * passes it to the view for rendering. It is used to navigate between creating
 * new blocks, viewing orders, blocks and sending orders for execution.
 * 
 * @author sbha53
 *
 */
@Controller
public class OrderController {

	final static Logger logger = Logger.getLogger(OrderController.class.getName());
	private FetchOrder fetch_orders;
	private FetchBlock fetch_Block;
	private TraderSends traderSends;
	PortfolioManager traderObj;

	/**
	 * 
	 * @param fetch_orders
	 */
	@Autowired
	public void setFetch_orders(FetchOrder fetch_orders) {
		this.fetch_orders = fetch_orders;
	}

	/**
	 * 
	 * @param traderSends
	 */
	@Autowired
	public void setTraderSends(TraderSends traderSends) {
		this.traderSends = traderSends;
	}

	/**
	 * 
	 * @param fetch_Block
	 */
	@Autowired
	public void setFetch_Block(FetchBlock fetch_Block) {
		this.fetch_Block = fetch_Block;
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("order_blotter")
	String orderBlotter(Model model) {
		//traderObj=(PortfolioManager)request.getAttribute("trader");
//		System.out.println(traderObj.getFname());
		logger.info("Inside Order Blotter");
		//model.addAttribute("trader1",traderObj);
		model.addAttribute("Orders", fetch_orders.listByStatus(OrderStatus.Open));
		model.addAttribute("symbolUpdated", fetch_orders.symbolFind()); // Added
		System.out.println(fetch_orders.listByStatus(OrderStatus.Open));
		return "orderBlotter";
	}

	/**
	 * 
	 * @param model
	 * @param myValues
	 * @return
	 */
	@RequestMapping("/blockBlotter")
	String blockBlotter(Model model, @RequestParam("openOrderList") String[] myValues) {

		// Changing status of orders to "Blocked"
		for (int i = 0; i < myValues.length; i++) {
			//fetch_orders.getOrdersById(Integer.parseInt(myValues[i]));
			fetch_orders.updateOrders("Blocked", Integer.parseInt(myValues[i]));
		}

		Block b = new Block();
		ArrayList<Double> list = new ArrayList<>();
		ArrayList<Double> list1 = new ArrayList<>();
		Orders order = null;
		int sum = 0;
		for (int i = 0; i < myValues.length; i++) {
			b.getOrderList().add(fetch_orders.getOrdersById(Integer.parseInt(myValues[i])));

			// find min limit price
			order = fetch_orders.getOrdersById(Integer.parseInt(myValues[i]));
			list.add(order.getLprice());
			list1.add(order.getSprice());
			sum += order.getQty();
		}

		for (Double x : list) {
			System.out.println("# " + x);
		}

		// add an exception here if in case order becomes null
		if (order.getOrderSide().compareToIgnoreCase("buy") == 0) {
			System.out.println("MIN " + Collections.min(list));
			b.setLimitPrice(Collections.min(list));
		}
		if (order.getOrderSide().compareToIgnoreCase("sell") == 0) {
			System.out.println("MAX " + Collections.max(list));
			b.setLimitPrice(Collections.max(list));
		}
		
		
		
		if (order.getOrderSide().compareToIgnoreCase("buy") == 0) {
			System.out.println("MIN " + Collections.min(list1));
			b.setStopPrice(Collections.min(list1));
		}
		if (order.getOrderSide().compareToIgnoreCase("sell") == 0) {
			System.out.println("MAX " + Collections.max(list1));
			b.setStopPrice(Collections.max(list1));
		}

		b.setStatus("New");
		b.setSymbol(order.getSymbol());
		b.setSide(order.getOrderSide());
		b.setBlockOrderType(order.getOrderType());
		b.setTotalQty(sum);
		b.setBlockQualifier(order.getOrderQualifier());
		//b.setStopPrice();
		fetch_Block.saveBlock(b);
		logger.info("Block Saved");
		model.addAttribute("Block", fetch_Block.listByStatus("New"));

		for (int i = 0; i < myValues.length; i++) {
			fetch_orders.updateBlockId(b.getBlockId(), Integer.parseInt(myValues[i]));
		}

		return "Trader";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("block_blotter")
	public String blockBlotView(Model model) {
		model.addAttribute("Block", fetch_Block.listByStatus("New"));
		return "Trader";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("execution_blotter")
	public String executeView(Model model) {
		model.addAttribute("Block", fetch_Block.listByStatus("Send For Execution"));
		model.addAttribute("Block1", fetch_Block.listByStatus("Complete"));
		model.addAttribute("Block2", fetch_Block.listByStatus("Partially Executed"));
		return "executionBlotter";
	}

	/**
	 * 
	 * @param model
	 * @param myValues
	 * @return
	 */
	@RequestMapping("/SendForExecution")
	public String SendForExecution(Model model, @RequestParam("BlockList") String[] myValues) {

		for (int i = 0; i < myValues.length; i++) {
			fetch_Block.updateBlock("Send For Execution", Integer.parseInt(myValues[i]));
		}
		
		List<Block> block = (List<Block>) fetch_Block.listByStatus("Send For Execution");
		for(int j=0;j<block.size();j++)
		{
		
		List<Orders> orderlist=fetch_orders.listbystatusandId("Blocked", block.get(j).getBlockId());
		
		for(int i=0;i<orderlist.size();i++)
		{
			fetch_orders.updateOrders("Send For Execution", orderlist.get(i).getOrderId());
			
		}
		
		}
		for (int i = 0; i < myValues.length; i++) {
			System.out.println(Integer.parseInt(myValues[i]));
			Block b = fetch_Block.getBlockById(Integer.parseInt(myValues[i]));

			System.out.println(b);
			traderSends.send_Object(b);
		}
		//int openQty=786;

		// Block b = fetch_Block.getBlockById(1);
		// fetch_Block.updateBlock("Send For Execution", b.getBlockId());

		model.addAttribute("Block", fetch_Block.listByStatus("Send For Execution"));
		model.addAttribute("Block1", fetch_Block.listByStatus("Complete"));
		model.addAttribute("Block2", fetch_Block.listByStatus("Partially Executed"));
		//fetch_Block.UpdateBlockOpenAfterAutoAllocation(openQty, Integer.parseInt(myValues[0]));
		return "executionBlotter";

	}

	/**
	 * 
	 * @param model
	 * @param Side
	 * @param Type
	 * @param Symbol
	 * @return
	 */
	// Added by Saurav - Starts
	@RequestMapping("/getFilter")
	public String FilterRecords(Model model, @RequestParam("sel1") String Side, @RequestParam("sel2") String Type,
			@RequestParam("sel3") String Symbol) {
		model.addAttribute("symbolUpdated", fetch_orders.symbolFind());
		System.out.println("$" + Side);
		System.out.println("$" + Type);
		System.out.println("$" + Symbol);
		model.addAttribute("Filters", fetch_orders.FilterOrders(Side, Type, Symbol));
		return "orderBlotter";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/resetOrder")
	public String ResetOrders(Model model) {
		model.addAttribute("Orders", fetch_orders.listByStatus(OrderStatus.Open));
		model.addAttribute("symbolUpdated", fetch_orders.symbolFind());
		return "orderBlotter";
	}

	/**
	 * 
	 * @param model
	 * @param blockId
	 * @return
	 */
	@RequestMapping("/Master")
	public String Master(Model model, @RequestParam("foo") Integer blockId) {
		System.out.println("%" + blockId);
		model.addAttribute("Block_Orders", fetch_orders.OrdersFind(blockId));

		model.addAttribute("Block", fetch_Block.listByStatus("New"));
		return "Trader";
	}

	/**
	 * 
	 * @param model
	 * @param blockId
	 * @return
	 */
	@RequestMapping("/MasterExecution")
	public String MasterExecution(Model model, @RequestParam("foo") Integer blockId) {
		System.out.println("%" + blockId);
		System.out.println("HERE");
		model.addAttribute("Block_Orders", fetch_orders.OrdersFind(blockId));

		model.addAttribute("Block", fetch_Block.listByStatus("Send For Execution"));
		model.addAttribute("Block1", fetch_Block.listByStatus("Complete"));
		model.addAttribute("Block2", fetch_Block.listByStatus("Partially Executed"));

		return "executionBlotter";
	}
	// Added by Saurav - Ends
}
