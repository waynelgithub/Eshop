package main.controller;

import java.security.Principal;

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
import main.service.ShoppingCartService;

@Controller
public class ShoppingCartDetailsController {

	@Autowired
	private ShoppingCartDetailsService shoppingCartDetailsService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@GetMapping("/add-shopping-cart-details/{productId}")
	public String showShoppingCartForm(Model model, Principal principal, @PathVariable long productId) {
		long cusNum = shoppingCartService.getCusNum(principal.getName());

		ShoppingCartDetails shoppingCartDetail = shoppingCartDetailsService.addShoppingCartDetail(productId, cusNum);
		
		model.addAttribute("shoppingCart", shoppingCartDetail.getShoppingCart());
		model.addAttribute("shoppingCartDetail", shoppingCartDetail);
		return "form-shopping-cart-details";
	}
	
	@PostMapping("/process-shopping-cart-details-form")
	public String showShoppingCartDetailsData(@Valid @ModelAttribute ShoppingCartDetails shoppingCartDetail,
			BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "form-shopping-cart-details";
		}
		shoppingCartDetailsService.saveOrUpdate(shoppingCartDetail);
		return "redirect:/show-shopping-cart";
	}
	
	@GetMapping("/shopping-cart-detail/{id}")
	public String deleteShoppingCartDetailsData(@PathVariable long id) {
		shoppingCartDetailsService.deleteByIdWithShoppingCartDetails(id);
		return "redirect:/show-shopping-cart";
	}
	
}
