package dao;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import dao.exception.DAOException;
import dao.item.Item;
import dao.item.ItemUtil;
import dao.ledger.Ledger;
import dao.ledger.LedgerUtil;
import dao.ledger.SoldItem;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws DAOException
    {
        System.out.println( "Hello World!" );
        
      /*  Item item=new Item();
        item.setName("Item3");
        item.setPurchasePrice(46.23);
        item.setSalePrice(113.213);
        item.setVat(8.0);
        item.setDiscount(5.0);
        
        int id;
		try {
			id = new ItemUtil().add(item);
			System.out.println("Item ID : "+id);
		} catch (DAOException e) {
			e.printStackTrace();
		}*/
        
        
        Ledger ledger=new Ledger();
        //ledger.setInvoiceNumber(1);
        ledger.setBalance(21.7);
        ledger.setBillAmount(25.5);
        ledger.setBillingType("ChckItem");
        ledger.setCustomerId(1);
        ledger.setDate(new Date(2016, 05, 03));
        ledger.setDebit(25.3);
        ledger.setPaidAmount(10.3);
        
        SoldItem soldItem1=new SoldItem();
        soldItem1.setInvoiceDate(new Date(2016, 05, 03));
        //soldItem1.setId(1);
        soldItem1.setItemId(5);
        soldItem1.setItemName("DEf");
        soldItem1.setPrice(25.3);
        soldItem1.setDiscount(10.0);
        
        SoldItem soldItem2=new SoldItem();
       // soldItem2.setId(2);
        soldItem2.setInvoiceDate(new Date(2016, 05, 04));
        soldItem2.setItemId(3);
        soldItem2.setItemName("ABCU");
        soldItem2.setPrice(250.3);
        soldItem2.setDiscount(50.0);
        
        Set<SoldItem> soldItems=new HashSet<SoldItem>();
        soldItems.add(soldItem1);
        soldItems.add(soldItem2);
        
        
        ledger.setSoldItems(soldItems);
       // new LedgerUtil().update(ledger);
        System.out.println(new LedgerUtil().add(ledger));
        
       /* Ledger ledgerEntry=new LedgerUtil().get(1);
        Set<SoldItem> solist=ledgerEntry.getSoldItems();
        System.out.println("size : "+solist.size());
        for(SoldItem so:solist){
        	System.out.println("name : "+so.getItemName() );
        }*/
        
        
    }
}
