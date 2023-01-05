package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Service;

import main.model.Customer;
import main.model.CustomerDetails;
import main.model.Role;
import main.model.User;
import main.repository.CustomerDetailsRepository;
import main.repository.CustomerRepository;
import main.repository.RoleRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerDetailsRepository customerDetailsRepository;
	
	@Override
	public void createNewAccount(User user) {
		user.setEnabled(true);
		user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
		userRepository.save(user);
					
		Role role = new Role();
		role.setRole("ROLE_CLIENT");
		role.setLogin(user.getLogin());
		roleRepository.save(role);
		
		Customer customer = new Customer();
		CustomerDetails customerDetail = new CustomerDetails();
		customerDetail.setCustomerNum(user.getLogin());
		customerDetail.setEmail("");
		customerDetail.setMobile("");
		customerDetailsRepository.save(customerDetail);
		customer.setCounty("");
		customer.setCustomerNum(user.getLogin());
		customer.setName(user.getLogin());
		customer.setCustomerDetails(customerDetail);
		customerRepository.save(customer);
	}

	@Override
	public boolean loginExists(String login) {
		return userRepository.existsByLogin(login);
	}
	
	@Override
	public User getById(long id) {
		return userRepository.findById(id).get();
	}

}
