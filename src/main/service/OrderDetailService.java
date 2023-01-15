package main.service;

import java.security.Principal;
import java.util.List;

import main.model.OrderDetail;

public interface OrderDetailService {

	public List<OrderDetail> getAll();
	
	public List<OrderDetail> getAllByOrderNumber(long orderNumber);
	
	public OrderDetail getById(long id);
	
	public void saveOrUpdate(OrderDetail orderDetail);
	
	public void delete(long id);
	
	// customer place return request for his/her own order
	public boolean placeReturnRequest(long orderDetailId, Principal principal);
	
	public boolean existsByOrderDetailId(long orderDetailId);
	
	public boolean verifyCustomerNumberByOrderDetailId(long orderDetailId, Principal principal);
	
}