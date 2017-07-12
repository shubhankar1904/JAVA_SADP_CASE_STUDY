package com.sapient;

import org.springframework.boot.SpringApplication;

import java.sql.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.sapient.dbHandler.BlockRepository;
import com.sapient.dbHandler.BrListInterface;
import com.sapient.dbHandler.BrokerAdminRepository;
import com.sapient.dbHandler.FillRepository;
import com.sapient.dbHandler.SecurityRepository;

import com.sapient.entities.Broker;
import com.sapient.entities.BrokerAdmin;
import com.sapient.entities.Fill;
import com.sapient.entities.Security;
import com.sapient.exchange.entities.Exchange;
import com.sapient.jms.BrokerSendsFill;
import com.sapient.moneytree.executionTrader.domain.Block;

@SpringBootApplication
public class ProjectEtPmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectEtPmApplication.class, args);
	}

	@Bean
	CommandLineRunner init(BrokerAdminRepository rep1, BlockRepository rep2, BrListInterface pmRepository,
			FillRepository rep3, SecurityRepository rep4, BrokerSendsFill broker) {
		return (evt) -> {
		
			/*Block b1 = new Block();
			b1.setBlockId(100);
			b1.setBlockOrderType("Market");
			b1.setBlockQualifier("GTC");
			//Date date=new Date(); 
			//b1.setDate(date.toString());
			b1.setExecutedQty(100);
			//b1.setLimitPrice(142.65764604953063);
			//b1.setOpenQty(100);
			b1.setSide("Buy");
			b1.setStatus("Open");
			b1.setStopPrice(2);
			b1.setSymbol("APP");
			b1.setTotalQty(200);
			b1.setTraderid(7);
			rep2.save(b1);

		
		 Fill f1 = new Fill(); f1.setBlockId(1);
		 f1.setExecutedQuantity(50);
		 f1.setExecutionPrice(125); f1.setExecutedQuantity(25);
		 f1.setOpenQuantity(50); f1.setTimestamp("23 march 2013 12:30 PM"
		 ); ; f1.setSide("BUY"); f1.setSymbol("SBIN");
			
			 rep3.save(f1);
			
		 Fill f2 = new Fill(); f2.setBlockId(2);
		 f2.setExecutedQuantity(100); 
		 f2.setExecutionPrice(128); f2.setExecutedQuantity(25);
		 f2.setOpenQuantity(100); f2.setTimestamp("23 march 2013 12:30 PM"
		 ); f2.setSide("SELL"); f2.setSymbol("PNB");
		
		 rep3.save(f2);
			
		 Fill f3 = new Fill(); f3.setBlockId(3);
		 f3.setExecutedQuantity(100);
		 f3.setExecutionPrice(128); f3.setExecutedQuantity(25);
		 f3.setOpenQuantity(100); f3.setTimestamp("23 march 2013 12:30 PM"
		 ); f3.setSide("BUY"); f3.setSymbol("REL");
		
		 rep3.save(f3);*/
			
		 Security s1 = new Security(); 
		 s1.setExecNo(10);
		 s1.setInterval(5);
	 s1.setLastPrice(100.12345678);
		 s1.setProb(50);
		 s1.setSecName("Apple Corp");
		 s1.setSecSymbol("APP");
		 s1.setSpread(5);
		
		 rep4.save(s1);
		
		Security s2 = new Security(); 
			 s2.setExecNo(5);
		 s2.setInterval(5);
		 s2.setLastPrice(80);
		 s2.setProb(40);
		 s2.setSecName("Google");
		 s2.setSecSymbol("GOOG");
		 s2.setSpread(5);
			
		 rep4.save(s2);			
		 Security s3 = new Security(); 
		 s3.setExecNo(9);
		 s3.setInterval(5);
		 s3.setLastPrice(200);
		 s3.setProb(60);
		 s3.setSecName("Microsoft");
	 s3.setSecSymbol("MICR");
			 s3.setSpread(5);
		
		 rep4.save(s3);

		 Broker pm = new Broker(); pm.setUname("sahil");
		 pm.setPassword("password"); pmRepository.save(pm); Broker pm1 =			 new Broker(); pm1.setUname("gaurav");
			 pm1.setPassword("password"); pmRepository.save(pm1); Broker pm2 =
			 new Broker(); pm2.setUname("sakshi");
			 pm2.setPassword("password"); pmRepository.save(pm2); Broker pm3 =
		 new Broker(); pm3.setUname("apurva");
		 pm3.setPassword("password"); pmRepository.save(pm3); Broker pm4 =
			 new Broker(); pm4.setUname("malik"); pm4.setPassword("password");
		 pmRepository.save(pm4);

			
		};

	}
}
