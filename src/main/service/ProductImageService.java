package main.service;

import java.util.List;

import main.model.ProductImage;



public interface ProductImageService {

	public List<ProductImage> getAll();
	
	public ProductImage getById(long id);
	
	public void saveOrUpdate(ProductImage productimage);
	
	public void delete(long id);
	
}