package main.service;

import java.util.List;

import main.model.OrderDetail;

public interface OrderDetailService {

	public boolean existsByOrderDetailId(long orderDetailId);
	
	public List<OrderDetail> getAll();
	
	public List<OrderDetail> getAllByOrderNumber(long orderNumber);
	
	public OrderDetail getById(long id);
	
	public void saveOrUpdate(OrderDetail orderDetail);
	
	public void delete(long id);
	
}