package main.service;

import java.util.List;

import main.model.ShoppingCartDetails;

public interface ShoppingCartDetailsService {
	
	public List<ShoppingCartDetails> getAll();

	public ShoppingCartDetails getById(long id);

	public void saveOrUpdate(ShoppingCartDetails shoppingCartDetails);

	public void delete(long id);
	
}
