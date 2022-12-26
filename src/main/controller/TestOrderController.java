package main.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
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

import main.model.Order;
import main.model.OrderDetail;
import main.service.OrderService;

@Controller
public class TestOrderController {

	@Autowired
	private OrderService orderService;
	
	//test area
	@GetMapping("/test-save-order")
	public String testSaveOrder() {
		Order order = new Order();
		order.setCustNum(2222);
		order.setOrdAmt(BigDecimal.valueOf(4141));
		order.setOrderDate(new Date());
		order.setOrdNum(363636);
		
		List<OrderDetail> orderDetails = new ArrayList<>();
		
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrdPrice(BigDecimal.valueOf(55));
		orderDetail.setOrdQty(3);
		orderDetail.setProdNum(7243);
		orderDetail.setOrder(order);
		orderDetail.setOrdNum(order.getOrdNum());
		orderDetails.add(orderDetail);

		orderDetail = new OrderDetail();
		orderDetail.setOrdPrice(BigDecimal.valueOf(1800));
		orderDetail.setOrdQty(100);
		orderDetail.setProdNum(5500);
		orderDetail.setOrder(order);
		orderDetail.setOrdNum(order.getOrdNum());
		orderDetails.add(orderDetail);		
		
				
		order.setOrderDetails(orderDetails);
		
				
		orderService.saveOrUpdate(order);
		return "redirect:show-orders";
		
	}
	
	//測試是否抓order 連帶 orderDetail 都有抓，要用debug mode
	@GetMapping("/test-show-orders")
	public String getOrders(Model model) {
		List<Order> orders = orderService.getAll();
		model.addAttribute("orders", orders);
		return "orders";
	}
		
}
