package main.service;

import java.util.List;

import main.model.ShoppingCartDetails;

public interface ShoppingCartDetailsService {
	
	public List<ShoppingCartDetails> getAll();

	public ShoppingCartDetails getById(long id);

	public void saveOrUpdate(ShoppingCartDetails shoppingCartDetails);

	public void delete(long id);
	
	public void deleteByIdWithShoppingCartDetails(long id);
	
	public ShoppingCartDetails getByProductNum(long num, long id);
	
	public long getProductNum(long productId);
	
	public ShoppingCartDetails addShoppingCartDetail(long productId, String customerNum);
}
