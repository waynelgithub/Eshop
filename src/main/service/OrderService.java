package main.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import main.model.Order;

public interface OrderService {

	public List<Order> getAll();
	
	public Order getById(long id);
	
	public Optional<Order> findById(long id);
	
	public List<Order> getOrdersByCustomerId(String customerNumber);
	
	public void saveOrUpdate(Order order);
	
	public void delete(long id);
	
	public boolean verifyCustomerNumberByOrder(Order order, Principal principal);
	
}