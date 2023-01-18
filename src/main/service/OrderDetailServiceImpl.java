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

		return orderDetailRepository.findById(id);
	}

	@Override
	public OrderDetail saveOrUpdate(OrderDetail orderDetail) {

		return orderDetailRepository.save(orderDetail);		
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
	public void placeReturnRequest(OrderDetail orderDetail) {

			Assert.notNull(orderDetail, "orderDetail must not be null.");

		 //create the sales return request			
			orderDetail.setModifedDate(new Date());
			orderDetail.setSalesReturnStatus(SalesReturnStatus.RETURN_REQUEST_PLACED);
			orderDetailService.saveOrUpdate(orderDetail);
	}

	@Override
	public boolean verifyCustomerNumberByOrderDetail(OrderDetail orderDetail, Principal principal) {

		Assert.notNull(orderDetail, "orderDetail must not be null.");
		
		//verify customerNumber 
			//get existing customerNumber
			String existingCustomerNumber = principal.getName();
			
			//get customerNumber through user input
			String customerNumberToVerify = orderDetail.getOrder().getCustomerNumer();
			
			//verify equality
			if (!customerNumberToVerify.equals(existingCustomerNumber)){
				return false;
			}
		return true;
	}
	
	@Override
	public boolean isRepeatedSalesReturnRequest(OrderDetail orderDetail) {
		
		Assert.notNull(orderDetail, "orderDetail must not be null.");
		
		return (orderDetail.getSalesReturnStatus().equals(SalesReturnStatus.RETURN_REQUEST_PLACED));
	}

	@Override
	public boolean isNonReturnable(OrderDetail orderDetail) {
		
		Assert.notNull(orderDetail, "orderDetail must not be null.");
			
		return (orderDetail.getSalesReturnStatus().equals(SalesReturnStatus.NON_RETURNABLE));
	}




	
}