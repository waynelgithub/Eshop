package main.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Order;
import main.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	
	
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order getById(long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(Order order) {
		orderRepository.save(order);		
	}

	@Override
	public void delete(long id) {
		orderRepository.deleteById(id);		
	}

	@Override
	public List<Order> getOrdersByCustomerId(String customerNumber) {

		return orderRepository.findAllByCustomerNumerOrderByOrderNumberDesc(customerNumber);
	}

	@Override
	public boolean verifyCustomerNumberByOrder(Order order, Principal principal) {
		
		assert order != null;
		
		//get existing customerNumber
		String existingCustomerNumber = principal.getName();
		
		//get customerNumber through user input
		String customerNumberToVerify = order.getCustomerNumer();
		
		//verify equality
		if (!customerNumberToVerify.equals(existingCustomerNumber))	return false;
		
		return true;
	}

	@Override
	public Optional<Order> findById(long id) {

		return orderRepository.findById(id);
	}


	
}