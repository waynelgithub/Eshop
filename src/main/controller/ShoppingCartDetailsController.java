package main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.service.ShoppingCartDetailsService;
import main.service.ShoppingCartService;

@Controller
public class ShoppingCartDetailsController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private ShoppingCartDetailsService shoppingCartDetailsService;
	
//	@GetMapping("/showShoppingCartDetails/{shoppingCartId}")
//	public String showTourDetails(@PathVariable long tourId, Model model) {
//		ShoppingCart shoppingCart = shoppingCartDetailsService.getByIdWithComments(tourId);
//		if(shoppingCart != null) {
//			model.addAttribute("shoppingCart", shoppingCart);
//			return "shoppingCartDetails";
//		}
//		return "redirect:/showShoppingCartOffer";
//	}
	
	@GetMapping("/editShoppingCartDetails/{shoppingCartId}")
	public String editShoppingCartDetails(@PathVariable long shoppingCartId, Model model) {
		ShoppingCart shoppingCart = shoppingCartService.getById(shoppingCartId);
		if(shoppingCart != null) {
			model.addAttribute("shoppingCartDetails", shoppingCart.getShoppingCartDetails());
			return "formShoppingCartDetails";
		}
		return "redirect:/showShoppingCartOffer";
	}
	
	@PostMapping("/processFormShoppingCartDetailsDetails")
	public String processShoppingCartDetailsData(@ModelAttribute ShoppingCartDetails shoppingCartDetails) {
		shoppingCartDetailsService.saveOrUpdate(shoppingCartDetails);
		return "redirect:/showShoppingCartOffer";
	}
}
