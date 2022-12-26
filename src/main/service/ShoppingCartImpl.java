package main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.ShoppingCart;
import main.repository.CustomerRepository;
import main.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartImpl implements ShoppingCartService {

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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

}
