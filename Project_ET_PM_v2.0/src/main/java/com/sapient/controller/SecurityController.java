package com.sapient.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapient.dbHandler.BlockRepository;
import com.sapient.entities.Security;
import com.sapient.services.BrService;
import com.sapient.services.SecurityService;
import com.sapient.jms.*;
import com.sapient.moneytree.executionTrader.domain.Block;

/**
 * List all the securities present in the system add more securities to the
 * database
 *
 */
@Controller
public class SecurityController {

	private SecurityService fetchSec;

	private StoreService storeservice;

	private BrokerSendsFill broker;

	private InListener listener;
	private BrService br;

	@Autowired
	public void setFetchSec(SecurityService fetchSec) {
		this.fetchSec = fetchSec;
	}

	@Autowired
	public void setBr(BrService br) {
		this.br = br;
	}

	public void setBlockrep(BlockRepository blockrep) {
		this.blockrep = blockrep;
	}

	private BlockRepository blockrep;

	@Autowired
	public void setBlockRepository(BlockRepository blockrep) {
		this.blockrep = blockrep;
	}

	@Autowired
	public void setFetch_sec(SecurityService fetch_sec) {
		this.fetchSec = fetch_sec;
	}

	@Autowired
	public void setBroker(BrokerSendsFill broker) {
		this.broker = broker;
	}

	public StoreService getStoreservice() {
		return storeservice;
	}

	@Autowired
	public void setStoreservice(StoreService storeservice) {
		this.storeservice = storeservice;
	}

	@Autowired
	public void setListener(InListener listener) {
		this.listener = listener;
	}

	/**
	 * method to list all securities
	 * 
	 * @return the page named BrokerHome
	 */
	@RequestMapping("BrokerHome1")
	public String list(Model model) {
		model.addAttribute("Sec", fetchSec.listAllSecurities());
	/*	model.addAttribute("User", br.getByBrId(id));*/

		//
		// Block blockObj;
		// blockObj= storeservice.getReceivedOrder("0");
		// blockrep.save(blockObj);

		// System.out.println(storeservice.getReceivedOrder("0"));

		/*
		 * Block b1= new Block(); int blockId=
		 * storeservice.getReceivedOrder("0").getBlockId();
		 * b1.setBlockId(blockId);
		 */

		// List<Block> b= storeservice.getBlocklist();
		//
		// System.out.println(b.size());

		return "BrokerHome";
	}

	/**
	 * 
	 * @return the page named ConfigureSecurities
	 */
	@RequestMapping(value = "/ConfigureSecurities3", method = RequestMethod.POST)
	public String list1(Model model) {
		System.out.println("in controllerr");
		model.addAttribute("Sec", fetchSec.listAllSecurities());
		return "redirect:/ConfigureSecurities1";

	}

	@RequestMapping("/ConfigureSecurities1")
	public String list2(Model model) {
		model.addAttribute("Security", fetchSec.listAllSecurities());
		/* model.addAttribute("Sec", fetchSec.listAllSecurities()); */
		return "ConfigureSecurities";

	}

	@RequestMapping(value = "/ConfigureSecurities2", method = RequestMethod.POST)
	String create(Model model, @RequestParam("1") String _1, @RequestParam("2") String _2, @RequestParam("3") Double _3,
			@RequestParam("4") Double _4, @RequestParam("5") Integer _5, @RequestParam("6") Double _6,
			@RequestParam("7") Double _7) {

		/* if(_4||_5||_6||_7) */
		fetchSec.UpdateSecurities(_4, _5, _6, _7, _2);
		return "redirect:/ConfigureSecurities1";
	}

	@RequestMapping("AddSecurities1")
	String addsecurity(Model model) {

		return "AddSecurities";
	}

	/**
	 * Method to add up new securities to the system
	 * 
	 * @param secSymbol
	 *            This refers to security symbol
	 * @param secName
	 *            This refers to security name
	 * @param lastPrice
	 *            This refers to security's last trade price
	 * @param spread
	 *            This refers to security's price variation percentage
	 * @param execNo
	 *            This is number of executions of every block of a particular
	 *            security
	 * @param interval
	 *            This is waiting time after every execution of block
	 * @param prob
	 *            This is probability of complete execution of a block
	 * @return The page named AddSecurities
	 */
	@RequestMapping(value = "/AddSecurities", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	String createNew(Model model, @RequestParam("1") String secSymbol, @RequestParam("2") String secName,
			@RequestParam("3") Double lastPrice, @RequestParam("4") Double spread, @RequestParam("5") Integer execNo,
			@RequestParam("6") Double interval, @RequestParam("7") Double prob) {

		Security s1 = new Security();
		s1.setSecSymbol(secSymbol);
		s1.setSecName(secName);
		s1.setLastPrice(lastPrice);
		s1.setSpread(spread);
		s1.setExecNo(execNo);
		s1.setInterval(interval);
		s1.setProb(prob);
		fetchSec.saveSecurity(s1);

		return "AddSecurities1";

	}

	@RequestMapping(value = "/Start", method = RequestMethod.POST)
	String Start(Model model) {
		System.out.println("Start");
		model.addAttribute("Sec", fetchSec.listAllSecurities());
		String start = "Start";
		broker.send_Start(start);
		return "BrokerHome";

	}

	@RequestMapping(value = "/Stop", method = RequestMethod.POST)
	String Stop(Model model) {

		System.out.println("Stop");

		model.addAttribute("Sec", fetchSec.listAllSecurities());
		String stop = "Stop";
		broker.send_Stop(stop);

		//
		// try {
		// listener.wait(10000);
		// } catch (InterruptedException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		return "BrokerHome";
	}

	public BrokerSendsFill getBroker() {
		return broker;
	}

	public InListener getListener() {
		return listener;
	}

	@RequestMapping(value = "/Search", method = RequestMethod.POST)
	public String SecurityDropDown(Model model, @RequestParam("symbol") String secSymbol) {

		model.addAttribute("Security99", fetchSec.listAllSecurities());

		model.addAttribute("Securities", fetchSec.filterBySymbol(secSymbol));

		return "ConfigureSecurities";
	}

	/*
	 * @RequestMapping(value = "/ConfigureSecurities6") public String
	 * Security1DropDown(Model model) { System.out.println(Securities);
	 * model.addAttribute("Security", fetchSec.listAllSecurities());
	 * 
	 * model.addAttribute("Security1", fetchSec.listAllSecurities());
	 * 
	 * return "ConfigureSecurities"; }
	 */
}
