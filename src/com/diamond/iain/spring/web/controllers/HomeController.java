package com.diamond.iain.spring.web.controllers;

import java.security.Principal;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.diamond.iain.spring.web.dao.Offer;
import com.diamond.iain.spring.web.service.OffersService;

@Controller
public class HomeController {
	
	//private static Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private OffersService offersService;
	
	@RequestMapping("/")
	public String showHome(Model model, Principal principal) {
		
		List<Offer> offers = offersService.getCurrent();
		boolean hasOffer = false;
		
		model.addAttribute("offers", offers);
		
		
		if(principal != null ) {
			
			hasOffer = offersService.hasOffer(principal.getName());
		}

		model.addAttribute("hasOffer", hasOffer);
		
		return "home";
	}
}
