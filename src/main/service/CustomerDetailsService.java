package main.service;

import main.model.CustomerDetails;

public interface CustomerDetailsService {

	public CustomerDetails getById(long id);
	
	public void saveOrUpdate(CustomerDetails customerDetails);
	
	public void delete(long id);
	
}
