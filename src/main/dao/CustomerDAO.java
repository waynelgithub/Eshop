package main.dao;

import java.util.List;

import main.model.Customer;

public interface CustomerDAO {
	
	public List<Customer> getAll();
	
	public Customer getById(long id);
	
	public void saveOrUpdate(Customer customer);
	
	public void delete(long id);

}
