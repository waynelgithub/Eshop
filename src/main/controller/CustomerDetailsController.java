package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Customer;
import main.model.CustomerDetails;
import main.service.CustomerDetailsService;
import main.service.CustomerService;

@Controller
public class CustomerDetailsController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@GetMapping("/showCustomerDetails/{customerId}")
	public String showCustomerDetails(@PathVariable long customerId, Model model) {
		Customer customer = customerService.getById(customerId);
		if(customer != null) {
			model.addAttribute("customer", customer);
			return "customerDetails";
		}
		return "redirect:/showCustomerOffer";
	}
	
	@GetMapping("/editCustomerDetails/{customerId}")
	public String editTourDetails(@PathVariable long customerId, Model model) {
		Customer customer = customerService.getById(customerId);
		if(customer != null) {
			model.addAttribute("customerDetails", customer.getCustomerDetails());
			return "formCustomerDetails";
		}
		return "redirect:/showCustomerOffer";
	}
	
	@PostMapping("/processFormCustomerDetails")
	public String processCustomerDetailsData(@ModelAttribute CustomerDetails customerDetails) {
		customerDetailsService.saveOrUpdate(customerDetails);
		return "redirect:/showCustomerOffer";
	}

}
