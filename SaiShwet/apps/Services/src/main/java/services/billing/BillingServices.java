package services.billing;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;

import dao.SessionUtil;
import dao.customer.Customer;
import dao.customer.CustomerUtil;
import dao.exception.DAOException;
import dao.item.Item;
import dao.item.ItemUtil;
import dao.item.stocks.Stock;
import dao.item.stocks.StocksDBUtil;
import dao.ledger.Ledger;
import dao.ledger.LedgerUtil;
import dao.ledger.Order;
import dao.ledger.SoldItem;
import dao.ledger.quotation.Quotation;
import dao.ledger.quotation.QuotationItem;
import dao.ledger.quotation.QuotationUtil;

@Path("/billingService")
public class BillingServices {
	
    @POST
	@Path("/placeOrder")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
	public Response placeOrder(Order order){
    	System.out.println("Place Ordr");
    	System.out.println(order.getBillAmount()+"  "+order.getBillDate());
    	int status=500;
    	Integer invoice=null;
    	
    	try {
			 Map<Integer, Integer> billingItems=order.getBillingItems();
			 Set<Integer> billItems= billingItems.keySet();
			 
			 Set<SoldItem> soldItems=new HashSet<SoldItem>();
			 Iterator<Integer> iterator=billItems.iterator();
			 while(iterator.hasNext()){
				 Integer itemId=iterator.next();
				 Item item=new ItemUtil().get(itemId);
				 SoldItem soldItem=new SoldItem();
				 soldItem.setDiscount(item.getDiscount());
				 soldItem.setInvoiceDate(order.getBillDate());
				 soldItem.setItemId(item.getId());
				 soldItem.setItemName(item.getName());
				 soldItem.setPrice(item.getSalePrice());
				 soldItem.setQuantity(billingItems.get(itemId));
				 soldItem.setVat(item.getVat());
				 
				 soldItems.add(soldItem);
			 }
			 
			 Ledger ledgerEntry=new Ledger();
			 ledgerEntry.setBillAmount(order.getBillAmount());
			 ledgerEntry.setBalance(order.getLedgerBalance()+order.getBillAmount()-order.getPaidAmount());
			 ledgerEntry.setCustomerId(order.getCustomerId());
			 ledgerEntry.setDate(order.getBillDate());
			 ledgerEntry.setPaidAmount(order.getPaidAmount());
			 ledgerEntry.setSoldItems(soldItems);
			 
			 invoice= new LedgerUtil().add(ledgerEntry);
			 //System.out.println("Invoice No : "+invoice);
			 if(invoice!=null){
				 status=200;
				 StocksDBUtil stockUtil=new StocksDBUtil();
				 Set<Integer> billItemsID= billingItems.keySet();
				 for(Integer itemId:billItemsID){
					 Stock itemStock=stockUtil.get(itemId);
					 itemStock.setAvailableStock(itemStock.getAvailableStock()-billingItems.get(itemId));
					 stockUtil.update(itemStock);
				 }
			 }
		} catch (Exception e) {
			status=500;
			e.printStackTrace();
		}
    	return Response.status(status).entity(invoice).build();
    }
	

    @POST
    @Path("/placeQuotation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response placeQuoatation(Order order){
    	int quotationId=0;
    	int status=500;
    	try {
    		Quotation quotation=new Quotation();
    		quotation.setCustomerId(order.getCustomerId());
    		quotation.setDate(order.getBillDate());
    		quotation.setDescription("");
    		quotation.setQuotationAmount(order.getBillAmount());
    		Map<Integer,Integer> billingItems =order.getBillingItems();
    		Set<Integer> billItems= billingItems.keySet();
			 
			Set<QuotationItem> quotationItems=new HashSet<QuotationItem>();
			Iterator<Integer> iterator=billItems.iterator();
			while(iterator.hasNext()){
				Integer itemId=iterator.next();
				Item item=new ItemUtil().get(itemId);
				QuotationItem quotationItem=new QuotationItem();
				quotationItem.setQuotationDate(order.getBillDate());
				quotationItem.setDiscount(item.getDiscount());
				quotationItem.setItemId(item.getId());
				quotationItem.setItemName(item.getName());
				quotationItem.setPrice(item.getSalePrice());
				quotationItem.setQuantity(billingItems.get(itemId));
				quotationItem.setVat(item.getVat());
				 
				quotationItems.add(quotationItem);
			 }
			quotation.setQuotationItem(quotationItems);
    		quotationId = new QuotationUtil().add(quotation);
    		if(quotationId>0)
    			status=200;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return Response.status(status).entity(quotationId).build();
    }
    
    
    @GET
    @Path("/getQuotation")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getQuotation(@QueryParam("quotationId") String id){
    	int status = 500;
    	System.out.println("QID == "+id);
		Quotation quotation=null;
		try {
			quotation=new QuotationUtil().get(Integer.parseInt(id));
			System.out.println("qid  = "+quotation.getId()+"   amount  "+quotation.getQuotationAmount());
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(quotation).build();
    }
    
    
}
