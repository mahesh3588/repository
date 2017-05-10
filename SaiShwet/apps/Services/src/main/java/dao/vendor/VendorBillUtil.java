package dao.vendor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SessionUtil;
import dao.exception.DAOException;
import dao.item.Item;
import dao.item.ItemUtil;
import dao.item.stocks.Stock;
import dao.item.stocks.StocksDBUtil;

public class VendorBillUtil {

	//private static Session
	
	public Long add(VendorBill vendorBill) throws DAOException  {
		Long id=null;
		final Session session=SessionUtil.getSessionFactory().openSession();
		try {
			//session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			vendorBill.getBillItemList().forEach(v->{
				v.setId(null);
				v.setVendorBill(vendorBill);
			});
			
			
			List<Item> itemList=new ItemUtil().get();
			List<Item> itemListToUpdate=vendorBill.getBillItemList().stream().map(vi->{
				Item item=itemList.stream().filter(itm->itm.getName().equalsIgnoreCase(vi.getName())).findFirst().get();
				item.setPurchasePrice(vi.getPurchasePrice());
				item.setSalePrice(vi.getSalePrice());
				item.setVat(vi.getVat());
				item.setDiscount(vi.getDiscount());
				return item;
			}).collect(Collectors.toList());
			
			List<Stock> stocksList=new StocksDBUtil().get();
			List<Stock> itemStockToUpdate=vendorBill.getBillItemList().stream().map(vi->{
				Item item=itemList.stream().filter(itm->itm.getName().equalsIgnoreCase(vi.getName())).findFirst().get();
				Stock stock=stocksList.stream().filter(stk->stk.getItemId()==item.getId()).findFirst().get();
				stock.setAvailableStock(stock.getAvailableStock()+vi.getQuantity());
				return stock;
			}).collect(Collectors.toList());
			
			id =(Long) session.save(vendorBill);
			itemListToUpdate.forEach(itm->session.update(itm));
			itemStockToUpdate.forEach(stk->session.update(stk));
			
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while adding VendorBill details : "+e.getMessage());
		}finally{
			if(session!=null)
				session.close();
		}
		return id;
	}
	
	public void update(VendorBill vendorBill) throws DAOException  {
		Session session=null;
		try {
			session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			session.update(vendorBill);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DAOException("Exception occurred while updating VendorBill details : "+e.getMessage());
		}finally{
			if(session!=null)
				session.close();
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
	
	public List<VendorBill> getVendorBills(Integer vendorId) throws DAOException{
		List<VendorBill> vendorBills=null;
		vendorBills=get().stream().filter(v->v.getVendorId()==vendorId).collect(Collectors.toList());
		return vendorBills;
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
