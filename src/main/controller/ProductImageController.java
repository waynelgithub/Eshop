package main.controller;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import main.model.Product;
import main.model.ProductImage;
import main.service.ProductImageService;
import main.service.ProductService;



@Controller
public class ProductImageController {

	@Autowired
	private ProductImageService productImageService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/add-product-image")
	public String showProductImageUpload(Model model) {
		model.addAttribute("productImage", new ProductImage());
		return "product-image-upload-demo";
	}

	/**
	 * upload image as Base64 string,
	 * set it as a product property,
	 * save the product with product id
	 * @param file the Multipart file been uploaded
	 * @param productId to get the product
	 * @return base64 image upload demo page
	 * @throws Exception
	 */
	@PostMapping(value = "/product-image-upload-with-product-id")
	public String saveImageWithProductId(@RequestParam MultipartFile file,@RequestParam long productId)
			throws Exception {

		String filename = file.getOriginalFilename();
		
		//convert file to Base64 string	
		byte[] bytes = file.getBytes();
		String encodedString = Base64.getEncoder().encodeToString(bytes);
		
		//get product by productId
		Product product = productService.getById(productId);
		
		//prepare productImage
		ProductImage productImage = new ProductImage();
		productImage.setFileName(filename);
		productImage.setImageBase64String(encodedString);
		
		product.setProductImage(productImage);
		
		
		productService.saveOrUpdate(product);
		
		//save product image in DB
		//productImageService.saveOrUpdate(productImage);	

		return "product-image-upload-demo";
	}
	
	/**
	 * upload a single image,
	 * encode it as Base64 string
	 * save it to DB
	 * @param file the Multipart file been uploaded
	 * @return base64 image upload demo page
	 * @throws Exception
	 */
	@PostMapping(value = "/product-image-upload")
	public String saveImage(@RequestParam MultipartFile file)
			throws Exception {

		String filename = file.getOriginalFilename();
		
		//convert file to Base64 string	
		byte[] bytes = file.getBytes();
		String encodedString = Base64.getEncoder().encodeToString(bytes);
		
		//prepare productImage
		ProductImage productImage = new ProductImage();
		productImage.setFileName(filename);
		productImage.setImageBase64String(encodedString);
				
		//save product in DB
		productImageService.saveOrUpdate(productImage);	

		return "product-image-upload-demo";
	}

	/**
	 * get an image in Base64 string format from the database,
	 * add it as a model attribute 
	 * @param imageNum image_num in DB
	 * @param model
	 * @return base64 image upload demo page
	 */
	@GetMapping("/get-product-image/{imageNum}")
	public String getProductImageString(@PathVariable int imageNum,Model model){
		
		ProductImage pi = productImageService.getById(imageNum);
		String encodedString = pi.getImageBase64String();

		model.addAttribute("img", encodedString);
		return "base64-image-demo";
		
	}
	
}
