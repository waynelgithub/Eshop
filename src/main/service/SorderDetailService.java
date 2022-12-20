package main.service;

import java.util.List;

import main.model.SorderDetail;


public interface SorderDetailService {

	public List<SorderDetail> getAll();
	
	public SorderDetail getById(long id);
	
	public void saveOrUpdate(SorderDetail sorderDetail);
	
	public void delete(long id);
	
}