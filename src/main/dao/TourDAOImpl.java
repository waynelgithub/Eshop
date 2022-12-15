package main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.Tour;

@Repository
public class TourDAOImpl implements TourDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Tour> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Tour t", Tour.class).list();
	}

	@Override
	@Transactional
	public Tour getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Tour.class, id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Tour tour) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(tour);
	}

	@Override
	@Transactional
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Tour tour = getById(id);
		session.delete(tour);
	}

	
}
