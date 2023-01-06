package main.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import main.model.ShoppingCart;
import main.service.ShoppingCartService;

@Controller
public class PaymentController {
	@Autowired
	ShoppingCartService shoppingCartService;
	
	@GetMapping("/payment")
	public String showPayment(Model model, Principal principal) {
		ShoppingCart shoppingCart = shoppingCartService.showShoppingCart(principal.getName());
		model.addAttribute("shoppingCart", shoppingCart);		
		return "payment";
	}

}
