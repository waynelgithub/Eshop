package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Product;
import main.repository.ProductRepository;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<Product> getAll() {
		return productRepository.findAll();
	}
	
	@Override
	public List<Product> getAllWithImage() {
		
		return productRepository.findByIdIsNotNull();
	}

	@Override
	public Product getById(long id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	public Product getByIdWithImage(long id) {
		return productRepository.getById(id);
	}

	@Override
	public void saveOrUpdate(Product product) {
		productRepository.save(product);		
	}

	@Override
	public void delete(long id) {
		productRepository.deleteById(id);		
	}






	
}