package com.sapient.moneytree.portfolioManager.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sapient.moneytree.Literals.OrderDeletionStatus;
import com.sapient.moneytree.Literals.OrderSide;
import com.sapient.moneytree.Literals.OrderStatus;
import com.sapient.moneytree.Literals.OrderType;
import com.sapient.moneytree.Literals.OrderValidity;
import com.sapient.moneytree.Literals.UserRole;
import com.sapient.moneytree.exceptions.InvalidOrderSideException;
import com.sapient.moneytree.exceptions.InvalidOrderTypeException;
import com.sapient.moneytree.exceptions.InvalidOrderValidityException;
import com.sapient.moneytree.exceptions.InvalidPasswordException;
import com.sapient.moneytree.exceptions.InvalidPriceException;
import com.sapient.moneytree.exceptions.InvalidQuantityException;
import com.sapient.moneytree.exceptions.InvalidSymbolException;
import com.sapient.moneytree.exceptions.InvalidTraderNameException;
import com.sapient.moneytree.exceptions.InvalidUserIdException;
import com.sapient.moneytree.executionTrader.service.FetchBlock;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.HoldingsDatabaseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.OrderCommunicatorDatabaseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.OrderDatabaseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.PMDatabseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.SecurityDatabaseInterface;
import com.sapient.moneytree.portfolioManager.createOrder.autoSuggest.DataCache;
import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;
import com.sapient.moneytree.portfolioManager.domain.Securities;
import com.sapient.moneytree.portfolioManager.service.PMService;

/**
 * Responsible for processing Portfolio Manager's requests and building
 * appropriate model and passes it to the view for rendering.
 * 
 * @author Shubhankar Mayank
 * @author Neha Maheshwari
 * @author Sarthak Jain
 * @author Ankush Tyagi
 * @author Vinod Choudhry
 *
 */

@Controller
public class PMController {
	final static Logger logger = Logger.getLogger(PMController.class);
	private PMService pmService;
	private HoldingsDatabaseInterface holdingDBInterface;
	private OrderDatabaseInterface orderDBInterface;
	private SecurityDatabaseInterface securityDBInterface;

	private PMDatabseInterface pmDBInterface;// object of PMDatabaseInterface

	PortfolioManager pm; // object of Portfolio Manager
	// --------ET code
	private FetchBlock fetch_Block;

	@Autowired
	public void setFetch_Block(FetchBlock fetch_Block) {
		this.fetch_Block = fetch_Block;
	}

	// ------ET code
	@Autowired
	public void setPMService(PMService pmService) {
		this.pmService = pmService;

	}

	@Autowired
	public void setPMRepository(PMDatabseInterface pmRepository) {
		this.pmDBInterface = pmRepository;

	}

	@Autowired
	public void setHoldingRepository(HoldingsDatabaseInterface holdingRepository) {
		this.holdingDBInterface = holdingRepository;
	}

	@Autowired
	public void setOrderRepository(OrderDatabaseInterface orderRepository) {
		this.orderDBInterface = orderRepository;
	}

	@Autowired
	public void setSecurityRepository(SecurityDatabaseInterface securityRepository) {
		this.securityDBInterface = securityRepository;
	}

	/**
	 * method used for validation of user and returning back the user to a
	 * specific page
	 * 
	 * @param model
	 * @param PMId
	 *            PMId is portfolio manager's Id
	 * @param password
	 *            password is portfolio manager's password
	 * @param uId
	 *            uId is user Id used at the time of login.(same as portfolio
	 *            manager's id )
	 * @return * returns portfolio page if valid user returns invalid if invalid
	 *         user id or password
	 * 
	 */

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(Model model, @RequestParam("uId") String PMId, @RequestParam("password") String password) {
		pm = new PortfolioManager();
		try {
			try {
				pm = pmService.getPMById(new Integer(PMId));
				if (pm == null)
					throw new InvalidUserIdException();
			} catch (Exception e) {
				throw new InvalidUserIdException();
			}
			if ("".equals(password) || password == null) {
				throw new InvalidPasswordException();
			}

		} catch (InvalidUserIdException e) {
			System.out.println(e.toString());
			return "indexInvalid";
		} catch (InvalidPasswordException e) {
			System.out.println(e.toString());
			return "indexInvalid";
		}

		if (validateUser(pm, password)) {
			if (getUserRole(pm) == UserRole.PortfolioManager) {
				model = getModel(model);
				logger.info("User is validated and is a Portfolio Manager");

				return "portfolio";
			}
			/* other roles here */
			else if (getUserRole(pm) == UserRole.Trader) {
				// model.addAttribute("trader",pm);
				logger.info("User is validated and is a Trader");
				return "redirect:order_blotter";
				// return "forward:order_blotter";

			} else if (getUserRole(pm) == UserRole.PortfolioManagerAndTrader) {
				logger.info("User is validated and is a Portfolio Manager and a Trader both");
				return "selector";

			}

		}
		return "indexInvalid";
	}

