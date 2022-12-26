package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
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
	private ProductImageRepository productImageServiceRepository;
	
	
	@GetMapping("/add-product-image")
	public String showProductImageForm(Model model) {
		model.addAttribute("productImage", new ProductImage());
		return "product-Image-form";
	}
	
	@GetMapping("/save-product-image")
	public void saveProductImage() throws IOException, SerialException, SQLException {
		File initialFile = new File("C:\\ABC\\pen1.png");
		InputStream stream = new FileInputStream(initialFile);
		byte[] bytes = IOUtils.toByteArray(stream);
		Blob blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		
		ProductImage productImage = new ProductImage();
		productImage.setFileName("pen1.png");
		productImage.setImageBlob(blob);
				
		productImageServiceRepository.save(productImage);

		initialFile = new File("C:\\ABC\\ruler1.png");
		stream = new FileInputStream(initialFile);
		bytes = IOUtils.toByteArray(stream);
		blob = new javax.sql.rowset.serial.SerialBlob(bytes);
		
		ProductImage productImage2 = new ProductImage();
		productImage2.setFileName("ruler1.png");
		productImage2.setImageBlob(blob);
		
		productImageServiceRepository.save(productImage2);		
		
	}
	
	@GetMapping("/get-product-image")
	public void getProductImage() throws SQLException, IOException  {
		ProductImage pi = productImageServiceRepository.findById((long) 1).get();
		Blob blob = pi.getImageBlob();
		InputStream in = blob.getBinaryStream();
		OutputStream out = new FileOutputStream("C:\\ABC\\pen2.png");
		IOUtils.copy(in, out);
		
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
