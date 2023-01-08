package main.service;

import java.util.List;

import main.model.Order;

public interface OrderService {

	public List<Order> getAll();
	
	public Order getById(long id);
	
	public List<Order> getOrdersByCustomerId(String customerNumber);
	
	public void saveOrUpdate(Order order);
	
	public void delete(long id);
	
}