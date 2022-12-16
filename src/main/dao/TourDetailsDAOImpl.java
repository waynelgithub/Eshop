package main.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.TourDetails;

@Repository
public class TourDetailsDAOImpl implements TourDetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public TourDetails getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(TourDetails.class, id);
	}

	@Override
	public void saveOrUpdate(TourDetails tourDetails) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(tourDetails);
	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		TourDetails tourDetails = getById(id);
		session.delete(tourDetails);
	}
	
	
}
