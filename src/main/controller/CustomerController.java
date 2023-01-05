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
	
	@Autowired
	private CustomerDetailsService customerDetailsService;
	
	@GetMapping("/add-customer")
	public String showForm(Model model, Principal principal) {
		Customer customer = new Customer();
		customer.setCustomerNum(principal.getName());
		model.addAttribute("customer", customer);
		return "customer-form";
	}

	@PostMapping("/process-customer-form")
	public String showCoustomerData(@Valid @ModelAttribute Customer customer, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "customer-form";
		}
		CustomerDetails customerDetail = customer.getCustomerDetails();
		customerDetail.setCustomerNum(customer.getCustomerNum());
		customerDetailsService.saveOrUpdate(customerDetail);
		customerService.saveOrUpdate(customer);
		return "redirect:show-customer";
	}

	@GetMapping("/show-customer")
	public String getCustomer(Model model, Principal principal) {
		Customer customer = customerService.getByCustomerNum(principal.getName());
		if (customer == null) {
			return "redirect:/add-customer";
		}
		model.addAttribute("customer", customer);
		return "customer";
	}
	
	@GetMapping("/show-customers")
	public String getCustomers(Model model) {
		List<Customer> customers = customerService.getAll();
		model.addAttribute("customers", customers);
		return "customers";
	}

	@GetMapping("/delete-customer/{customerNum}")
	public String deleteCustomer(Principal principal,@PathVariable String customerNum) {
//		long userId = customerService.getUserId(principal.getName());
		Customer customer = customerService.getByCustomerNum(customerNum);
		if (customer != null) {
			customerService.delete(customer.getId());
		}
		return "redirect:/show-customer";
	}

	@GetMapping("/edit-customer")
	public String editCustomer(Model model, Principal principal) {
		Customer customer = customerService.getByCustomerNum(principal.getName());
		if (customer != null) {
			model.addAttribute("customer", customer);
			return "customer-form";
		}
		return "redirect:/show-customer";
	}
	
	@GetMapping("/edit-customer/{customerNum}")
	public String editCustomers(Model model,@PathVariable String customerNum) {
		Customer customer = customerService.getByCustomerNum(customerNum);
		if (customer != null) {
			model.addAttribute("customer", customer);
			return "customer-form";
		}
		return "redirect:/show-customer/{customerNum}";
	}
	
	@GetMapping("/show-customer/{customerNum}")
	public String getCustomer(Model model,@PathVariable String customerNum) {
		Customer customer = customerService.getByCustomerNum(customerNum);
		if (customer == null) {
			return "redirect:/add-customer";
		}
		model.addAttribute("customer", customer);
		return "customer";
	}
}
