package main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.model.User;
import main.repository.ShoppingCartRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class ShoppingCartSericeImpl implements ShoppingCartService {
	
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<ShoppingCart> getAll() {
		return shoppingCartRepository.findAll();
	}

	@Override
	public ShoppingCart getById(long id) {
		return shoppingCartRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(ShoppingCart shoppingCart) {
		shoppingCartRepository.save(shoppingCart);
	}

	@Override
	public void delete(long id) {
		shoppingCartRepository.deleteById(id);
	}
	
	@Override
	public ShoppingCart getByIdWithShoppingCartDetails(long id) {
		return shoppingCartRepository.getByIdWithShoppingCartDetails(id);
	}
	
	@Override
	public ShoppingCart getByCustomerNum(String customer_num) {
		return shoppingCartRepository.getByCustomerNum(customer_num);
	}

	@Override
	public List<ShoppingCart> findFirstByOrderById() {
		return shoppingCartRepository.findFirstByOrderById();
	}
	
	@Override
	public void sumAmount(String customer_num) {
		ShoppingCart shoppingCart = getByCustomerNum(customer_num);
		BigDecimal amount = new BigDecimal(0);
		
		if (shoppingCart != null) {
			List<ShoppingCartDetails> shoppingCartDetails = shoppingCart.getShoppingCartDetails();
			if (!shoppingCartDetails.isEmpty()) {
				for(int i = 0; i < shoppingCartDetails.size(); i++) {
					BigDecimal qty = new BigDecimal(shoppingCartDetails.get(i).quantity);
					BigDecimal price = shoppingCartDetails.get(i).productPrice;
					amount = amount.add((price.multiply(qty)));
				}
			}
		}
		shoppingCart.setAmount(amount);
		saveOrUpdate(shoppingCart);
	}
	
	@Override
	public ShoppingCart setCustomerNum(String login) {
		return shoppingCartRepository.getByCustomerNum(login);
	}
	
	@Override
	public long getCusNum(String login) {
		User user = userRepository.findByLogin(login);
		return user.getId();		
	}

	@Override
	public ShoppingCart showShoppingCart(String customerNum) {
		ShoppingCart shoppingCart = getByCustomerNum(customerNum);
		
		logger.info("\n" + "shoppingCart is null: " + (shoppingCart == null) + "\n");
		
		if (shoppingCart == null) {
			BigDecimal amount = new BigDecimal(0);
			shoppingCart = new ShoppingCart();
			shoppingCart.setAmount(amount);
			shoppingCart.setCustomer_num(customerNum);
			shoppingCart.setDate(new Date());
			
			//雖然是空 list，不提供則前端在第一次呈現購物車時會報錯
			List<ShoppingCartDetails> shoppingCartDetails = new ArrayList<>();
			shoppingCart.setShoppingCartDetails(shoppingCartDetails);
			saveOrUpdate(shoppingCart);
		} 
				
		else {
			sumAmount(customerNum);
		}
		return shoppingCart;
	}
}
