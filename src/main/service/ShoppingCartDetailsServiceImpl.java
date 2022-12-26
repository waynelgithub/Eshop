package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.ShoppingCartDetails;
import main.repository.ShoppingCartDetailsRepository;

@Service
@Transactional
public class ShoppingCartDetailsServiceImpl implements ShoppingCartDetailsService {

	
	@Autowired
	private ShoppingCartDetailsRepository shoppingCartDetailsRepository;
	
	@Override
	public ShoppingCartDetails getById(long id) {
		return shoppingCartDetailsRepository.getOne(id);
	}

	@Override
	public void saveOrUpdate(ShoppingCartDetails shoppingCartDetails) {
		shoppingCartDetailsRepository.save(shoppingCartDetails);
	}

	@Override
	public void delete(long id) {
		shoppingCartDetailsRepository.deleteById(id);
	}
	
	

}
