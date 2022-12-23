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

import main.model.ShoppingCart;
import main.service.ShoppingCartService;

/**
 * 
 * @author hsu
 *
 */
@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/add-shopping-cart")
	public String showShoppingCartForm(Model model) {
		model.addAttribute("shoppingCart", new ShoppingCart());
		return "form-shopping-cart";
	}

	@PostMapping("/processs-shopping-cart-form")
	public String showTourData(@Valid @ModelAttribute ShoppingCart shoppingCart, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form-shopping-cart";
		}
		shoppingCartService.saveOrUpdate(shoppingCart);
		return "redirect:/show-shopping-cart";
	}

	@GetMapping("/show-shopping-cart")
	public String getTours(Model model) {
		List<ShoppingCart> shoppingCarts = shoppingCartService.getAll();
		model.addAttribute("shoppingCarts", shoppingCarts);
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

}
