package main.controller;

import java.math.BigDecimal;
import java.security.Principal;
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

import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/add-shopping-cart")
	public String showShoppingCartForm(Model model, Principal principal) {
		ShoppingCart shoppingCart = shoppingCartService.setCustomerNum(principal.getName());
		if (shoppingCart == null) {
			model.addAttribute("shoppingCart", new ShoppingCart());
			return "form-shopping-cart";
		}
		return "redirect:show-shopping-cart";
	}

	@PostMapping("/process-shopping-cart-form")
	public String showTourData(@Valid @ModelAttribute ShoppingCart shoppingCart, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "form-shopping-cart";
		}
		shoppingCartService.saveOrUpdate(shoppingCart);
		return "redirect:show-shopping-cart";
	}

	@GetMapping("/show-shopping-cart")
	public String getTours(Model model, Principal principal) {
		long id = shoppingCartService.getCusNum(principal.getName());
		ShoppingCart shoppingCart = shoppingCartService.showShoppingCart(id);
		model.addAttribute("shoppingCart", shoppingCart);
		return "shopping-cart";
	}

	@GetMapping("/delete-shopping-cart/{id}")
	public String deleteTour(@PathVariable long id) {
		ShoppingCart shoppingCart = shoppingCartService.getById(id);
		if (shoppingCart != null) {
			shoppingCartService.delete(id);
		}
		return "redirect:/show-shopping-cart";
	}

	@GetMapping("/edit-shopping-cart/{id}")
	public String editTour(@PathVariable long id, Model model) {
		ShoppingCart shoppingCart = shoppingCartService.getById(id);
		if (shoppingCart != null) {
			model.addAttribute("shoppingCart", shoppingCart);
			return "form-shopping-cart";
		}
		return "redirect:/show-shopping-cart";
	}
	
	@GetMapping("/payment")
	public String showPayment() {
		return "payment";
	}
	
}
