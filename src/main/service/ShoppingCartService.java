package main.service;

import java.util.List;

import main.model.ShoppingCart;

public interface ShoppingCartService {
	
	public List<ShoppingCart> getAll();

	public ShoppingCart getById(long id);

	public void saveOrUpdate(ShoppingCart shoppingCart);

	public void delete(long id);
	
	public ShoppingCart getByIdWithShoppingCartDetails(long id);
	
	public ShoppingCart getByCustomerNum(long id);
	
	public List<ShoppingCart> findFirstByOrderById();
	
	public void sumAmount(long id);
	
	public long getCusNum(String login);
	
	public ShoppingCart setCustomerNum(String login);
	
}
