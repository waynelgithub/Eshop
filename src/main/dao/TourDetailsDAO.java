package main.dao;

import main.model.TourDetails;

public interface TourDetailsDAO {

	public TourDetails getById(long id);
	
	public void saveOrUpdate(TourDetails tourDetails);
	
	public void delete(long id);
	
}
