package main.service;

import java.util.List;

import main.model.Order;
/**
 * 
 * @author hsu
 *
 */
public interface OrderService {

	public List<Order> getAll();
	
	public Order getById(long id);
	
	public void saveOrUpdate(Order order);
	
	public void delete(long id);
	
}