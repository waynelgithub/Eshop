package main.service;

import java.util.List;

import main.model.Customer;

public interface CustomerService {

	public List<Customer> getAll();

	public Customer getById(long id);

	public void saveOrUpdate(Customer customer);

	public void delete(long id);
	
	public Customer getByUserId(long id);
	
	public long getUserId(String name);
}
