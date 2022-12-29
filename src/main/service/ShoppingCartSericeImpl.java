package main.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.model.User;
import main.repository.ShoppingCartDetailsRepository;
import main.repository.ShoppingCartRepository;
import main.repository.UserRepository;

@Service
@Transactional
public class ShoppingCartSericeImpl implements ShoppingCartService {

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
	public ShoppingCart getByCustomerNum(long id) {
		return shoppingCartRepository.getByCustomerNum(id);
	}

	@Override
	public List<ShoppingCart> findFirstByOrderById() {
		return shoppingCartRepository.findFirstByOrderById();
	}
	
	@Override
	public void sumAmount(long id) {
		ShoppingCart shoppingCart = getByCustomerNum(id);
		BigDecimal amount = new BigDecimal(0.0);
		
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
		User user = userRepository.findByLogin(login);
		return shoppingCartRepository.getByCustomerNum(user.getId());
	}
	
	@Override
	public long getCusNum(String login) {
		User user = userRepository.findByLogin(login);
		return user.getId();		
	}

	@Override
	public ShoppingCart showShoppingCart(long customerNum) {
		ShoppingCart shoppingCart = getByCustomerNum(customerNum);
		
		if (shoppingCart == null) {
			List<ShoppingCartDetails> shoppingCartDetails = new ArrayList<>();
			shoppingCart = new ShoppingCart();
			shoppingCart.setAmount(new BigDecimal(0));
			shoppingCart.setCustomer_num(customerNum);
			shoppingCart.setDate(new Date());
			shoppingCart.setShoppingCartDetails(shoppingCartDetails);
			saveOrUpdate(shoppingCart);
		} else {
			sumAmount(customerNum);
		}
		return shoppingCart;
	}
}
