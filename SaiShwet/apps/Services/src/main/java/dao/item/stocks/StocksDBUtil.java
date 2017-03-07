package dao.item.stocks;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import dao.SessionUtil;
import dao.exception.DAOException;

public class StocksDBUtil {


	public Integer add(Stock stock) throws DAOException  {
		Integer id=null;
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			id =(Integer) session.save(stock);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding stock details : "+e.getMessage());
		}
		return id;
	}
	
	public void update(Stock stock) throws DAOException  {
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(stock);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating stock details : "+e.getMessage());
		}
	}
	
	public void delete(Stock stock ) throws DAOException{
		
		try {
			Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(stock);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while deleting stock  : "+e.getMessage());
		}
		
	}
	
	public Stock get(Integer itemId) throws DAOException{
		Stock stock=null;
		try {
			Criteria stockCriteria=SessionUtil.getSessionFactory().openSession().createCriteria(Stock.class);
			stockCriteria.add(Restrictions.eq("itemId", itemId));
			List<Stock> stockList=stockCriteria.list();
			if(stockList.size()>0){
				stock=stockList.get(0);
			}
		    
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Stock details : "+e.getMessage());
		}
		return stock;
	}
	
	public List<Stock> get() throws DAOException{
		List<Stock> stockList=null;
		try {
			Criteria stockCriteria=SessionUtil.getSessionFactory().openSession().createCriteria(Stock.class);
			stockList=stockCriteria.list();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while getting Stock details : "+e.getMessage());
		}
		return stockList;
	}

}
