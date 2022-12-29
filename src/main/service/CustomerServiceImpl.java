package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Customer;
import main.model.User;
import main.repository.CustomerRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private UserRepository userRepository;
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
	public Customer getByUserId(long id) {
		return customerRepository.getByUserId(id);
	}

	@Override
	public long getUserId(String name) {
		User user = userRepository.findByLogin(name);
		return user.getId();
	}
}
