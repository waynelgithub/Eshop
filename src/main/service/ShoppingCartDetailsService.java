package main.service;

import main.model.ShoppingCartDetails;

public interface ShoppingCartDetailsService {
	
	public ShoppingCartDetails getById(long id);
	
	public void saveOrUpdate(ShoppingCartDetails shoppingCartDetails);
	
	public void delete(long id);

}
