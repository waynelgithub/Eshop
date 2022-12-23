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

import main.model.Order;

import main.service.OrderService;
/**
 * 
 * @author hsu
 *
 */

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	
	@GetMapping("/add-order")
	public String showOrderForm(Model model) {
		model.addAttribute("order", new Order());
		return "order-form";
	}
	
	@PostMapping("/save-order")
	public String saveOrderData(@Valid @ModelAttribute Order order, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "order-form";
		}
		orderService.saveOrUpdate(order);
		return "redirect:show-orders";
	}
	
	@GetMapping("/show-orders")
	public String getOrders(Model model) {
		List<Order> orders = orderService.getAll();
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/delete-order/{id}")
	public String deleteOrder(@PathVariable int id) {
		Order order = orderService.getById(id);
		if(order != null) {
			orderService.delete(id);
		}
		return "redirect:/show-orders";
	}
	
	@GetMapping("/edit-order/{id}")
	public String editOrder(@PathVariable int id, Model model) {
		Order order = orderService.getById(id);
		if(order != null) {
			model.addAttribute("order", order);
			return "order-form";
		}
		return "redirect:/show-orders";
	}
	
}
