package main.service;

import java.security.Principal;
import java.util.List;

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
	
	@Autowired
	private OrderService orderService;

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
	public boolean verifyCustomerNumberByOrderNumber(long orderNumber, Principal principal) {
		//null check for orderNumber
		if(!orderRepository.existsById(orderNumber)) {
			//show message in console
			System.out.println("\nSomeone tried to access the orderNumber: " + orderNumber + " that doesn't exist.\n");
			return false;
		}
		//verify customerNumber 
			//get existing customerNumber
			String existingCustomerNumber = principal.getName();
			
			//get customerNumber through user input
			Order order = orderService.getById(orderNumber);
			String customerNumberToVerify = order.getCustomerNumer();
			
			//verify equality
			if (!customerNumberToVerify.equals(existingCustomerNumber)){
				//show message in console
				System.out.println("\nSomeone tried to access the orderNumber: " + orderNumber + " that doesn't belong to him.\n");				
				return false;
			}
		return true;
	}


	
}