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

import main.model.Product;
import main.model.Sorder;
import main.model.Tour;
import main.service.ProductService;
import main.service.SorderService;

@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/addProduct")
	public String showForm(Model model) {
		model.addAttribute("product", new Product());
		return "addProduct";
	}
	
	@PostMapping("/submitProduct")
	public String showProductData(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "addProduct";
		}
		productService.saveOrUpdate(product);
		return "redirect:showProduct";
	}
	
	@GetMapping("/showProduct")
	public String getProduct(Model model) {
		List<Product> products=productService.getAll();
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id) {
		Product product = productService.getById(id);
		if(product != null) {
			productService.delete(id);
		}
		return "redirect:showProduct";
	}
	
	@GetMapping("/editProduct/{id}")
	public String editProduct(@PathVariable int id, Model model) {
		Product product = productService.getById(id);
		if(product != null) {
			model.addAttribute("product", product);
			return "addProduct";
		}
		return "redirect:showProduct";
	}
	
}
