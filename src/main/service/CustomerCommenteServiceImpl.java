package main.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.model.CustomerComment;
import main.repository.CustomerCommentRepository;

@Service
@Transactional
public class CustomerCommenteServiceImpl implements CustomerCommentService{

	@Autowired
	private CustomerCommentRepository customerCommentRepository;
	
	@Override
	public void save(CustomerComment customerComment) {
		customerCommentRepository.save(customerComment);		
	}

	
}
