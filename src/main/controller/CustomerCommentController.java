package main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Customer;
import main.model.CustomerComment;
import main.service.CustomerCommentService;
import main.service.CustomerService;

@Controller
public class CustomerCommentController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerCommentService customerCommentService;
	
	@GetMapping("/addCustomerComment")
	public String showCommentForm(Model model) {
		List<Customer> customers = customerService.getAll();
		model.addAttribute("customers", customers);
		model.addAttribute("customerComment", new CustomerComment());
		return "formCustomerComment";
	}
	
	@PostMapping("/processFormCustomerComment")
	public String addCustomerCommentData(@ModelAttribute CustomerComment customerComment) {
		customerCommentService.save(customerComment);
		return "home";
	}
}
