package main.service;

import java.util.List;

import main.model.OrderDetail;

public interface OrderDetailService {

	public List<OrderDetail> getAll();
	
	public OrderDetail getById(long id);
	
	public void saveOrUpdate(OrderDetail orderDetail);
	
	public void delete(long id);
	
}