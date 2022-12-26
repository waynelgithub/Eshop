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

import main.model.Product;
import main.model.Order;
import main.model.OrderDetail;
import main.service.ProductService;
import main.service.OrderDetailService;
import main.service.OrderService;

@Controller
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/add-order-detail")
	public String showOrderDetailForm(Model model) {
		List<Order> orders = orderService.getAll();
		model.addAttribute("orders", orders);
		List<Product> products = productService.getAll();
		model.addAttribute("products", products);		
		
		model.addAttribute("orderDetail", new OrderDetail());
		return "order-detail-form";
	}
	
	@PostMapping("/save-order-detail")
	public String saveOrderDetailData(@Valid @ModelAttribute OrderDetail orderDetail, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "order-detail-form";
		}
		orderDetailService.saveOrUpdate(orderDetail);
		return "redirect:show-order-details";
	}
	
	@GetMapping("/show-order-details")
	public String getOrderDetails(Model model) {
		List<OrderDetail> orderDetails=orderDetailService.getAll();
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	
	@GetMapping("/delete-order-detail/{id}")
	public String deleteOrderDetail(@PathVariable int id) {
		OrderDetail orderDetail = orderDetailService.getById(id);
		if(orderDetail != null) {
			orderDetailService.delete(id);
		}
		return "redirect:/show-order-details";
	}
	
	@GetMapping("/edit-order-detail/{id}")
	public String editOrderDetail(@PathVariable int id, Model model) {
		OrderDetail orderDetail = orderDetailService.getById(id);
		if(orderDetail != null) {
			model.addAttribute("orderDetail", orderDetail);
			return "order-detail-form";
		}
		return "redirect:/show-order-details";
	}
	
}
