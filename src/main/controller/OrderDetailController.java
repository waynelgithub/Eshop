package main.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Order;
import main.model.OrderDetail;
import main.model.Product;
import main.service.OrderDetailService;
import main.service.OrderService;
import main.service.ProductService;

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
		return "redirect:/show-my-order-details";
	}
	
	// for admin to show all users' order details
	@GetMapping("/show-all-order-details")
	public String getOrderDetails(Model model) {
		List<OrderDetail> orderDetails=orderDetailService.getAll();
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	
	@GetMapping(value = {"/show-my-order-details/{orderNumber}", "/show-customer-order-details/{orderNumber}"})
	public String getMyOrderDetails(Model model, Principal principal, @PathVariable long orderNumber) {	
		// verify customer number first
		if (!orderService.verifyCustomerNumberByOrderNumber(orderNumber, principal)) 
			return "redirect:/";
		
		//prepare orderDetails to show
		List<OrderDetail> orderDetails=orderDetailService.getAllByOrderNumber(orderNumber);
		// verify data in console
		System.out.println(orderDetails);
		
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	
	/** 
	 * for admin to delete orderDetail, not yet to update the order which contains it
	 *  
	 * @param orderDetailId
	 * @return a string as a view to show a list of orderDetails
	 */
	@GetMapping("/delete-order-detail/{orderDetailId}")
	public String deleteOrderDetail(@PathVariable long orderDetailId) {
		//get the existing record from data source in order to delete it.
		//
		Order order = new Order();
		
		orderDetailService.findById(orderDetailId).ifPresent(
				orderDetail -> {
					order.setOrderNumber(orderDetailService.findById(orderDetailId).get().getOrder().getOrderNumber());
					orderDetailService.delete(orderDetail.getOrderDetailId());//如果先刪掉 orderDetail ，就取不到 order 了
		
		});
		
		Assert.notNull(order.getOrderNumber(), "orderNumber must not be null");
		
		return "redirect:/show-customer-order-details/" + order.getOrderNumber();
	}
	
	/**
	 * for admin to edit an OrderDetail. view is not ready.
	 * <p>Get an Optional<Orderdetail>,
	 * <br>if existed, add to Model and send it to View for editing the content of OrderDetail.
	 * <br>if not, redirect to home page. 
	 * @param orderDetailId
	 * @param model
	 * @return a string as view
	 */
	@GetMapping("/edit-order-detail/{orderDetailId}")
	public String editOrderDetail(@PathVariable long orderDetailId, Model model) {

		Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(orderDetailId);		

		String returnUrl = 
				orderDetailOptional
					.map( // if value is present
							orderDetail -> {
								model.addAttribute("orderDetail", orderDetail); 
								return "order-detail-form";})
					.or(
							() -> Optional.of("redirect:/"))
					.get()
					;
		
		return returnUrl;
	}
	
	/**	
	 * Customer place return request for his/her own order
	 * @param orderDetailId 訂單明細編號
	 * @param principal current Principal object
	 * @return redirect to URL: show-my-order-details
	 */
	@GetMapping("/place-return-request/{orderDetailId}")
	public String placeReturnRequest(@PathVariable long orderDetailId, Principal principal) {
		if (!orderDetailService.placeReturnRequest(orderDetailId, principal)) 
			return "redirect:/";

		Assert.notNull(orderDetailId, "orderDetailId must not be null");
		
		long orderNumber = orderDetailService.findById(orderDetailId).get().getOrder().getOrderNumber();
		
		return "redirect:/show-my-order-details/" + orderNumber;
		
	}
	
}
