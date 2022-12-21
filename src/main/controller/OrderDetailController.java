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
	
	
	@GetMapping("/addOrderDetail")
	public String showOrderDetailForm(Model model) {
		List<Order> orders = orderService.getAll();
		model.addAttribute("orders", orders);
		List<Product> products = productService.getAll();
		model.addAttribute("products", products);		
		
		model.addAttribute("orderDetail", new OrderDetail());
		return "addOrderDetail";
	}
	
	@PostMapping("/saveOrderDetail")
	public String showOrderDetailData(@Valid @ModelAttribute OrderDetail orderDetail, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addOrderDetail";
		}
		orderDetailService.saveOrUpdate(orderDetail);
		return "redirect:showOrderDetail";
	}
	
	@GetMapping("/showOrderDetail")
	public String getOrderDetails(Model model) {
		List<OrderDetail> orderDetails=orderDetailService.getAll();
		model.addAttribute("orderDetails", orderDetails);
		return "orderDetails";
	}
	
	@GetMapping("/deleteOrderDetail/{id}")
	public String deleteOrderDetail(@PathVariable int id) {
		OrderDetail orderDetail = orderDetailService.getById(id);
		if(orderDetail != null) {
			orderDetailService.delete(id);
		}
		return "redirect:../showOrderDetail";
	}
	
	@GetMapping("/editOrderDetail/{id}")
	public String editOrderDetail(@PathVariable int id, Model model) {
		OrderDetail orderDetail = orderDetailService.getById(id);
		if(orderDetail != null) {
			model.addAttribute("orderDetail", orderDetail);
			return "addOrderDetail";
		}
		return "redirect:../showOrderDetail";
	}
	
}
