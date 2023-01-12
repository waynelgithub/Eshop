package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Product;
import main.model.Order;
import main.model.OrderDetail;
import main.repository.ProductRepository;
import main.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;

	
	@Override
	public List<OrderDetail> getAll() {
		return orderDetailRepository.findAll();
	}
	
	@Override
	public List<OrderDetail> getAllByOrderNumber(long orderNumber) {
		return orderDetailRepository.getAllByOrder_OrderNumber(orderNumber);
	}

	@Override
	public OrderDetail getById(long id) {
		//return productRepository.getOne(id);
		return orderDetailRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(OrderDetail orderDetail) {
		orderDetailRepository.save(orderDetail);		
	}

	@Override
	public void delete(long id) {
		orderDetailRepository.deleteById(id);		
	}

	@Override
	public boolean existsByOrderDetailId(long orderDetailId) {
		return orderDetailRepository.existsById(orderDetailId);
	}




	
}