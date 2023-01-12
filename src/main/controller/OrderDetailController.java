package main.controller;

import java.security.Principal;
import java.util.Date;
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
import main.model.SalesReturnStatus;
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
		return "redirect:/show-my-order-details";
	}
	
	// for admin to show all users' order details
	@GetMapping("/show-order-details")
	public String getOrderDetails(Model model) {
		List<OrderDetail> orderDetails=orderDetailService.getAll();
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	
	@GetMapping("/show-my-order-details/{orderNumber}")
	public String getMyOrderDetails(Model model, Principal principal, @PathVariable long orderNumber) {	
		
		List<OrderDetail> orderDetails=orderDetailService.getAllByOrderNumber(orderNumber);
		// verify data in console
		System.out.println(orderDetails);
		
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	
	@GetMapping("/delete-order-detail/{orderDetailId}")
	public String deleteOrderDetail(@PathVariable long orderDetailId) {
		OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
		if(orderDetail != null) {
			orderDetailService.delete(orderDetailId);
		}
		return "redirect:/show-my-order-details";
	}
	
	@GetMapping("/edit-order-detail/{orderDetailId}")
	public String editOrderDetail(@PathVariable long orderDetailId, Model model) {
		OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
		if(orderDetail != null) {
			model.addAttribute("orderDetail", orderDetail);
			return "order-detail-form";
		}
		return "redirect:/show-my-order-details";
	}
	
	// customer place return request for his/her own order
	@GetMapping("/place-return-request/{orderDetailId}")
	public String placeReturnRequest(@PathVariable long orderDetailId, Principal principal) {
	  //check if orderDetailId exists
		if(!orderDetailService.existsByOrderDetailId(orderDetailId)) {
			System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't exist.\n");
			return "redirect:/";
		}
		
	  //verify customerNumber 
		//get existing customerNumber
		String existingCustomerNumber = principal.getName();
		
		//get customerNumber through user input
		OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
		String customerNumberToVerify = orderDetail.getOrder().getCustomerNumer();
		
		//verify equality
		if (!customerNumberToVerify.equals(existingCustomerNumber)){
			//show message in console
			System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + "\n");
			return "redirect:/";
		}
		
	  //create the return request
		orderDetail.setModifedDate(new Date());
		orderDetail.setSalesReturnStatus(SalesReturnStatus.RETURN_REQUEST_PLACED);
		orderDetailService.saveOrUpdate(orderDetail);
		return "redirect:/show-my-order-details/"+orderDetail.getOrder().getOrderNumber();
		
		
	}
	
}
