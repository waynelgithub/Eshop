package main.controller;

import java.security.Principal;
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
import main.model.CustomerDetails;
import main.service.CustomerDetailsService;
import main.service.CustomerService;

@Controller
public class CustomerController {

	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/add-customer")
	public String showForm(Model model, Principal principal) {
		long userId = customerService.getUserId(principal.getName());
		Customer customer = new Customer();
		customer.setUserId(userId);
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@PostMapping("/process-customer-form")
	public String showCoustomerData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "customer-form";
		}
		customerService.saveOrUpdate(customer);
		return "redirect:show-customer";
	}

	@GetMapping("/show-customer")
	public String getCustomers(Model model, Principal principal) {
		long userId = customerService.getUserId(principal.getName());
		Customer customer = customerService.getByUserId(userId);
		if (customer == null) {
			return "redirect:/add-customer";
		}
		model.addAttribute("customer", customer);
		return "customer";
	}

	@GetMapping("/delete-customer")
	public String deleteCustomer(Principal principal) {
		long userId = customerService.getUserId(principal.getName());
		Customer customer = customerService.getById(userId);
		if (customer != null) {
			customerService.delete(customer.getId());
		}
		return "redirect:/show-customer";
	}

	@GetMapping("/edit-customer")
	public String editCustomer(Model model, Principal principal) {
		long userId = customerService.getUserId(principal.getName());
		Customer customer = customerService.getByUserId(userId);
		if (customer != null) {
			model.addAttribute("customer", customer);
			return "customer-form";
		}
		return "redirect:/show-customer";
	}
	
	
}
