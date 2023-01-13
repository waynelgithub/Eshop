package main.service;

import java.security.Principal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Override
	public String placeReturnRequest(long orderDetailId, Principal principal) {
		  //check if orderDetailId exists
			if(!orderDetailService.existsByOrderDetailId(orderDetailId)) {
				//show message in console
				System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't exist.\n");
				return "redirect:/";
			}
			
		  //verify customerNumber by orderDetailId
			if(!orderDetailService.verifyCustomerNumberByOrderDetailId(orderDetailId, principal)) {
				//show message in console
				System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't belong to him.\n");
				return "redirect:/";
			}
			
		  //create the sales return request
			OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
			orderDetail.setModifedDate(new Date());
			orderDetail.setSalesReturnStatus(SalesReturnStatus.RETURN_REQUEST_PLACED);
			orderDetailService.saveOrUpdate(orderDetail);
			
			return "redirect:/show-my-order-details/"+orderDetail.getOrder().getOrderNumber();
	}

	@Override
	public boolean verifyCustomerNumberByOrderDetailId(long orderDetailId, Principal principal) {

		//null check for orderDetailId
		if(!orderDetailService.existsByOrderDetailId(orderDetailId)) {
			return false;
		}
		//verify customerNumber 
			//get existing customerNumber
			String existingCustomerNumber = principal.getName();
			
			//get customerNumber through user input
			OrderDetail orderDetail = orderDetailService.getById(orderDetailId);
			String customerNumberToVerify = orderDetail.getOrder().getCustomerNumer();
			
			//verify equality
			if (!customerNumberToVerify.equals(existingCustomerNumber)){
				return false;
			}
		return true;
	}




	
}