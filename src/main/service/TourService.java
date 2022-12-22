package main.service;

import java.util.List;

import main.model.Tour;

public interface TourService {

	public List<Tour> getAll();

	public Tour getById(long id);

	public void saveOrUpdate(Tour tour);

	public void delete(long id);
	
	public Tour getByIdWithComments(long id);
	
	public void addUserToTour(long id, long userId);

	public List<Tour> getAllforNextMonth();
}
