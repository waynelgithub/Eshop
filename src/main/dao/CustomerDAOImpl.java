package main.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import main.model.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public List<Customer> getAll() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("from Customer c", Customer.class).list();
	}

	@Override
	@Transactional
	public Customer getById(long id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Customer.class, id);
	}

	@Override
	@Transactional
	public void saveOrUpdate(Customer customer) {
		Session session = sessionFactory.getCurrentSession();	
		session.saveOrUpdate(customer);
	}

	@Override
	@Transactional
	public void delete(long id) {
		Session session = sessionFactory.getCurrentSession();	
		Customer customer = getById(id);
		session.delete(customer);	
	}
	
	

}
