package main.dao;

import java.util.List;

import main.model.Tour;

public interface TourDAO {

	public List<Tour> getAll();
	
	public Tour getById(long id);
	
	public void saveOrUpdate(Tour tour);
	
	public void delete(long id);
	
}
