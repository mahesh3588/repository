package dao.ledger.quotation;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import dao.SessionUtil;
import dao.exception.DAOException;

public class QuotationUtil {

	
	public Integer add(Quotation quotation) throws DAOException  {
		Integer id=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(quotation);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding quotation details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(Quotation quotation) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(quotation);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating quotation details : "+e.getMessage());
		}
	}
	
	public void delete(Quotation quotation ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(quotation);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting quotation  : "+e.getMessage());
		}
	}
	
	public Quotation get(Integer quotationId) throws DAOException{
		Quotation quotation=null;
		try {
			quotation = SessionUtil.getSessionFactory().openSession().get(Quotation.class, quotationId);
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting quotation details : "+e.getMessage());
		}
		return quotation;
	}
	
	public List<Quotation> get() throws DAOException{
		List<Quotation> quotationList=null;
		try {
		    Query q=SessionUtil.getSessionFactory().openSession().createQuery("from Quotation");
		    quotationList=q.list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting quotation details : "+e.getMessage());
		}
		return quotationList;
	}
	
	
	public List<Quotation> getCustomerQuotation(Integer customerId) throws DAOException{
		List<Quotation> ledgerEntryList=null;
		try {
			Criteria ledgerCriteria = SessionUtil.getSessionFactory().openSession().createCriteria(Quotation.class);
			ledgerCriteria.add(Restrictions.eq("customerId", customerId));
			ledgerEntryList=ledgerCriteria.list();
			
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Quoatation Ledger details : "+e.getMessage());
		}
		return ledgerEntryList;
	}
	
	
}
