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

	@GetMapping("/add-customer")
	public String showForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer-form";
	}

	@PostMapping("/process-customer-form")
	public String showCoustomerData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "customer-form";
		}
		customerService.saveOrUpdate(customer);
		return "redirect:/show-customers";
	}

	@GetMapping("/show-customers")
	public String getCustomers(Model model) {
		List<Customer> customers = customerService.getAll();
		model.addAttribute("customers", customers);
		return "customers";
	}

	@GetMapping("/delete-customer/{id}")
	public String deleteCustomer(@PathVariable long id) {
		Customer customer = customerService.getById(id);
		if (customer != null) {
			customerService.delete(id);
		}
		return "redirect:/show-customers";
	}

	@GetMapping("/edit-customer/{id}")
	public String editCustomer(@PathVariable long id, Model model) {
		Customer customer = customerService.getById(id);
		if (customer != null) {
			model.addAttribute("customer", customer);
			return "customer-form";
		}
		return "redirect:/show-customers";
	}
	
	
}
