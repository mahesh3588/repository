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
import dao.item.stocks.Stock;

public class ItemUtil {

	//private static Session
	
	public Integer add(Item item) throws DAOException  {
		Integer id=null;
		Session session=SessionUtil.getSessionFactory().openSession();
		try {
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(item);
			if(id>0){
				Stock stock=new Stock();
				stock.setAvailableStock(0);
				stock.setItemId(id);
				session.save(stock);
			}
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding Item details : "+e.getMessage());
		}finally{
			session.close();
		}
		return id;
	}
	
	public void update(Item item) throws DAOException  {
		try {
			Item itemExisting=get(item.getId());
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			if(itemExisting!=null &&
					itemExisting.getId()==item.getId()){
				session.update(item);
				
			}else{
				session.save(item);
			}
			transaction.commit();
			session.close();
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
			session.close();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting Item  : "+e.getMessage());
		}
		
	}
	
	public Item get(Integer itemId) throws DAOException{
		Item item=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
		    item = session.get(Item.class, itemId);
		    session.close();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Item details : "+e.getMessage());
		}
		return item;
	}
	
	public List<Item> get() throws DAOException{
		List<Item> itemList=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
		    Query q=session.createQuery("from Item");
		    itemList=q.list();
		    session.close();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Item details : "+e.getMessage());
		}
		return itemList;
	}
}
