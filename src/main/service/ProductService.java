package main.service;

import java.util.List;

import main.model.Product;

/**
 * 
 * @author hsu
 *
 */
public interface ProductService {

	public List<Product> getAll();
	
	public Product getById(long id);
	
	public void saveOrUpdate(Product product);
	
	public void delete(long id);
	
}