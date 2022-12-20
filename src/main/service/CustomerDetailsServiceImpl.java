package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.CustomerDetails;
import main.repository.CustomerDetailsRepository;

@Service
@Transactional
public class CustomerDetailsServiceImpl implements CustomerDetailsService{

	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	
	@Override
	public CustomerDetails getById(long id) {
		return customerDetailsRepository.getOne(id);
	}

	@Override
	public void saveOrUpdate(CustomerDetails customerDetails) {
		customerDetailsRepository.save(customerDetails);
	}

	@Override
	public void delete(long id) {
		customerDetailsRepository.deleteById(id);
	}
	
}
