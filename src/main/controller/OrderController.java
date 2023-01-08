package main.controller;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

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
import main.model.OrderDetail;
import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.service.OrderService;
import main.service.ShoppingCartService;

@Controller
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
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
		return "redirect:/show-my-orders";
	}
	
	@GetMapping("/save-order/{shoppingCartId}")
	public String saveOrderByShoppingCartId(@Valid @PathVariable int shoppingCartId) {
		//取出shoppingCart 跟 shopping detail 資料
		ShoppingCart shoppingCart = shoppingCartService.getByIdWithShoppingCartDetails(shoppingCartId);
		List<ShoppingCartDetails> shoppingCartDetails = shoppingCart.getShoppingCartDetails();
		
		//轉寫資料到 order & orderDetail		
		Order order = new Order();
		order.setCustomerNumer(shoppingCart.getCustomer_num());
		order.setOrderCreatedDate(shoppingCart.getDate());
		order.setOrderAmount(shoppingCart.getAmount());
		
		List<OrderDetail> orderDetails = shoppingCartDetails.stream()
				.map(o -> new OrderDetail(
							o.getProductNum(),
							o.getQuantity(),
							o.getProductPrice(),
							o.getProductPrice().multiply(BigDecimal.valueOf(o.getQuantity())),
							order
							)
					)
				.collect(Collectors.toList());
		
		order.setOrderDetails(orderDetails);
		
		//save order & orderDetail to DB
		orderService.saveOrUpdate(order);
	
		//刪除 shoppingCart DB 資料
		shoppingCartService.delete(shoppingCartId);	
		
		//轉個人訂單列表畫面
		return "redirect:/show-my-orders";
	}
	
	@GetMapping("/show-my-orders")
	public String getOrders(Model model, Principal principal) {
		String customerNumber = principal.getName();
		List<Order> orders = orderService.getOrdersByCustomerId(customerNumber);
		model.addAttribute("orders", orders);
		return "orders";
	}
	
	@GetMapping("/delete-order/{id}")
	public String deleteOrder(@PathVariable int id) {
		Order order = orderService.getById(id);
		if(order != null) {
			orderService.delete(id);
		}
		return "redirect:/show-my-orders";
	}
	
	@GetMapping("/edit-order/{id}")
	public String editOrder(@PathVariable int id, Model model) {
		Order order = orderService.getById(id);
		if(order != null) {
			model.addAttribute("order", order);
			return "order-form";
		}
		return "redirect:/show-my-orders";
	}
	
}
