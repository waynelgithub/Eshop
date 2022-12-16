package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import main.service.TourDetailsService;
import main.service.TourService;

@Controller
public class TourDetailsController {

	@Autowired
	private TourService tourService;
	
	@Autowired
	private TourDetailsService tourDetailsService;
	
	public String showTourDetails() {
		return "";
	}
	
	public String editTourDetails() {
		return "";
	}
}
