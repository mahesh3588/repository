package dao.vendor;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SessionUtil;
import dao.exception.DAOException;

public class VendorUtil {

	//private static Session
	
	public Integer add(Vendor vendor) throws DAOException  {
		Integer id=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(vendor);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding Vendor details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(Vendor vendor) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(vendor);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating Vendor details : "+e.getMessage());
		}
	}
	
	public void delete(Vendor vendor ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(vendor);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting Vendor  : "+e.getMessage());
		}
		
	}
	
	public Vendor get(Integer vendorId) throws DAOException{
		Vendor item=null;
		try {
		    item = SessionUtil.getSessionFactory().openSession().get(Vendor.class, vendorId);
		    
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Vendor details : "+e.getMessage());
		}
		return item;
	}
	
	public List<Vendor> get() throws DAOException{
		List<Vendor> customerList=Collections.EMPTY_LIST;
		try {
			customerList=SessionUtil.getSessionFactory().openSession().createQuery("from Vendor").list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Vendor details : "+e.getMessage());
		}
		return customerList;
	}
}
