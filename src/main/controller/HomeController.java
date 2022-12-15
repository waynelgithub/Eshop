package main.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import main.model.ShoppingCart;

@Controller
public class HomeController {

	private List<ShoppingCart> shoppingCart = new ArrayList<>();
	
	@RequestMapping("/")
	public String getHome() {
		return "home";
	}
	
	@GetMapping("/showShoppingCart")
	public String getShoppingCart(Model model) {
		model.addAttribute("shoppingCart", shoppingCart);
		return "shoppingCart";
	}
	
}
