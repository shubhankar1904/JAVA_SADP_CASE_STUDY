package com.sapient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.entities.Fill;
import com.sapient.exceptions.InvalidFilterException;
import com.sapient.exceptions.InvalidPasswordException;
import com.sapient.services.FillServices;
import com.sapient.services.SecurityService;

/**
 * It lists all the fills present in the database sorts the fills according to
 * various filters applied
 * 
 * @author scho20
 *
 */
@Controller
public class FillController {

	private FillServices fillServices;
	private SecurityService secServices;

	

	@Autowired
	public void setFillServices(FillServices fillServices) {
		this.fillServices = fillServices;
	}
	@Autowired
	public void setSecServices(SecurityService secServices) {
		this.secServices = secServices;
	}
	
	
	
	
	
	
	
	
	@RequestMapping("/BrokerHomeFills")
	public String list(Model model) {
		
		model.addAttribute("Fill", fillServices.listAllFills());
		model.addAttribute("Sec", secServices.listAllSecurities());
		
		return "ViewFills";
	}

	

	/**
	 * This function filters the securities in database by side and symbol
	 * 
	 * @param side
	 *            the side of the fills as selected by the broker
	 * @param symbol
	 *            the symbol as selected by the broker
	 * @return the page named ViewFills
	 */
	@RequestMapping(value = "/Filters", method = RequestMethod.POST)
	public String FilterDropDown(Model model, @RequestParam("side") String side,
			@RequestParam("symbol") String symbol) {

		model.addAttribute("Fills", secServices.listAllSecurities());

		if (side.equals("")) {
			model.addAttribute("Filters", fillServices.filterBySymbol(symbol));
		} else if (symbol.equals("")) {
			model.addAttribute("Filters", fillServices.filterBySide(side));
		}

		else {

			model.addAttribute("Filters", fillServices.filterByBoth(side, symbol));
		}

		return "ViewFills";
	}

	/**
	 * This function filters the securities in database by execution id and
	 * block id
	 * 
	 * @param execId
	 *            the execution id of the fill
	 * @param blockId
	 *            the block id of the fill
	 * @return the page named ViewFills
	 * @throws InvalidFilterException
	 */
	@RequestMapping(value = "/FilterById", method = RequestMethod.POST)
	String FilterId(Model model, @RequestParam("executionID") long execId, @RequestParam("blockID") long blockId) {

		model.addAttribute("Fills", secServices.listAllSecurities());
		if (execId == 0&&((blockId>0))) {
			
			model.addAttribute("Filters1", fillServices.filterByblockID(blockId));
		}
		else if (blockId == 0&&execId>0) {
			System.out.println(fillServices.filterByblockID(blockId));
			model.addAttribute("Filters1", fillServices.filterByexecID(execId));
		}

		else if((blockId == 0) && (execId == 0)) {
			try {
				
				throw new InvalidFilterException();
			} catch (InvalidFilterException e) {
				System.out.println(e.toString());
				model.addAttribute("FillSReset", fillServices.listAllFills());
				return "ViewFills";
			}

		}
		else if (Long.toString(blockId).equals("") || Long.toString(execId).equals("")) {
			System.out.println("longtostring");
		} else {
			
			model.addAttribute("Filters1", fillServices.filterByBothID(execId, blockId));
		}

		return "ViewFills";
	}

	/**
	 * This function resets the filters applied on securities
	 * 
	 * @return the page named ViewFills
	 */
	@RequestMapping(value = "/FilterReset", method = RequestMethod.POST)
	String FilterReset(Model model) {

		model.addAttribute("Fills", secServices.listAllSecurities());
		model.addAttribute("FillSReset", fillServices.listAllFills());
		return "ViewFills";
	}
	
	/*@ExceptionHandler(value = NullPointerException.class)*/
	/*@RequestMapping(value = "/error")
	@ExceptionHandler(value = NullPointerException.class)
	public String HandleNullPointerexception(Exception e) {

		System.out.println("NullPointer Exception" + e);
		return "ViewFills";

	}*/
	
	
	
	
	
	
	
}