	/**
	 * 
	 * @param model
	 * @return returns portfolio page
	 */
	@RequestMapping(value = "/pmLogin")
	public String pmLogin(Model model) {
		model = getModel(model);
		logger.info("PM login");
		return "portfolio";
	}

	/**
	 * 
	 * @param model
	 * @return returns orderBlotter page
	 */
	@RequestMapping(value = "/traderLogin")
	public String traderLogin(Model model) {
		// model.addAttribute("trader",pm);
		logger.info("Trader login");
		// return "orderBlotter";
		return "redirect:order_blotter";
	}

	/**
	 * when logout is requested
	 * 
	 * @param model
	 * @return returns login page
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model) {

		pm = null;
		System.out.println("logged out");
		logger.info("Sucessful logout from the application");
		return "index";
	}

	/**
	 * method for reset password
	 * 
	 * @param model
	 * @return returns ResetPassword page
	 */
	@RequestMapping("/ResetPassword")
	public String resetPassword(Model model) {
		logger.info("Password is reset");
		return "ResetPassword";
	}

	/**
	 * 
	 * @param model
	 * @param Uid
	 *            is the user id
	 * @param Newpassword
	 *            is the new password entered by the user
	 * @return returns login page
	 */
	@RequestMapping("/forgotPassword")
	public String forgotPassword(Model model, @RequestParam("id") String Uid,
			@RequestParam("ChangePass") String Newpassword) {

		PortfolioManager pm = pmService.getPMById(new Integer(Uid));
		pmDBInterface.setPasswordFor(pm.getpMId(), Newpassword);
		logger.info("Forgot Password Service Activated");
		return "index";
	}

	/**
	 * retrieves selected orders and passes values to sendOrder
	 * 
	 * @param model
	 * @param selection
	 *            selection contains orders retrieved by pm to send to the
	 *            trader
	 * @return *returns Success if send to trader is successful
	 * 
	 */

