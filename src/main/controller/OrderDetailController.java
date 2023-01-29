package main.controller;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import main.helper.UserHelper;
import main.model.Order;
import main.model.OrderDetail;
import main.model.Product;
import main.model.Role;
import main.service.OrderDetailService;
import main.service.OrderService;
import main.service.ProductService;
import main.service.RoleService;

@Controller
public class OrderDetailController {

	@Autowired
	private OrderDetailService orderDetailService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private RoleService roleService;
	
	
//	@GetMapping("/add-order-detail")
//	public String showOrderDetailForm(Model model) {
//		List<Order> orders = orderService.getAll();
//		model.addAttribute("orders", orders);
//		List<Product> products = productService.getAll();
//		model.addAttribute("products", products);		
//		
//		model.addAttribute("orderDetail", new OrderDetail());
//		return "order-detail-form";
//	}
	
//	@PostMapping("/save-order-detail")
//	public String saveOrderDetailData(@Valid @ModelAttribute OrderDetail orderDetail, BindingResult bindingResult) {
//		if(bindingResult.hasErrors()) {
//			return "order-detail-form";
//		}
//		orderDetailService.saveOrUpdate(orderDetail);
//		return "redirect:/show-order-details";
//	}
	
	// for admin to show all users' order details
	@GetMapping("/show-all-order-details")
	public String getAllOrderDetails(Model model) {
		List<OrderDetail> orderDetails=orderDetailService.getAll();
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	

	@GetMapping(value = {"/show-order-details2/{orderNumber}"})
	public String getMyOrderDetails2(Model model, 
									@PathVariable long orderNumber,
									Principal principal, 
//									Authentication authentication,
									HttpServletRequest request) {	

		//null check for orderNumber
		Optional<Order> orderOptional = orderService.findById(orderNumber);
		System.out.println("\n Got order Optional!\n");
		
		if(orderOptional.isEmpty()) {
			//show message in console
			System.out.println("\nSomeone tried to access the orderNumber: " + orderNumber + " that doesn't exist.\n");
			return "redirect:/";
		}

		Order order = orderOptional.get();
		
		assert order != null;
		//assert request.getUserPrincipal() != null;
		
		// verify user privileges
		
		//0.1 verified customerNumber or EMPLOYEE or ADMIN can access data
		//String currentUserRole = roleService.findRoleByLogin(principal.getName()).getRole();
		//if (!orderService.verifyCustomerNumberByOrder(order, principal) & !currentUserRole.equals("ROLE_EMPLOYEE")  & !currentUserRole.equals("ROLE_ADMIN")   ) {
			//show message in console
		//	System.out.println("\nSomeone tried to access the orderNumber: " + order.getOrderNumber() + " that doesn't belong to him.\n");	
		//	return "redirect:/";
		//}
		//
		
		//1.1 inject an Authentication
		//Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		//authorities.forEach(au -> System.out.println("\n" + au.getAuthority()));// ex: ROLE_EMPLOYEE
		//boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
		//boolean isEmployee = authorities.stream().anyMatch(a -> a.getAuthority().equals("EMPLOYEE"));
		
		//1.2 get an Authentication through SecurityContextHolder
		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		//Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
		//authorities.forEach(au -> System.out.println("\n" + au.getAuthority()));// ex: ROLE_EMPLOYEE
		//boolean isAdmin = authorities.stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
		//boolean isEmployee = authorities.stream().anyMatch(a -> a.getAuthority().equals("EMPLOYEE"));
		
		//1.3 in Spring MVC, inject an HttpServletRequest
		boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
		boolean isEmployee = request.isUserInRole("ROLE_EMPLOYEE");

		System.out.println("");
		System.out.println("isAdmin: " + isAdmin);
		System.out.println("isEmployee: " + isEmployee);
		System.out.println("");
		
		//2.1 verify customerNumber and user privileges
//		if (!orderService.verifyCustomerNumberByOrder(order, principal) && !isAdmin && !isEmployee ) {
//			//show message in console
//			System.out.println("\nSomeone tried to access the orderNumber: " + order.getOrderNumber() + " that doesn't belong to him.\n");	
//			return "redirect:/";
//		}
		//2.2 verify customerNumber and user privileges. 檢查大量 role
		//2.2.1 取得此功能，且不需 customerNumber(也就不需 ROLE_CLIENT)，即可查看的 roles
		List<String> roleStrings = List.of("ROLE_ADMIN","ROLE_EMPLOYEE");
		
		if(!orderService.verifyCustomerNumberByOrder(order, principal) && !UserHelper.hasAnyManagementRoleByHttpServletRequest(roleStrings, request)) {
			//show message in console
			System.out.println("\nSomeone tried to access the orderNumber: " + order.getOrderNumber() + " that doesn't belong to him.\n");	
			return "redirect:/";			
		}	
		
		System.out.println("\n" +"Verified order's customerNumber and user privileges" + "\n");
		
		//prepare orderDetails to show
		List<OrderDetail> orderDetails=orderDetailService.getAllByOrderNumber(orderNumber);
		System.out.println("\nGot order detail list!\n");
		// verify data in console
		System.out.println(orderDetails);
		
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}

	//method for test SPEL purpose only
	@PreAuthorize(//"hasAnyRole('EMPLOYEE', 'ADMIN')" +
					" (hasRole('CLIENT') && #principal.getName().equals( @orderServiceImpl.findById(#orderNumber).get().getCustomerNumer() ))" +
//同上式//			" (hasRole('CLIENT') && #principal.name.equals( @orderServiceImpl.findById(#orderNumber).get().customerNumer ))" +
			//"|| @orderServiceImpl.findById(#orderNumber).get().getOrderStatus().equals(T(main.model.OrderStatus).OPEN)" +
			"|| new main.helper.UserHelperTestSPEL().hasAnyManagementRoleByHttpServletRequest( T(java.util.List).of('ROLE_ADMIN','ROLE_EMPLOYEE'), #request)"
			)
	@GetMapping(value = {"/show-order-details/{orderNumber}"})
	public String getMyOrderDetails(Model model, 
									@PathVariable long orderNumber,
									Principal principal,
									HttpServletRequest request) {	

		//null check for orderNumber
		Optional<Order> orderOptional = orderService.findById(orderNumber);
		System.out.println("\n Got order Optional!\n");
		
		if(orderOptional.isEmpty()) {
			//show message in console
			System.out.println("\nSomeone tried to access the orderNumber: " + orderNumber + " that doesn't exist.\n");
			return "redirect:/";
		}

		Order order = orderOptional.get();
		
		assert order != null;
		
		
		//1.3 in Spring MVC, inject an HttpServletRequest
		boolean isAdmin = request.isUserInRole("ROLE_ADMIN");
		boolean isEmployee = request.isUserInRole("ROLE_EMPLOYEE");

		System.out.println("");
		System.out.println("isAdmin: " + isAdmin);
		System.out.println("isEmployee: " + isEmployee);
		System.out.println("");
				
		//prepare orderDetails to show
		List<OrderDetail> orderDetails=orderDetailService.getAllByOrderNumber(orderNumber);
		System.out.println("\nGot order detail list!\n");
		// verify data in console
		System.out.println(orderDetails);
		
		model.addAttribute("orderDetails", orderDetails);
		return "order-details";
	}
	
//	/** 
//	 * for admin to delete orderDetail, not yet to update the order which contains it
//	 *  
//	 * @param orderDetailId
//	 * @return a string as a view to show a list of orderDetails
//	 */
//	@GetMapping("/delete-order-detail/{orderDetailId}")
//	public String deleteOrderDetail(@PathVariable long orderDetailId) {
//		//get the existing record from data source in order to delete it.
//		//
//		Order order = new Order();//為了要在刪掉 orderDetail後，還能用 order.getOrderNumber 來呈現 view
//		
//		Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(orderDetailId);
//		orderDetailOptional.ifPresent(
//				orderDetail -> {
//					order.setOrderNumber(orderDetail.getOrder().getOrderNumber());
//					orderDetailService.delete(orderDetail.getOrderDetailId());//如果先刪掉 orderDetail ，就取不到 order 了
//		
//					});
//		
//		//Assert.notNull(order.getOrderNumber(), "orderNumber must not be null");//錯誤比對，還可能會是 0 -> null 只比對 object
//		
//		return "redirect:/show-order-details/" + order.getOrderNumber();
//	}
	
//	/**
//	 * for admin to edit an OrderDetail. view is not ready.
//	 * <p>Get an Optional<Orderdetail>,
//	 * <br>if existed, add to Model and send it to View for editing the content of OrderDetail.
//	 * <br>if not, redirect to home page. 
//	 * @param orderDetailId
//	 * @param model
//	 * @return a string as view
//	 */
//	@GetMapping("/edit-order-detail/{orderDetailId}")
//	public String editOrderDetail(@PathVariable long orderDetailId, Model model) {
//
//		Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(orderDetailId);		
//
//		String returnUrl = 
//				orderDetailOptional
//					.map( // if value is present
//							orderDetail -> {
//								model.addAttribute("orderDetail", orderDetail); 
//								return "order-detail-form";})
//					.or(
//							() -> Optional.of("redirect:/"))
//					.get()
//					;
//		
//		return returnUrl;
//	}
	
	/**	
	 * Customer place return request for his/her own order
	 * @param orderDetailId 訂單明細編號
	 * @param principal current Principal object
	 * @return redirect to URL to show the order details
	 */
	@GetMapping("/place-return-request/{orderDetailId}")
	public String placeReturnRequest(@PathVariable long orderDetailId, Principal principal) {
	
	 //check if orderDetailId exists and prepare the orderDetail object
		Optional<OrderDetail> orderDetailOptional = orderDetailService.findById(orderDetailId);
		System.out.println("\nCheck if orderDetailId exists and prepare the orderDetail Optional\n");

		if (orderDetailOptional.isEmpty()) {
			//show message in console
			System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't exist.\n");
			return "redirect:/";		
		}
		
		OrderDetail orderDetail = orderDetailOptional.get();
		System.out.println("\nGot the orderDetail!\n");
		
		Assert.notNull(orderDetail, "orderDetail must not be null.");
		
		long orderNumber = orderDetail.getOrder().getOrderNumber();

	 //verify customerNumber by orderDetail
		if(!orderDetailService.verifyCustomerNumberByOrderDetail(orderDetail, principal)) {
			//show message in console
			System.out.println("\nSomeone tried to change the status of orderDetailId: " + orderDetailId + " that doesn't belong to him.\n");
			return "redirect:/";
		}
		System.out.println("\nVerified customerNumber!\n");
		
	 //reject return request for item marked as NON_RETURNABLE
		if(orderDetailService.isNonReturnable(orderDetail)) {
			System.out.println("\nNon-Returnable item. orderDetailId: " + orderDetailId + "\n");
			return "redirect:/show-order-details/" + orderNumber;
			//better to show a warning message
		}
		System.out.println("\nVerified Non-returnable item\n");
		
	 //avoid repeatedly place sales return
		if (orderDetailService.isRepeatedSalesReturnRequest(orderDetail)) {
			System.out.println("\nRepeated sales return request of orderDetailId: " + orderDetailId + "\n");
			return "redirect:/show-order-details/" + orderNumber;
			//better to show a warning message
		}
		System.out.println("\nVerified repeatedly placing sales return\n");
	 //上面兩個檢查，似乎可以整合成一個，去檢查該 orderDetail 的 isReturnable()就可以
	
	 //change sales return status to place return request
		System.out.println("\nStart to place return request!\n");
		
		orderDetailService.placeReturnRequest(orderDetail); 	
		
		System.out.println("\nFinished return request!\n");
	
		return "redirect:/show-order-details/" + orderNumber;
		
	}
	
}
