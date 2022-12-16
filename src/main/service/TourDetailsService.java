package main.service;

import main.model.TourDetails;

public interface TourDetailsService {
	
	public TourDetails getById(long id);
	
	public void saveOrUpdate(TourDetails tourDetails);
	
	public void delete(long id);

}
