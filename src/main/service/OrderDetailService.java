package main.service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import main.model.OrderDetail;

public interface OrderDetailService {

	public List<OrderDetail> getAll();
	
	public List<OrderDetail> getAllByOrderNumber(long orderNumber);
	
	public Optional<OrderDetail> findById(long id);
	
	public OrderDetail saveOrUpdate(OrderDetail orderDetail);
	
	public void delete(long id);
	
	public boolean existsByOrderDetailId(long orderDetailId);
	
	public boolean verifyCustomerNumberByOrderDetail(OrderDetail orderDetail, Principal principal);
	
	public boolean isNonReturnable(OrderDetail orderDetail);
	
	public boolean isRepeatedSalesReturnRequest(OrderDetail orderDetail);
	
	public void placeReturnRequest(OrderDetail orderDetail);
	
}