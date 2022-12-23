package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Order;
import main.repository.OrderRepository;
/**
 * 
 * @author hsu
 *
 */
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


	
}