package main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import main.dao.SorderDAO;
import main.model.Sorder;

@Service
@Transactional
public class SorderServiceImpl implements SorderService {
	
	@Autowired
	private SorderDAO sorderDAO;

	@Override
	public List<Sorder> getAll() {
		return sorderDAO.getAll();
	}

	@Override
	public Sorder getById(long id) {
		return sorderDAO.getById(id);
	}

	@Override
	public void saveOrUpdate(Sorder sorder) {
		sorderDAO.saveOrUpdate(sorder);		
	}

	@Override
	public void delete(long id) {
		sorderDAO.delete(id);		
	}


	
}