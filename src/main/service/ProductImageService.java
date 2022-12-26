package main.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.ProductImage;



public interface ProductImageService {

	public List<ProductImage> getAll();
	
	public ProductImage getById(long id);
	
	public void saveOrUpdate(ProductImage productimage);
	
	public void delete(long id);
	
}