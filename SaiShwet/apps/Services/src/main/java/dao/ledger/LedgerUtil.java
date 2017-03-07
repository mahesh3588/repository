package dao.ledger;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.SessionUtil;
import dao.exception.DAOException;


public class LedgerUtil {

	
	public Integer add(Ledger ledgerEntry) throws DAOException  {
		Integer id=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(ledgerEntry);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding Ledger details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(Ledger ledgerEntry) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(ledgerEntry);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating Ledger details : "+e.getMessage());
		}
	}
	
	public void delete(Ledger ledgerEntry ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(ledgerEntry);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting Ledger  : "+e.getMessage());
		}
		
	}
	
	public Ledger get(Integer invoiceNumber) throws DAOException{
		Ledger ledgerEntry=null;
		try {
		    ledgerEntry = SessionUtil.getSessionFactory().openSession().get(Ledger.class, invoiceNumber);
		    System.out.println("sss "+ledgerEntry.getSoldItems().size());
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Ledger details : "+e.getMessage());
		}
		return ledgerEntry;
	}
	
	public List<Ledger> get() throws DAOException{
		List<Ledger> ledgerEntryList=null;
		try {
		    Query q=SessionUtil.getSessionFactory().openSession().createQuery("from Ledger");
		    ledgerEntryList=q.list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Ledger details : "+e.getMessage());
		}
		return ledgerEntryList;
	}
	
	
	public List<Ledger> getCustomerLedger(Integer customerId) throws DAOException{
		List<Ledger> ledgerEntryList=null;
		try {
			Criteria ledgerCriteria = SessionUtil.getSessionFactory().openSession().createCriteria(Ledger.class);
			ledgerCriteria.add(Restrictions.eq("customerId", customerId));
			ledgerEntryList=ledgerCriteria.list();
			
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Customer Ledger details : "+e.getMessage());
		}
		return ledgerEntryList;
	}
	
	
}
