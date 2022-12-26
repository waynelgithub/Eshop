package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.ProductImage;
import main.repository.ProductImageRepository;


@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
	private ProductImageRepository productImageRepository;

	@Override
	public List<ProductImage> getAll() {
		return productImageRepository.findAll();
	}

	@Override
	public ProductImage getById(long id) {
		
		return productImageRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(ProductImage productimage) {
		productImageRepository.save(productimage);
		
	}

	@Override
	public void delete(long id) {
		productImageRepository.deleteById(id);
		
	}


	
}