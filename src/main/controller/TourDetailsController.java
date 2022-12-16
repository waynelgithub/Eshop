package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import main.model.Tour;
import main.model.TourDetails;
import main.service.TourDetailsService;
import main.service.TourService;

@Controller
public class TourDetailsController {

	@Autowired
	private TourService tourService;
	
	@Autowired
	private TourDetailsService tourDetailsService;
	
	@GetMapping("/showTourDetails/{tourId}")
	public String showTourDetails(@PathVariable long tourId, Model model) {
		Tour tour = tourService.getById(tourId);
		if(tour != null) {
			tourService.addTourDetailsIfNotExists(tour);
			model.addAttribute("tour", tour);
			return "tour-details";
		}
		return "redirect:showTourDetails";
	}
	
	@GetMapping("/editTourDetails/{tourId}")
	public String editTourDetails(@PathVariable long tourId, Model model) {
		Tour tour = tourService.getById(tourId);
		if(tour != null) {
			tourService.addTourDetailsIfNotExists(tour);
			model.addAttribute("tour", tour);
			return "form-tour-details";
		}
		return "redirect:/showOffer";
	}
	
	@GetMapping("/processForm/TourDetails/{tourId}")
	public String processTourDetailsData(@ModelAttribute TourDetails tourDetails) {
		tourDetailsService.saveOrUpdate(tourDetails);
		return "redirect:/showOffer";
	}
}
