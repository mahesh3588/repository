package dao.item;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;

import dao.SessionUtil;
import dao.exception.DAOException;

public class ItemUtil {

	//private static Session
	
	public Integer add(Item item) throws DAOException  {
		Integer id=null;
		System.out.println("ItemUtil add()");
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(item);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding Item details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(Item item) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(item);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating Item details : "+e.getMessage());
		}
	}
	
	public void delete(Item item ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(item);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting Item  : "+e.getMessage());
		}
		
	}
	
	public Item get(Integer itemId) throws DAOException{
		Item item=null;
		try {
			/*Criteria byIdCriteria=SessionUtil.getSessionFactory().openSession().createCriteria(Item.class);
			byIdCriteria.add(null);
			Criterion c=byIdCriteria.c*/
			
		    item = SessionUtil.getSessionFactory().openSession().get(Item.class, itemId);
		    
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Item details : "+e.getMessage());
		}
		return item;
	}
	
	public List<Item> get() throws DAOException{
		List<Item> itemList=null;
		try {
		    Query q=SessionUtil.getSessionFactory().openSession().createQuery("from Item");
		    itemList=q.list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Item details : "+e.getMessage());
		}
		return itemList;
	}
}
