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

import main.model.OrderDetail;
import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.service.ShoppingCartDetailsService;
import main.service.ShoppingCartService;

@Controller
public class ShoppingCartDetailsController {

	@Autowired
	private ShoppingCartDetailsService shoppingCartDetailsService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/add-shopping-cart-details")
	public String showShoppingCartForm(Model model) {
		List<ShoppingCart> shoppingCarts = shoppingCartService.getAll();
		model.addAttribute("shoppingCarts", shoppingCarts);
		model.addAttribute("shoppingCartDetails", new ShoppingCartDetails());
		return "form-shopping-cart-details";
	}
	
	@PostMapping("/process-shopping-cart-details-form")
	public String showShoppingCartDetailsData(@Valid @ModelAttribute ShoppingCartDetails shoppingCartDetails,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form-shopping-cart-details";
		}
		shoppingCartDetailsService.saveOrUpdate(shoppingCartDetails);
		return "redirect:/show-shopping-cart";
	}
	
}
