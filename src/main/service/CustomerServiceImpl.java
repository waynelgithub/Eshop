package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Customer;
import main.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public List<Customer> getAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer getById(long id) {
		return customerRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public void delete(long id) {
		customerRepository.deleteById(id);

	}

	@Override
	public Customer getByIdWithComments(long id) {
		return customerRepository.getByIdWithComments(id);
	}

}
