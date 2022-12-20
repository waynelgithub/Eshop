package main.service;

import java.util.List;

import main.model.Sorder;

public interface SorderService {

	public List<Sorder> getAll();
	
	public Sorder getById(long id);
	
	public void saveOrUpdate(Sorder sorder);
	
	public void delete(long id);
	
}