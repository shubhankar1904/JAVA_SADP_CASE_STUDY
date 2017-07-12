package com.sapient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sapient.entities.Broker;
import com.sapient.exceptions.InvalidPasswordException;
import com.sapient.exceptions.InvalidUserIdException;
import com.sapient.services.BrService;
/**
 * Responsible for processing Broker's requests and building appropriate model and passes it to the view for rendering.
 *
 */
@Controller
public class BrController {

	private BrService brService;

	@Autowired
	public void setBrokerService(BrService brService) {
		this.brService = brService;
	}

	/**
	 * 
	 * @param brId
	 *            This is the id that broker uses to log on to the system
	 * @param password
	 *            password of broker to login to the system
	 * @return reference to associated page
	 */
	
	
	
	
	
	
	
	
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String newBroker(Model model, @RequestParam("uId") String brId, @RequestParam("password") String password) {

		Broker br;
		try {
			try {
				br = brService.getByBrId(new Integer(brId));
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

		if (validateUser(br, password)) {

			model.addAttribute("br",br);
			return "redirect:/BrokerHome1";
			/*return "redirect:/BrokerHome1";
*/
		} else
			return "indexInvalid";
	}

	/**
	 * 
	 * @param br
	 *            object of type broker
	 * @param password
	 *            password entered by the broker to login
	 * @return returns true if password entered and the one in database matches
	 *         otherwise false
	 */
	public boolean validateUser(Broker br, String password) {
		if (br.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

}
