package com.sapient;

import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sapient.moneytree.Literals.OrderSide;
import com.sapient.moneytree.Literals.OrderStatus;
import com.sapient.moneytree.Literals.OrderType;
import com.sapient.moneytree.Literals.OrderValidity;
import com.sapient.moneytree.Literals.UserRole;
import com.sapient.moneytree.executionTrader.DatabaseInterfaces.AllocationDatabaseInterface;
import com.sapient.moneytree.executionTrader.DatabaseInterfaces.BlockDatabaseInterface;
import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ETOrderDatabaseInterface;
import com.sapient.moneytree.executionTrader.DatabaseInterfaces.ExecutionDatabaseInterface;
import com.sapient.moneytree.executionTrader.domain.Allocations;
import com.sapient.moneytree.executionTrader.domain.Block;
import com.sapient.moneytree.executionTrader.domain.Execution;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.HoldingsDatabaseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.OrderDatabaseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.PMDatabseInterface;
import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.SecurityDatabaseInterface;
import com.sapient.moneytree.portfolioManager.domain.Holdings;
import com.sapient.moneytree.portfolioManager.domain.Orders;
import com.sapient.moneytree.portfolioManager.domain.PortfolioManager;
import com.sapient.moneytree.portfolioManager.domain.Securities;

@SpringBootApplication
public class ProjectEtPmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEtPmApplication.class, args);
	}

	@Bean
	CommandLineRunner init(PMDatabseInterface pmRepository, HoldingsDatabaseInterface hrep, OrderDatabaseInterface or,
			SecurityDatabaseInterface sr, ETOrderDatabaseInterface repository, BlockDatabaseInterface repository1,
			ExecutionDatabaseInterface rep, AllocationDatabaseInterface allrep) {
		return (evt) -> {
			PortfolioManager pm = new PortfolioManager();
			pm.setFname("shubhankar");
			pm.setLname("Mayank");
			pm.setUname("admin");
			pm.setPassword("password");
			pm.setRole(UserRole.PortfolioManager);
			pmRepository.save(pm);
			PortfolioManager pm1 = new PortfolioManager();
			pm1.setFname("Trader1");
			pm1.setLname("Trader");
			pm1.setUname("trader1");
			pm1.setPassword("password");
			pm1.setRole(UserRole.Trader);
			pmRepository.save(pm1);
			PortfolioManager pm2 = new PortfolioManager();
			pm2.setFname("Trader2");
			pm2.setLname("Trader");
			pm2.setUname("trader2");
			pm2.setPassword("password");
			pm2.setRole(UserRole.Trader);
			pmRepository.save(pm2);
			PortfolioManager pm3 = new PortfolioManager();
			pm3.setFname("Trader3");
			pm3.setLname("Trader");
			pm3.setUname("trader3");
			pm3.setPassword("password");
			pm3.setRole(UserRole.PortfolioManagerAndTrader);
			pmRepository.save(pm3);
			Date d1 = new Date();
			Holdings h1 = new Holdings();
			h1.setSymbol("APP");
			h1.setQty("100");
			h1.setPmId(pm.getpMId());
			h1.setTimeStamp(d1);
			h1.setBuyPrice("100.5");
			h1.setType(OrderType.LIMIT);
			hrep.save(h1);

			Holdings h2 = new Holdings();
			h2.setSymbol("APP");
			h2.setQty("300");
			h2.setPmId(pm.getpMId());
			h2.setTimeStamp(d1);
			h2.setBuyPrice("300.5");
			h2.setType(OrderType.LIMIT);
			hrep.save(h2);
			Date d = new Date();
			Orders o = new Orders();
			o.setOrderSide("BUY");
			o.setOrderStatus("NEW");
			o.setOrderType(OrderType.LIMIT);
			o.setPfId(1);
			o.setPmId(pm.getpMId());
			o.setPrice(100);
			o.setQty(100);
			o.setSecurityName("Apple");
			o.setSymbol("APP");
			o.settId(2);
			o.settName("Trader1");
			o.setTimeStamp(d);
			// o.settName("shuhul");
			o.setLprice(99.6);
			o.setSprice(101.6);
			o.setOrderValidity(OrderValidity.GTC);
			o.setAllocatedQty(90);
			or.save(o);

			/*Orders o1 = new Orders();
			o1.setOrderSide(OrderSide.BUY);
			o1.setOrderStatus(OrderStatus.Open);
			o1.setOrderType(OrderType.LIMIT);
			o1.setPfId(1);
			o1.setPmId(pm.getpMId());
			o1.setPrice(100);
			o1.setQty(100);
			o1.setSecurityName("Apple");
			o1.setSymbol("APP");
			o1.settId(2);
			o1.settName("Trader1");
			o1.setTimeStamp(d);
			// o.settName("shuhul");
			o1.setLprice(99.6);
			o1.setSprice(101.6);
			o1.setOrderValidity(OrderValidity.GTC);
			o1.setAllocatedQty(90);
			or.save(o);*/
			
			Securities s = new Securities();
			s.setCompany("Apple");
			s.setSymbol("APP");
			s.setCurPrice("100.5");
			sr.save(s);
			Securities s1 = new Securities();
			s1.setCompany("Google");
			s1.setSymbol("GOOG");
			s1.setCurPrice("200.5");
			sr.save(s1);
			Securities s2 = new Securities();
			s2.setCompany("Microsoft");
			s2.setSymbol("MICR");
			s2.setCurPrice("400.5");
			sr.save(s2);
			Allocations alloc = new Allocations();
			Orders order;
			Execution exec;
			Block block = new Block();

			alloc.setAllocatedQty(100);
			alloc.setOpenQty(50);
			alloc.setSymbol("Apple");
			allrep.save(alloc);

			/*
			 * // Sample Order 1 order = new Orders();
			 * order.setAccountType("Cash"); order.setLprice(407.00);
			 * order.setManagerid(1); order.setOrderType("Limit");
			 * order.setPfId(1); order.setOrderSide("Buy");
			 * order.setOrderStatus("Open"); order.setSymbol("Apple");
			 * order.setQty(100); order.getAllocList().add(alloc);
			 * repository.save(order);
			 */
			// Save to block

			// Sample order 2
			/*
			 * order = new Orders(); order.setAccountType("Cash");
			 * order.setAllocatedQty(0); order.setLprice(300.00);
			 * order.setManagerid(1); order.setOrderType("Limit");
			 * order.setPfId(1); order.setOrderSide("Buy");
			 * order.setOrderStatus("Open"); order.setSymbol("Apple");
			 * order.setQty(300); repository.save(order);
			 * 
			 * // Save to Block - try
			 * 
			 * // Sample order 3 order = new Orders();
			 * order.setAccountType("Cash"); order.setAllocatedQty(0);
			 * order.setLprice(675.65); order.setManagerid(2);
			 * order.setOrderType("Limit"); order.setPfId(2);
			 * order.setOrderSide("Buy"); order.setOrderStatus("Open");
			 * order.setSymbol("Google"); order.setQty(300);
			 * repository.save(order);
			 */
			/*
			 * block.getOrderList().add(order);
			 */
			/*
			 * // Sample order 4 order = new Orders();
			 * order.setAccountType("Cash"); order.setAllocatedQty(0);
			 * order.setSprice(450.87); order.setManagerid(3);
			 * order.setOrderType("Market"); order.setPfId(1);
			 * order.setOrderSide("Sell"); order.setOrderStatus("Open");
			 * order.setSymbol("Google"); order.setQty(309);
			 * repository.save(order);
			 * 
			 * block.getOrderList().add(order);
			 */
			// Sample Execution 1
			/*exec = new Execution();
			//exec.setQuantity(100);
			exec.setSide("buy");
			exec.setStatus("partial");
			exec.setSymbol("Apple");
			exec.setTxnprice(100.78);
			exec.setTxnTime(new Date());
			rep.save(exec);

			// Add execution to block
			block.getExecList().add(exec);

			// Sample Execution 2
			exec = new Execution();
			//exec.setQuantity(100);
			exec.setSide("buy");
			exec.setStatus("partial");
			exec.setSymbol("Apple");
			exec.setTxnprice(100.78);
			exec.setTxnTime(new Date());
			rep.save(exec);

			// Add execution to block
			block.getExecList().add(exec);
			block.setStatus("New");
			block.setSymbol("Apple");
			block.setSide("Buy");
			block.setBlockOrderType("Limit");
			block.setTotalQty(1000);
			block.setLimitPrice(123.5);
			block.setStopPrice(0);
			repository1.save(block);*/
		};
	}
}
