package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.model.Sorder;
import main.repository.SorderRepository;

@Service
@Transactional
public class SorderServiceImpl implements SorderService {
	
	
	@Autowired
	private SorderRepository sorderRepository;

	@Override
	public List<Sorder> getAll() {
		return sorderRepository.findAll();
	}

	@Override
	public Sorder getById(long id) {
		return sorderRepository.findById(id).get();
	}

	@Override
	public void saveOrUpdate(Sorder sorder) {
		sorderRepository.save(sorder);		
	}

	@Override
	public void delete(long id) {
		sorderRepository.deleteById(id);		
	}


	
}