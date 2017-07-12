package com.sapient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * Responsible for mapping of index page
 *
 */
@Controller
public class IndexController {

	@RequestMapping("/")
	String index() {
		return "indexBroker";
	}

	@RequestMapping("AddSecurities")
	String addsecurity(Model model) {

		return "AddSecurities";
	}
	
//	@RequestMapping("error")
//	String error() {
//		return "Error";
//	}

}
