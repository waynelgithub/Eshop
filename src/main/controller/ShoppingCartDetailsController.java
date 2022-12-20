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

import main.model.ShoppingCartDetails;
import main.service.ShoppingCartDetailsService;

@Controller
public class ShoppingCartDetailsController {
	
	@Autowired
	private ShoppingCartDetailsService shoppingCartDetailsService;
	
	@GetMapping("/add-shopping-cart-details")
	public String showShoppingCartForm(Model model) {
		model.addAttribute("shoppingCartDetails", new ShoppingCartDetails());
		return "form-shopping-cart-details";
	}
	
	@PostMapping("/process-shopping-cart-details-form")
	public String showShoppingCartDetailsData(@Valid @ModelAttribute ShoppingCartDetails shoppingCartDetails, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form-shopping-cart-details";
		}
		shoppingCartDetailsService.saveOrUpdate(shoppingCartDetails);
		return "redirect:/show-shopping-cart-details-offer";
	}
	
	
	@GetMapping("/show-shopping-cart-details-offer")
	public String getShoppingCartDetails(Model model) {
		List<ShoppingCartDetails> shoppingCartDetails = shoppingCartDetailsService.getAll();
		model.addAttribute("shoppingCartDetails", shoppingCartDetails);
		return "shopping-carts";
	}

	@GetMapping("/delete-shopping-cart-details/{id}")
	public String deleteShoppingCartDetails(@PathVariable long id) {
		ShoppingCartDetails shoppingCartDetails = shoppingCartDetailsService.getById(id);
		if (shoppingCartDetails != null) {
			shoppingCartDetailsService.delete(id);
		}
		return "redirect:/show-shopping-cart-details-offer";
	}

	@GetMapping("/edit-shopping-cart-details/{id}")
	public String editShoppingCartDetails(@PathVariable long id, Model model) {
		ShoppingCartDetails shoppingCartDetails = shoppingCartDetailsService.getById(id);
		if (shoppingCartDetails != null) {
			model.addAttribute("shoppingCartDetails", shoppingCartDetails);
			return "form-shopping-cart-details";
		}
		return "redirect:/show-shopping-cart-details-offer";
	}
}
