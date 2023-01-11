package main.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import main.model.Product;
import main.model.ProductImage;
import main.service.ProductService;


@Controller
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
	@GetMapping("/add-product")
	public String showProductForm(Model model) {
		model.addAttribute("product", new Product());
		return "product-form";
	}
	
	@PostMapping("/save-product")
	public String saveProductDataWithImage(@Valid @ModelAttribute Product product, BindingResult bindingResult, @RequestPart @Valid MultipartFile file, Errors errors) throws IOException {
		
		//verify if uploaded multipart file is null
		if(file.isEmpty()) {
			//register a GlobalError, and link the message code in the message.properties 
			errors.reject("upload.file.required");
		}	

		//要讓 product 的文字欄位跟 multipart 的上傳檔案欄位都同時能回報驗證錯誤
		//用 bitwise OR 確保左右兩邊的 hasErrors() 都被執行過
		if(errors.hasErrors() | bindingResult.hasErrors()) {
			return "product-form";
		}
		
		String filename = file.getOriginalFilename();
		
		//convert file to Base64 string	
		byte[] bytes = file.getBytes();
		String encodedString = Base64.getEncoder().encodeToString(bytes);
	
	
		//prepare productImage
		ProductImage productImage = new ProductImage();

			//check if this product id exists
			Product existingProduct = productService.getByIdWithImage(product.getId());
			//get existing productImage if product id exists
			if(existingProduct != null)	{
				productImage = existingProduct.getProductImage();
			}
			
		// update productImage fields
		productImage.setFileName(filename);
		productImage.setImageBase64String(encodedString);
		
		//prepare product with ProductImage
		product.setProductImage(productImage);
		
		//save product and productImage by cascadeType=ALL
		productService.saveOrUpdate(product);
		return "redirect:show-products";
	}
	
	
	@GetMapping("/show-products")
	public String getProduct(Model model) {
		List<Product> products=productService.getAll();
		model.addAttribute("products", products);
		return "products";
	}
	
	@GetMapping("/delete-product/{id}")
	public String deleteProduct(@PathVariable int id) {
		Product product = productService.getById(id);
		if(product != null) {
			productService.delete(id);
		}
		return "redirect:/show-products";
	}
	
	@GetMapping("/edit-product/{id}")
	public String editProduct(@PathVariable int id, Model model) {
//		Product product = productService.getById(id);
		Product product = productService.getByIdWithImage(id);
		if(product != null) {
			model.addAttribute("product", product);
			return "product-form";
		}
		return "redirect:/show-products";
	}
	
	@GetMapping("/product-home")
	public String showProductsOnHomePage(Model model) {
		List<Product> products=productService.getAllWithImage();
		model.addAttribute("products", products);
		return "home";
	}
	
}
