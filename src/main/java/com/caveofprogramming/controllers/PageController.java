package com.caveofprogramming.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.caveofprogramming.model.StatusUpdate;
import com.caveofprogramming.service.StatusUpdateService;

@Controller
public class PageController {
	
	@Autowired
	private StatusUpdateService statusUpdateService;
	
	@RequestMapping("/home")
	String home() {
		return "app.home";
	}
	
	
	@RequestMapping("/about")
	String about() {
		return "app.about";
	}
	
	@RequestMapping(value="/addstatus", method=RequestMethod.GET)
	ModelAndView addStatus(ModelAndView modelAndView, @ModelAttribute("statusUpdate")StatusUpdate statusUpdate) {
		modelAndView.setViewName("app.addstatus");
		//StatusUpdate statusUpdate = new StatusUpdate("Hello from the  Model!", new Date());
		StatusUpdate latestStatsUpdate = statusUpdateService.getLatest();
		//modelAndView.getModel().put("statusUpdate", statusUpdate );
		modelAndView.getModel().put("latestStatsUpdate", latestStatsUpdate );
		return modelAndView;
	}
	

	@RequestMapping(value="/addstatus", method=RequestMethod.POST)
	ModelAndView addStatus(ModelAndView modelAndView, StatusUpdate statusUpdate, String temp) {
		modelAndView.setViewName("app.addstatus");
		StatusUpdate latestStatsUpdate = statusUpdateService.getLatest();
		statusUpdateService.save(statusUpdate);
		
		modelAndView.getModel().put("latestStatsUpdate", latestStatsUpdate );
		return modelAndView;
	}
	
	
	
}
