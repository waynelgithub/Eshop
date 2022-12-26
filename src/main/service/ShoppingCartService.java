package main.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import main.model.ShoppingCart;

public interface ShoppingCartService {
	
	public List<ShoppingCart> getAll();

	public ShoppingCart getById(long id);

	public void saveOrUpdate(ShoppingCart shoppingCart);

	public void delete(long id);
	
	public ShoppingCart getByIdWithShoppingCartDetails(long id);
	
	public ShoppingCart getByCustomerNum(long id);
	
	public List<ShoppingCart> findFirstByOrderById();
	
	public void sumAmount();
}
