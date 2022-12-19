package main.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import main.model.Sorder;

@Repository
public class SorderDAOImpl implements SorderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Sorder> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Sorder s", Sorder.class).list();
	}

	@Override
	public Sorder getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Sorder.class, id);
	}

	@Override
	public void saveOrUpdate(Sorder sorder) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(sorder);
	}

	@Override
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();
		Sorder sorder = getById(id);
		session.delete(sorder);
	}

	
}
