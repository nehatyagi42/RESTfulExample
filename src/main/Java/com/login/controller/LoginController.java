package com.login.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.quarntine.service.AuthService;

@Controller
@RequestMapping("/customers")
public class LoginController {
	 @Autowired
	    private AuthService authenticateService;            // This will auto-inject the authentication service into the controller.
	 
	    private static Logger log = Logger.getLogger(LoginController.class);
	 
	    // Checks if the user credentials are valid or not.
	    @RequestMapping(value = "/validate", method = RequestMethod.POST)
	    public ModelAndView validateCustomer(@RequestParam("customername")String customername, @RequestParam("customerpassword")String customerpassword) {
	        String msg = "";
	        boolean isValid = authenticateService.findCustomer(customername, customerpassword);
	        log.info("Is user valid?= " + isValid);
	 
	        if(isValid) {
	            msg = "Welcome " + customername + "!";
	        } else {
	            msg = "Invalid credentials";
	        }
	 
	        return new ModelAndView("result", "output", msg);
	    }
	
	
}
