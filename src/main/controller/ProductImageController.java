package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;

import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.model.Product;
import main.model.ProductImage;
import main.repository.ProductImageRepository;
import main.service.ProductImageService;
import main.service.ProductImageServiceImpl;
import main.service.ProductService;


@Controller
public class ProductImageController {

	@Autowired
	private ProductImageService productImageService;
	
	
	@GetMapping("/add-product-image")
	public String showProductImageForm(Model model) {
		model.addAttribute("productImage", new ProductImage());
		return "product-Image-form";
	}
	
	@GetMapping("/save-product-image")
	public void saveProductImage() throws IOException, SerialException, SQLException {
		File initialFile = new File("/home/wl/Documents/ABC/pen1.png");
		InputStream stream = new FileInputStream(initialFile);
		byte[] bytes = IOUtils.toByteArray(stream);
		Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		
		ProductImage productImage = new ProductImage();
		productImage.setFileName("pen01.png");
		productImage.setImageBlob(blob);
				
		productImageService.saveOrUpdate(productImage);

		initialFile = new File("/home/wl/Documents/ABC/ruler1.png");
		stream = new FileInputStream(initialFile);
		bytes = IOUtils.toByteArray(stream);
		blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		
		ProductImage productImage2 = new ProductImage();
		productImage2.setFileName("ruler01.png");
		productImage2.setImageBlob(blob);
		
		productImageService.saveOrUpdate(productImage2);		
		
	}
	
	@GetMapping("/get-product-image")
	public void getProductImage() throws SQLException, IOException  {
		ProductImage pi = productImageService.getById((long) 1);
		Blob blob = pi.getImageBlob();
		InputStream in = blob.getBinaryStream();
		OutputStream out = new FileOutputStream("/home/wl/Documents/ABC/pen02.png");
		IOUtils.copy(in, out);
		
	}
	
	@GetMapping("/get-product-image-to-web")
	public String getProductImageToWeb(Model model) throws SQLException, IOException  {
		
		ProductImage pi = productImageService.getById(2);
		Blob blob = pi.getImageBlob();
		
		
		//convert blob to byte array[]
		byte[] bytes = blob.getBinaryStream().readAllBytes();
		
		//convert byte array to base64 string
		String encodedString = Base64.getEncoder().encodeToString(bytes);


		model.addAttribute("img", encodedString);
		return "base64-image-demo";
		
		

		
	}
	
//	@PostMapping("/save-product")
//	public String saveProductData(@Valid @ModelAttribute Product product, BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			return "product-form";
//		}
//		productService.saveOrUpdate(product);
//		return "redirect:show-products";
//	}
//	
//	@GetMapping("/show-products")
//	public String getProduct(Model model) {
//		List<Product> products=productService.getAll();
//		model.addAttribute("products", products);
//		return "products";
//	}
//	
//	@GetMapping("/delete-product/{id}")
//	public String deleteProduct(@PathVariable int id) {
//		Product product = productService.getById(id);
//		if(product != null) {
//			productService.delete(id);
//		}
//		return "redirect:/show-products";
//	}
//	
//	@GetMapping("/edit-product/{id}")
//	public String editProduct(@PathVariable int id, Model model) {
//		Product product = productService.getById(id);
//		if(product != null) {
//			model.addAttribute("product", product);
//			return "product-form";
//		}
//		return "redirect:/show-products";
//	}
	
}
