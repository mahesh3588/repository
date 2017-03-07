package dao.customer;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.SessionUtil;
import dao.exception.DAOException;

public class CustomerUtil {

	//private static Session
	
	public Integer add(Customer customer) throws DAOException  {
		Integer id=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(customer);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding Customer details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(Customer customer) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(customer);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating Customer details : "+e.getMessage());
		}
	}
	
	public void delete(Customer customer ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(customer);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting Customer  : "+e.getMessage());
		}
		
	}
	
	public Customer get(Integer customerId) throws DAOException{
		Customer item=null;
		try {
		    item = SessionUtil.getSessionFactory().openSession().get(Customer.class, customerId);
		    
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Customer details : "+e.getMessage());
		}
		return item;
	}
	
	public Customer get(String customerName) throws DAOException{
		Customer item=null;
		try {
		    Session session = SessionUtil.getSessionFactory().openSession();
		    Criteria criteria = session.createCriteria(Customer.class);
		    Restrictions.eq("name", customerName);
		    
		    
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Customer details : "+e.getMessage());
		}
		return item;
	}
	
	public List<Customer> get() throws DAOException{
		List<Customer> customerList=Collections.EMPTY_LIST;
		try {
			customerList=SessionUtil.getSessionFactory().openSession().createQuery("from Customer").list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Customer details : "+e.getMessage());
		}
		return customerList;
	}
}
