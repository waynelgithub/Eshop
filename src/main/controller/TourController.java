package main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Tour;
import main.service.TourService;

@Controller
public class TourController {

	@Autowired
	private TourService tourService;

	@GetMapping("/addTour")
	public String showForm(Model model) {
		model.addAttribute("tour", new Tour());
		return "form";
	}

	@PostMapping("/processForm")
	public String showTourData(@Valid @ModelAttribute Tour tour, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form";
		}
		tourService.saveOrUpdate(tour);
		return "redirect:/";
	}

	@GetMapping("/")
	public String getTours(Model model) {
		List<Tour> tours = tourService.getAll();
		model.addAttribute("tours", tours);
		return "home";
	}

	@GetMapping("/deleteTour/{id}")
	public String deleteTour(@PathVariable long id) {
		Tour tour = tourService.getById(id);
		if (tour != null) {
			tourService.delete(id);
		}
		return "redirect:/showOffer";
	}

	@GetMapping("/editTour/{id}")
	public String editTour(@PathVariable long id, Model model) {
		Tour tour = tourService.getById(id);
		if (tour != null) {
			model.addAttribute("tour", tour);
			return "form";
		}
		return "redirect:/showOffer";
	}
	
	@GetMapping("/addUserToTour/{id}/{userId}")
	public String addUserToTour(@PathVariable long id, @PathVariable long userId) {
		tourService.addUserToTour(id, userId);
		return "redirct:/showOffer";
	}
	
	@GetMapping("/showOfferForNextMonth")
	public String getToursForNextMonth(Model model) {
		List<Tour> tours = tourService.getAllforNextMonth();
		model.addAttribute("tours", tours);
		return "tours";
	}
}
