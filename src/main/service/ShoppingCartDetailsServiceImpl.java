package main.service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.Product;
import main.model.ShoppingCart;
import main.model.ShoppingCartDetails;
import main.repository.ProductRepository;
import main.repository.ShoppingCartDetailsRepository;
import main.repository.ShoppingCartRepository;

@Service
@Transactional
public class ShoppingCartDetailsServiceImpl implements ShoppingCartDetailsService {

	
	@Autowired
	private ShoppingCartDetailsRepository shoppingCartDetailsRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	@Override
	public List<ShoppingCartDetails> getAll() {
		return shoppingCartDetailsRepository.findAll();
	}

	@Override
	public ShoppingCartDetails getById(long id) {
		return shoppingCartDetailsRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(ShoppingCartDetails shoppingCartDetails) {
		shoppingCartDetailsRepository.save(shoppingCartDetails);
	}

	@Override
	public void delete(long id) {
		shoppingCartDetailsRepository.deleteById(id);		
	}
	
	@Override
	public void deleteByIdWithShoppingCartDetails(long id) {
		shoppingCartDetailsRepository.deleteByIdWithShoppingCartDetails(id);
	}
	
	@Override
	public ShoppingCartDetails getByProductNum(long num) {
		return shoppingCartDetailsRepository.getByProductNum(num);
	}
	
	@Override
	public long getProductNum(long productId) {
		Product product = productRepository.getOne(productId);
		return product.getProdNum();		
	}

	@Override
	public ShoppingCartDetails addShoppingCartDetail(long productId, String customerNum) {
		
		Product product = productRepository.findById(productId).get();
		
		ShoppingCart shoppingCart = shoppingCartRepository.getByCustomerNum(customerNum);
		if (shoppingCart == null) {
			BigDecimal amount = new BigDecimal(0);
			shoppingCart = new ShoppingCart();
			shoppingCart.setAmount(amount);
			shoppingCart.setCustomer_num(customerNum);
			shoppingCart.setDate(new Date());
		}
		shoppingCartRepository.save(shoppingCart);
		
		ShoppingCartDetails shoppingCartDetail = shoppingCartDetailsRepository.getByProductNum(product.getProdNum());
		if (shoppingCartDetail == null) {
			shoppingCartDetail = new ShoppingCartDetails();
			shoppingCartDetail.setProductName(product.getProdName());
			shoppingCartDetail.setProductNum(product.getProdNum());
			shoppingCartDetail.setProductPrice(product.getProdPrice());
			shoppingCartDetail.setQuantity(1);
			shoppingCartDetail.setShoppingCart(shoppingCart);
		}
		return shoppingCartDetail;
	}
}
