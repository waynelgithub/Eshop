package main.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.sql.SQLException;
import java.util.Base64;

import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import main.model.ProductImage;
import main.service.ProductImageService;



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

		
		ProductImage productImage = new ProductImage();
		productImage.setFileName("pen01.png");
		productImage.setImage(bytes);
				
		productImageService.saveOrUpdate(productImage);

		initialFile = new File("/home/wl/Documents/ABC/ruler1.png");
		stream = new FileInputStream(initialFile);
		bytes = IOUtils.toByteArray(stream);
		
		ProductImage productImage2 = new ProductImage();
		productImage2.setFileName("ruler01.png");
		productImage2.setImage(bytes);
		
		productImageService.saveOrUpdate(productImage2);		
		
	}
	
	@GetMapping("/get-product-image-to-web")
	public String getProductImageToWeb(Model model) throws SQLException, IOException  {
		
		ProductImage pi = productImageService.getById(1);
		byte[] bytes = pi.getImage();
		
		//convert byte array to base64 string
		String encodedString = Base64.getEncoder().encodeToString(bytes);


		model.addAttribute("img", encodedString);
		return "base64-image-demo";
		
	}
	
	@GetMapping("/image-upload")
	public String showProductImageUpload(Model model) {
		model.addAttribute("productImage", new ProductImage());
		return "product-image-upload-demo";
	}


	@PostMapping(value = "/product-image-upload")
	public String saveImage(@RequestParam MultipartFile file)
			throws Exception {

		String filename = file.getOriginalFilename();
		
		byte[] bytes = file.getBytes();

		ProductImage productImage = new ProductImage();
		productImage.setFileName(filename);
		productImage.setImage(bytes);
				
		productImageService.saveOrUpdate(productImage);

	

		return "product-image-upload-demo";
	}
	
	
	
//	@GetMapping("/get-product-image")
//	public void getProductImage() throws SQLException, IOException  {
//		ProductImage pi = productImageService.getById((long) 1);
//		Blob blob = pi.getImageBlob();
//		InputStream in = blob.getBinaryStream();
//		OutputStream out = new FileOutputStream("/home/wl/Documents/ABC/pen02.png");
//		IOUtils.copy(in, out);
//		
//	}
	
	
	
	
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
