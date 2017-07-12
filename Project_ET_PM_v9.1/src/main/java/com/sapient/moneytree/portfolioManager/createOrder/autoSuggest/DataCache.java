package com.sapient.moneytree.portfolioManager.createOrder.autoSuggest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sapient.moneytree.portfolioManager.DatabaseInterfaces.SecurityDatabaseInterface;
import com.sapient.moneytree.portfolioManager.domain.Securities;
/**
 * 
 * @author Sarthak Jain
 *
 */
public class DataCache {
    
    StringBuilder dataCache;
    String [] data;
  
    
	public DataCache(List<Securities> securities){
	dataCache = new StringBuilder();


	for (Securities security : securities) {
		dataCache.append(security.getSymbol()+",");
	}
	
	data =dataCache.toString().split(",");
	}
    
    public  List<String> getName(String name) {

	List<String> returnMatchName = new ArrayList<String>();
	String [] data =dataCache.toString().split(",");	
	for (String string : data) {
	    if (string.toUpperCase().indexOf(name.toUpperCase())!= -1) {
		returnMatchName.add(string);
	    }
	}
	
	return returnMatchName;
    }

}
