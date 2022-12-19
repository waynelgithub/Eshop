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

@Controller
public class ShoppingCartController {
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/addShoppingCart")
	public String showForm(Model model) {
		model.addAttribute("tour", new ShoppingCart());
		return "formShoppingCart";
	}

	@PostMapping("/processShoppingCartForm")
	public String showTourData(@Valid @ModelAttribute ShoppingCart shoppingCart, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formShoppingCart";
		}
		shoppingCartService.saveOrUpdate(shoppingCart);
		return "redirect:/showShoppingCartOffer";
	}

	@GetMapping("/showShoppingCartOffer")
	public String getTours(Model model) {
		List<ShoppingCart> shoppingCarts = shoppingCartService.getAll();
		model.addAttribute("shoppingCarts", shoppingCarts);
		return "shoppingCarts";
	}

	@GetMapping("/deleteShoppingCart/{id}")
	public String deleteTour(@PathVariable long id) {
		ShoppingCart shoppingCart = shoppingCartService.getById(id);
		if (shoppingCart != null) {
			shoppingCartService.delete(id);
		}
		return "redirect:/showShoppingCartOffer";
	}

	@GetMapping("/editShoppingCart/{id}")
	public String editTour(@PathVariable long id, Model model) {
		ShoppingCart shoppingCart = shoppingCartService.getById(id);
		if (shoppingCart != null) {
			model.addAttribute("shoppingCart", shoppingCart);
			return "form";
		}
		return "redirect:/showShoppingCartOffer";
	}

}