	@RequestMapping(value = "/sendToTrader", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String sendToTrader(Model model, @RequestParam("selection") String selection) {

		String status = sendOrder(selection.split(","));
		logger.info("Order is send to Trader for execution.");
		return status;
	}

	/**
	 * method to return stock name according to the substring entered
	 * 
	 * @param name
	 *            is the string entered in the search box during creation of
	 *            order
	 * @return returns matched stock name
	 */

	@RequestMapping(value = "/getMachedNames", method = RequestMethod.GET)
	public @ResponseBody List<String> getMachedNames(@RequestParam("term") String name) {
		DataCache d = new DataCache(securityDBInterface.findAll());
		List<String> matchName = d.getName(name);
		logger.info("A security was found with the symbol entered");
		return matchName;
	}

	/**
	 * 
	 * @param model
	 * @param search
	 * @param orderType
	 *            orderType is type of the order (Market/Limit)
	 * @param traderName
	 *            is trader name selected by the user
	 * @param orderSide
	 *            is the side selected(Buy/Sell)
	 * @param orderValidity
	 *            is the validity selected(GTC/GTD)
	 * @param qty
	 *            is the quantity entered by the pm
	 * @param price
	 *            market price of stock
	 * @param sprice
	 *            stop price entered by the pm
	 * @param lprice
	 *            limit price entered by the pm
	 * @return returns hello if creation is successful
	 * @throws InvalidSymbolException
	 *             if the symbol entered is not valid or null
	 * @throws InvalidOrderTypeException
	 *             if the order type is not selected
	 * @throws InvalidQuantityException
	 *             if the quantity entered is 0 or negative
	 * @throws InvalidPriceException
	 *             if the price entered is 0 or negative
	 * @throws InvalidOrderValidityException
	 *             if order validity is not selected
	 * @throws InvalidOrderSideException
	 *             if side is invalid
	 * @throws InvalidTraderNameException
	 *             if the trader name is null
	 */

	@RequestMapping(value = "/createorder", method = RequestMethod.POST, produces = "text/plain")
	@ResponseBody
	String create(Model model, @RequestParam("search") String search, @RequestParam("orderType") String orderType,
			@RequestParam("traderName") String traderName, @RequestParam("orderSide") String orderSide,
			@RequestParam("orderValidity") String orderValidity, @RequestParam("qty") Double qty,
			@RequestParam("sprice") Double sprice, @RequestParam("lprice") Double lprice) {

		System.out.println(search + "," + orderType + "," + traderName + "," + orderSide + "," + orderValidity + ","
				+ qty + "," + sprice + "," + lprice);
		if (qty == null) {
			qty = 0.0;
		}
		/*
		 * if(price==null){ price=(double) 0.0; }
		 */
		if (lprice == null) {
			lprice = 0.0;
		}
		if (sprice == null) {
			sprice = 0.0;
		}
		if (OrderType.MARKET.equals(orderType) && lprice > 0.0) {
			return "Cannot acccept limit price in market order";
		}
		if (OrderType.LIMIT.equals(orderType) && sprice > 0.0) {
			return "Cannot acccept stop price in limit order";
		}
		if (OrderType.LIMIT.equals(orderType)) {
			sprice = 0.0;
		}

		if (OrderType.MARKET.equals(orderType)) {
			lprice = 0.0;
		}

		String status = validateOrder(search, orderType, traderName, orderSide, orderValidity, qty, sprice, lprice);
		System.out.println(status);
		if ("SUCCESS".equals(status))
			orderDBInterface.save(
					saveOrder(search, orderType, traderName, orderSide, orderValidity, qty, null, sprice, lprice));
		return status;

	}

	/**
	 * method deletes selected orders
	 * 
	 * @param model
	 * @param selection
	 *            selection contains orders retrieved by PM for deletion
	 * @return returns success if done
	 */

	@RequestMapping(value = "/deleteOrders", method = RequestMethod.POST, produces = "text/plain")
	public @ResponseBody String deleteOrders(Model model, @RequestParam("selection") String selection) {
		System.out.print(selection + "success");
		if ("".equals(selection))
			return OrderDeletionStatus.NONESELECTED;
		String status = deleteOrder(selection.split(","));
		logger.info("Order is sucessfully deleted");
		return status;
	}

	/**
	 * 
	 * @param model
	 * @param orderId
	 *            is the orderId of the selected order for modification
	 * @return returns editOrder page if status is NEW returns ammendOrder page
	 *         if status is OPEN returns portfolio is status if status is OUT
	 */

	@RequestMapping(value = "/editOrder", method = RequestMethod.POST)
	public String editOrders(Model model, @RequestParam("orderId") String orderId) {

		Orders orderObj = orderDBInterface.findByOrderId(new Integer(orderId));

		if (orderObj.getOrderStatus().trim().equals(OrderStatus.New)) {
			model = getModelForEditing(model, orderId);
			logger.info("Order is edited");
			return "editOrder";
		}
		if (orderObj.getOrderStatus().trim().equals(OrderStatus.Open)) {

			model = getModelForEditing(model, orderId);
			logger.info("Order is amended");
			return "ammendOrder";
		}
		System.out.println("out");
		model = getModelForEditing(model, orderId);
		logger.info("Status of order is changed accordingly");
		return "portfolio";
	}

	/**
	 * 
	 * @param model
	 * @param orderId
	 *            is the order Id generated for a particular order
	 * @param symbol
	 * @param orderType
	 * @param traderName
	 *            is trader name selected by the user
	 * @param orderSide
	 *            is the side selected(Buy/Sell)
	 * @param orderValidity
	 *            is the validity selected(GTC/GTD)
	 * @param qty
	 *            quantity for the entered order
	 * @param price
	 *            market price of stock
	 * @param sprice
	 *            stop price entered by the pm
	 * @param lprice
	 *            limit price entered by the pm
	 * @return returns portfolio page
	 */

	@RequestMapping(value = "/modifyorder", method = RequestMethod.POST)
	public String ModifyOrders(Model model, @RequestParam("orderId") String orderId,
			@RequestParam("symbol") String symbol, @RequestParam("orderType") String orderType,
			@RequestParam("traderName") String traderName, @RequestParam("orderSide") String orderSide,
			@RequestParam("orderValidity") String orderValidity, @RequestParam("qty") Double qty,
			@RequestParam("price") Double price, @RequestParam("sprice") Double sprice,
			@RequestParam("lprice") Double lprice) {
		System.out.print(orderId + "success");
		System.out.println("model");

		if (qty == null) {
			qty = 0.0;
		}
		/*
		 * if(price==null){ price=(double) 0.0; }
		 */
		if (lprice == null) {
			lprice = 0.0;
		}
		if (sprice == null) {
			sprice = 0.0;
		}

		if (OrderType.LIMIT.equals(orderType)) {
			sprice = 0.0;
		}

		if (OrderType.MARKET.equals(orderType)) {
			lprice = 0.0;
		}
		if (qty < 0 || sprice < 0 || lprice < 0) {
			return "portfolio";
		}

		orderDBInterface.modifyOrderFor(new Integer(orderId), symbol, orderType, traderName, orderSide, orderValidity,
				qty, price, sprice, lprice);// , orderType, traderName,
											// orderSide, orderValidity, qty,
											// price, sprice, lprice);
		model = getModel(model);
		logger.info("Order is modified");
		return "portfolio";
	}

	/**
	 * 
	 * @param model
	 * @param orderId
	 *            is the order id for the selected order
	 * @param qty
	 *            quantity for the selected order
	 * @return returns portfolio page
	 */

	@RequestMapping(value = "/ammendorder", method = RequestMethod.POST)
	public String ammendyOrders(Model model, @RequestParam("orderId") String orderId, @RequestParam("qty") Double qty) {
		System.out.print(orderId + "successammend");
		System.out.println("model");

		if (qty <= 0) {
			return "portfolio";
		}
		orderDBInterface.modifyQtyFor(new Integer(orderId), qty);// , orderType,
																	// traderName,
																	// orderSide,
																	// orderValidity,
																	// qty,
																	// price,
																	// sprice,
																	// lprice);
		model = getModel(model);
		return "portfolio";
	}

	/**
	 * 
	 * @param model
	 * @return returns model
	 */

	private Model getModel(Model model) {
		System.out.println("pm" + pm.getpMId());
		model.addAttribute("pm", pm);
		model.addAttribute("holdings", holdingDBInterface.findByPmId(new Integer(pm.getpMId())));
		// System.out.println("holding" + holdingRepository.findByPmId(new
		// Integer(pm.getpMId())).toString());
		model.addAttribute("orders", orderDBInterface.findByPmIdOrderByTimeStampDesc((pm.getpMId())));
		for (Orders element : orderDBInterface.findByPmIdOrderByTimeStampDesc(pm.getpMId())) {
			System.out.println(element.toString());
		}
		List<String> traderList = getTraderList(pmService.findByRole(UserRole.Trader));
		model.addAttribute("traders", traderList);
		return model;
	}

	/**
	 * 
	 * @param model
	 * @param orderId
	 *            is the order Id generated for a particular order
	 * @return returns model
	 */

	private Model getModelForEditing(Model model, String orderId) {

		model.addAttribute("orders", orderDBInterface.findByOrderId(new Integer(orderId.trim())));
		List<String> traderList = getTraderList(pmService.findByRole(UserRole.Trader));
		model.addAttribute("traders", traderList);
		return model;
	}

	/**
	 * 
	 * @param portFolioManagers
	 *            list of portfolio managers
	 * @return returns the trader list
	 */

	public ArrayList<String> getTraderList(List<PortfolioManager> portFolioManagers) {
		ArrayList<String> traderList = new ArrayList<String>();
		for (PortfolioManager portfolioManager : portFolioManagers) {
			String idAndFName = portfolioManager.getpMId() + "," + portfolioManager.getFname();
			traderList.add(idAndFName);
			System.out.println(idAndFName);
		}
		return traderList;
	}

	/**
	 * this method saves the order in the db
	 * 
	 * @param search
	 *            is the stock searched by the pm
	 * @param orderType
	 *            is type of the order (Market/Limit)
	 * @param traderName
	 *            is trader name
	 * @param orderSide
	 *            is the side selected(Buy/Sell)
	 * @param orderValidity
	 *            is the validity selected(GTC/GTD)
	 * @param qty
	 *            is the quantity of order entered by the pm
	 * @param price
	 *            market price of stock
	 * @param sprice
	 *            stop price entered by the pm
	 * @param lprice
	 *            limit price entered by the pm
	 * @return returns order
	 */

	public Orders saveOrder(String search, String orderType, String traderName, String orderSide, String orderValidity,
			Double qty, Double price, Double sprice, Double lprice) {
		Date d = new Date();
		Orders o = new Orders();
		o.setOrderSide(orderSide);
		o.setOrderStatus(OrderStatus.New);
		o.setOrderType(orderType);
		o.setPfId(1);
		o.setPmId(pm.getpMId());
		o.setPrice(0);
		o.setQty(qty);
		o.setAllocatedQty(0);
		o.setOpenQty(qty);
		Securities s = securityDBInterface.findBySymbol(search);
		// System.out.println(s.getCompany() + s.getSymbol());
		o.setSecurityName(s.getCompany());
		o.setSymbol(s.getSymbol());
		o.settId(new Integer((traderName.split(","))[0]));
		o.settName((traderName.split(","))[1]);
		o.setTimeStamp(d);
		o.setLprice(lprice);
		o.setSprice(sprice);
		o.setOrderValidity(orderValidity);
		// System.out.println(o.getSecurityName() + o.getSymbol());
		logger.info("Order is saved in the database.");
		return o;
	}

	/**
	 * method sends the order to the trader
	 * 
	 * @param ids
	 *            is used for order id selected by user to send the order
	 */

	private String sendOrder(String ids[]) {
		boolean noneSelected = true;
		boolean noNewOrder = true;
		boolean allSent = true;
		System.out.println(ids);
		for (String id : ids) {
			noneSelected = false;
			System.out.println("id" + id);
			Orders o = orderDBInterface.findByOrderId(new Integer(id.trim()));
			System.out.println("os" + o.getOrderStatus());
			if (o.getOrderStatus().equals(OrderStatus.New)) {
				noNewOrder = false;
				System.out.println("id" + id);
				orderDBInterface.setStatusFor(OrderStatus.Open, (o.getOrderId()));
			} else {
				allSent = false;
			}

		}
		if (noneSelected == true)
			return OrderDeletionStatus.NONESELECTED;
		if (noNewOrder == true)
			return OrderDeletionStatus.NONEWORDER;
		if (allSent == false)
			return OrderDeletionStatus.PARTIALSUCCESS;
		else
			return OrderDeletionStatus.SUCCESS;
	}

	/**
	 * 
	 * @param ids
	 *            contains the order id
	 */

	private String deleteOrder(String ids[]) {
		boolean noneSelected = true;
		boolean noNewOrder = true;
		boolean allDeleted = true;

		for (String id : ids) {
			System.out.println(id.substring(0, 3));
			if (id.substring(0, 3).equals("del")) {
				noneSelected = false;
				Orders o = orderDBInterface.findByOrderId(new Integer(id.trim().substring(3)));
				if (o.getOrderStatus().equals(OrderStatus.New)) {
					noNewOrder = false;
					orderDBInterface.setStatusFor(OrderStatus.Deleted, (o.getOrderId()));

				} else {
					allDeleted = false;
				}

			}

		}
		if (noneSelected == true)
			return OrderDeletionStatus.NONESELECTED;
		if (noNewOrder == true)
			return OrderDeletionStatus.NONEWORDER;
		if (allDeleted == false)
			return OrderDeletionStatus.PARTIALSUCCESS;
		else
			return OrderDeletionStatus.SUCCESS;
	}

	/**
	 * method validates user by matching PM's password from the db with the
	 * password entered by user
	 * 
	 * @param pm
	 *            contains details for Portfolio Manager
	 * 
	 * @param password
	 *            is the password entered by the user while logging in
	 *
	 * @return returns true if password matches
	 */

	public boolean validateUser(PortfolioManager pm, String password) {
		if (password.equals(pm.getPassword())) {
			return true;
		}
		return false;
	}

	/**
	 * @param pm
	 *            contains Portfolio Manager
	 * @return returns user role. 1 for PM ,2 for ET, 3 for both
	 */
	public int getUserRole(PortfolioManager pm) {
		return pm.getRole();
	}

	/**
	 * 
	 * this method validates the order during its creation and throws exception
	 * if any
	 * 
	 * @param search
	 *            is the stock searched by the pm
	 * @param orderType
	 *            is type of the order (Market/Limit)
	 * @param traderName
	 *            is trader name
	 * @param orderSide
	 *            is the side selected(Buy/Sell)
	 * @param orderValidity
	 *            is the validity selected(GTC/GTD)
	 * @param qty
	 *            is the quantity of order entered by the pm
	 * @param price
	 *            is market price of stock
	 * @param sprice
	 *            is stop price entered by the pm
	 * @param lprice
	 *            limit price entered by the pm
	 * @throws InvalidSymbolException
	 *             if the symbol entered is not valid or null
	 * @throws InvalidOrderTypeException
	 *             if the order type is not selected
	 * @throws InvalidQuantityException
	 *             if the quantity entered is 0 or negative
	 * @throws InvalidPriceException
	 *             if the price entered is 0 or negative
	 * @throws InvalidOrderValidityException
	 *             if order validity is not selected
	 * @throws InvalidOrderSideException
	 *             if side is invalid
	 * @throws InvalidTraderNameException
	 *             if the trader name is null
	 */

	private String validateOrder(String search, String orderType, String traderName, String orderSide,
			String orderValidity, Double qty, Double sprice, Double lprice) {
		try {
			if (search == null || search.equals("")) {
				throw new InvalidSymbolException();
			}
		} catch (InvalidSymbolException e) {
			return e.toString();
		}
		try {
			if (orderType.equals(OrderType.LIMIT) || orderType.equals(OrderType.MARKET)) {

			} else
				throw new InvalidOrderTypeException();
		} catch (InvalidOrderTypeException e) {
			return e.toString();
		}
		try {
			if (traderName == null || "".equals(traderName))
				throw new InvalidTraderNameException();
		} catch (InvalidTraderNameException e) {
			return e.toString();
		}
		try {
			if (qty <= 0 || qty % 1 != 0 || qty == null) {
				throw new InvalidQuantityException();
			}
		} catch (InvalidQuantityException e) {
			return e.toString();
		}
		/*
		 * try { if (price <= 0 || price.isNaN() || price.isInfinite()||
		 * price==null) { throw new InvalidPriceException(); } } catch
		 * (InvalidPriceException e) { return e.toString(); }
		 */
		try {
			if (orderValidity.equals(OrderValidity.GTD) || orderValidity.equals(OrderValidity.GTC)) {
			} else
				throw new InvalidOrderValidityException();
		} catch (InvalidOrderValidityException e) {
			return e.toString();
		}
		try {
			if (orderSide.equals(OrderSide.BUY) || orderSide.equals(OrderSide.SELL)) {
			} else
				throw new InvalidOrderSideException();
		} catch (InvalidOrderSideException e) {
			return e.toString();
		}
		try {
			if (sprice < 0 || sprice.isNaN() || sprice.isInfinite() || sprice == null) {
				throw new InvalidPriceException();
			}
		} catch (InvalidPriceException e) {
			return e.toString();
		}
		try {
			if (lprice < 0 || lprice.isNaN() || lprice.isInfinite() || lprice == null) {
				throw new InvalidPriceException();
			}
		} catch (InvalidPriceException e) {
			return e.toString();
		}

		return "SUCCESS";

	}

}
