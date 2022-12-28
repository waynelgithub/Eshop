package main.service;

import java.util.List;

import main.model.Product;

public interface ProductService {

	public List<Product> getAll();
	
	public List<Product> getAllWithImage();
	
	public Product getById(long id);
	
	public Product getByIdWithImage(long id);
	
	public void saveOrUpdate(Product product);
	
	public void delete(long id);
	
}