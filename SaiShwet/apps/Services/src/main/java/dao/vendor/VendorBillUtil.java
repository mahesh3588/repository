package dao.vendor;

import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SessionUtil;
import dao.exception.DAOException;

public class VendorBillUtil {

	//private static Session
	
	public Integer add(VendorBill vendorBill) throws DAOException  {
		Integer id=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(vendorBill);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding VendorBill details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(VendorBill vendorBill) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(vendorBill);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating VendorBill details : "+e.getMessage());
		}
	}
	
	public void delete(VendorBill vendorBill ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(vendorBill);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting VendorBill  : "+e.getMessage());
		}
		
	}
	
	public VendorBill get(Integer vendorBillId) throws DAOException{
		VendorBill vendorBill=null;
		try {
			vendorBill = SessionUtil.getSessionFactory().openSession().get(VendorBill.class, vendorBillId);
		    
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting VendorBill details : "+e.getMessage());
		}
		return vendorBill;
	}
	
	@SuppressWarnings("unchecked")
	public List<VendorBill> get() throws DAOException{
		List<VendorBill> vendorBills=Collections.EMPTY_LIST;
		try {
			vendorBills=SessionUtil.getSessionFactory().openSession().createQuery("from VendorBill").list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting VendorBill details : "+e.getMessage());
		}
		return vendorBills;
	}
}
