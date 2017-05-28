/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sales;

import beans.Customer;
import beans.CustomerLedger;
import beans.Ledger;
import beans.Quotation;
import beans.QuotationItem;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import services.billing.BillingClient;
import services.customer.CustomerClient;
import services.ledger.LedgerClient;

/**
 *
 * @author Owner
 */
public class BillQuotationPrintPanel extends JPanel {
    private CustomerLedger customerLedger;
    private Customer customerDetails;
    private Ledger ledger,lastLedger;
    private Quotation quotation;
    private Set<QuotationItem> quotationItems;
    private String billType;
    private JFrame containerFrame;
    private Dimension tableBillItemsDimension;
    
    private BillPanel headerPanel;
    /**
     * Creates new form BillPanel
     */
    public BillQuotationPrintPanel() {
    }

    public BillQuotationPrintPanel(Integer customerId,Integer invoiceNumber,JFrame containerFrame) {
        customerLedger=new LedgerClient().getCustomerLedger(customerId);
        customerDetails= customerLedger.getCustomerDetails();
        List<Ledger> ledgerList=customerLedger.getCustomerLedger();
        ledger=ledgerList.stream().filter(t->t.getInvoiceNumber()==invoiceNumber).findFirst().get();
        lastLedger=ledgerList.get(ledgerList.size()-2);
        
        Integer height=0,width=465;
        height=ledger.getSoldItems().size()*10;
        tableBillItemsDimension=new Dimension(width, height);
        
        this.containerFrame=containerFrame;
        
        headerPanel=new BillPanel(customerId, invoiceNumber, containerFrame);
        
        setup();
    }
    
    public BillQuotationPrintPanel(Integer quotationNumber,JFrame containerFrame){
        this.containerFrame=containerFrame;
        try {
            quotation=new BillingClient().getQuotation(quotationNumber);
            customerDetails= (Customer)new CustomerClient().getByID(quotation.getCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        headerPanel=new BillPanel(quotationNumber, containerFrame);
        
        setup();
    }

    private void setup(){
        setLayout(null);
        setSize(500,500);
        
        headerPanel.setLocation(0, 0);
        add(headerPanel);
    }
    
}
