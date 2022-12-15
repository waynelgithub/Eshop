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

import main.model.Customer;
import main.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@GetMapping("/addCustomer")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "form";
	}
	
	@PostMapping("/processForm")
	public String showTourData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "form";
		}
		customerService.saveOrUpdate(customer);
		return "redirect:showOffer";
	}
	
	@GetMapping("/showCustomer")
	public String getTours(Model model) {
		List<Customer> customers = customerService.getAll();
		model.addAttribute("customers", customers);
		return "customers";
	}
	
	@GetMapping("/deleteTour/{id}")
	public String deleteTour(@PathVariable long id) {
		Customer customer = customerService.getById(id);
		if(customer != null) {
			customerService.delete(id);
		}
		return "redirect:/showCustomer";
	}
	
	@GetMapping("/editTour/{id}")
	public String editTour(@PathVariable long id, Model model) {
		Customer customer = customerService.getById(id);
		if(customer != null) {
			model.addAttribute("customer", customer);
			return "form";
		}
		return "redirect:/showCustomer";
	}
}
