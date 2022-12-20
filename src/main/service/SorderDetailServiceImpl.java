package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Product;
import main.model.Sorder;
import main.model.SorderDetail;
import main.repository.ProductRepository;
import main.repository.SorderDetailRepository;

@Service
@Transactional
public class SorderDetailServiceImpl implements SorderDetailService {
	
	@Autowired
	private SorderDetailRepository sorderDetailRepository;

	@Override
	public List<SorderDetail> getAll() {
		return sorderDetailRepository.findAll();
	}

	@Override
	public SorderDetail getById(long id) {
		//return productRepository.getOne(id);
		return sorderDetailRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(SorderDetail sorderDetail) {
		sorderDetailRepository.save(sorderDetail);		
	}

	@Override
	public void delete(long id) {
		sorderDetailRepository.deleteById(id);		
	}


	
}