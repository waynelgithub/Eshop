package main.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import main.model.OrderDetail;
import main.model.SalesReturnStatus;
import main.repository.OrderDetailRepository;

@Service
@Transactional
public class OrderDetailServiceImpl implements OrderDetailService {
	
	@Autowired
	private OrderDetailRepository orderDetailRepository;
	
	@Autowired
	private OrderDetailService orderDetailService;

	
	@Override
	public List<OrderDetail> getAll() {
		return orderDetailRepository.findAll();
	}
	
	@Override
	public List<OrderDetail> getAllByOrderNumber(long orderNumber) {
		return orderDetailRepository.getAllByOrder_OrderNumber(orderNumber);
	}

	@Override
	public Optional<OrderDetail> findById(long id) {
		//return orderDetailRepository.getOne(id);
		return orderDetailRepository.findById(id);
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

	@Override
	public boolean placeReturnRequest(long orderDetailId, Principal principal) {
		 //check if orderDetailId exists
			if(!orderDetailService.existsByOrderDetailId(orderDetailId)) {
				//show message in console
				System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't exist.\n");
				return false;
			}
			
			Assert.notNull(orderDetailId, "orderDetailId must not be null or invalid.");
			
		 //verify customerNumber by orderDetailId
			if(!orderDetailService.verifyCustomerNumberByOrderDetailId(orderDetailId, principal)) {
				//show message in console
				System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't belong to him.\n");
				return false;
			}
			
		 //avoid repeatedly place sales return
			OrderDetail orderDetail = orderDetailService.findById(orderDetailId).get();
			if (orderDetail.getSalesReturnStatus().equals(SalesReturnStatus.RETURN_REQUEST_PLACED))
				return false;

		 //create the sales return request		
			orderDetail.setModifedDate(new Date());
			orderDetail.setSalesReturnStatus(SalesReturnStatus.RETURN_REQUEST_PLACED);
			orderDetailService.saveOrUpdate(orderDetail);
			
			return true;
	}

	@Override
	public boolean verifyCustomerNumberByOrderDetailId(long orderDetailId, Principal principal) {

		//null check for orderDetailId
		if(!orderDetailService.existsByOrderDetailId(orderDetailId)) {
			return false;
		}
		
		Assert.notNull(orderDetailId, "orderDetailId must not be null or invalid.");
		
		//verify customerNumber 
			//get existing customerNumber
			String existingCustomerNumber = principal.getName();
			
			//get customerNumber through user input
			OrderDetail orderDetail = orderDetailService.findById(orderDetailId).get();
			String customerNumberToVerify = orderDetail.getOrder().getCustomerNumer();
			
			//verify equality
			if (!customerNumberToVerify.equals(existingCustomerNumber)){
				return false;
			}
		return true;
	}




	
}