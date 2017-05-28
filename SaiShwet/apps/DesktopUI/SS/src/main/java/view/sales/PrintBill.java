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
import beans.SoldItem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import services.billing.BillingClient;
import services.customer.CustomerClient;
import services.ledger.LedgerClient;

/**
 *
 * @author Owner
 */
public class PrintBill extends javax.swing.JPanel {
    String billType;
    Integer customerId;
    Integer invoiceNumber;
    Integer quotationId;
    JFrame containerFrame;
    
    private CustomerLedger customerLedger;
    private Customer customerDetails;
    private Ledger ledger,lastLedger;
    private Quotation quotation;
    private Set<QuotationItem> quotationItems;
    private JTable tableBillComponent;
    private JScrollPane panelBillContainerSchroll;
    
    private Integer billPanelHeight=0;
    /**
     * Creates new form PrintBill
     */
    public PrintBill() {
        initComponents();
    }
    public PrintBill(Integer customerId,Integer invoiceNumber,JFrame containerFrame) {
        this.customerId=customerId;
        this.invoiceNumber=invoiceNumber;
        this.containerFrame=containerFrame;
        billType="SALE";
        
        customerLedger=new LedgerClient().getCustomerLedger(customerId);
        customerDetails= customerLedger.getCustomerDetails();
        List<Ledger> ledgerList=customerLedger.getCustomerLedger();
        ledger=ledgerList.stream().filter(t->t.getInvoiceNumber()==invoiceNumber).findFirst().get();
        lastLedger=ledgerList.get(ledgerList.size()-2);
        
        initComponents();
        constructBillPanel("SALE");
        /*panelBillContainer.setLayout(null);
        
        Double x_billHeader=billHeader.getBounds().getX();
        Double y_billHeader=billHeader.getBounds().getY();
        Double width_billHeader=billHeader.getBounds().getWidth();
        Double height_billHeader=billHeader.getBounds().getHeight();
        
        JPanel panelTable=new JPanel();
        panelTable.setLayout(new BorderLayout());
        JTable table=getLedgerTable();
        Double tableHeight=table.getMaximumSize().getHeight();
        JScrollPane tableScrollPane=new JScrollPane(table);
        tableScrollPane.setBounds(x_billHeader.intValue()-1, y_billHeader.intValue()+height_billHeader.intValue(), width_billHeader.intValue()-2,tableHeight.intValue()+40);
        panelBillContainer.add(tableScrollPane);*/
    }
    
    public PrintBill(Integer quotationNumber,JFrame containerFrame){
        this.containerFrame=containerFrame;
        billType="QUOTATION";
        this.quotationId=quotationNumber;
        
        initComponents();
        try {
            quotation=new BillingClient().getQuotation(quotationNumber);
            customerDetails= (Customer)new CustomerClient().getByID(quotation.getCustomerId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        constructBillPanel("QUOTATION");
        /*
        panelBillContainer.setLayout(null);
        
        Double x_billHeader=billHeader.getBounds().getX();
        Double y_billHeader=billHeader.getBounds().getY();
        Double width_billHeader=billHeader.getBounds().getWidth();
        Double height_billHeader=billHeader.getBounds().getHeight();
        
        JPanel panelTable=new JPanel();
        panelTable.setLayout(new BorderLayout());
        JTable table=getQuotationTable();
        Double tableHeight=table.getMaximumSize().getHeight();
        JScrollPane tableScrollPane=new JScrollPane(table);
        tableScrollPane.setBounds(x_billHeader.intValue()-1, y_billHeader.intValue()+height_billHeader.intValue(), width_billHeader.intValue()-2,tableHeight.intValue()+40);
        panelBillContainer.add(tableScrollPane);
       */
    }

    public Integer getBillPanelHeight() {
        return billPanelHeight;
    }

    public JPanel getBillPanel(){
        return panelBillContainer;
    }
    
    private void constructBillPanel(String billType ){
        panelBillContainer.setLayout(null);
        panelBillContainer.setBackground(Color.WHITE);
        
        Double x_billHeader=billHeader.getBounds().getX();
        Double y_billHeader=billHeader.getBounds().getY();
        Double width_billHeader=billHeader.getBounds().getWidth();
        Double height_billHeader=billHeader.getBounds().getHeight();
        
        JPanel panelTable=new JPanel();
        panelTable.setLayout(new BorderLayout());
       
        JTable table=null;
        if(billType.equals("SALE"))
            table=getLedgerTable();
        else if(billType.equals("QUOTATION"))
            table=getQuotationTable();
        
        table.setEnabled(false);
        Double tableHeight=table.getMaximumSize().getHeight();
        JScrollPane tableScrollPane=new JScrollPane(table);
        tableScrollPane.setBounds(x_billHeader.intValue()-1, y_billHeader.intValue()+height_billHeader.intValue(), width_billHeader.intValue()-2,tableHeight.intValue()+30);
        billPanelHeight=tableHeight.intValue()+30+155;
        panelBillContainer.add(tableScrollPane);
    }
    
    
    
    private JTable getLedgerTable(){
        Set<SoldItem> soldItems=ledger.getSoldItems();
        Object[] columnNames={
                                "SrNo",
                                "Item Name",
                                "Price",
                                "Quantity",
                                "Total"
                             };
        DefaultTableModel tableModel=new DefaultTableModel(columnNames, soldItems.size()+2);
        tableModel.setRowCount(0);
        
        
        int i=1;
        for(SoldItem soldItem:soldItems){
            
            Double grossTotal=soldItem.getPrice()*soldItem.getQuantity();
            grossTotal=Double.parseDouble(new DecimalFormat(".##").format(grossTotal));
            Object[] rowData={
                                i,
                                soldItem.getItemName(),
                                soldItem.getPrice(),
                                soldItem.getQuantity(),
                                grossTotal
                             };
            tableModel.addRow(rowData);
            i++;
        }
        Object[] rowData1={"",
                   "",
                   "",
                   "Bill Amount",
                   ledger.getBillAmount()
           };
        tableModel.addRow(rowData1);
        Object[] rowData2={"",
                  "",
                  "",
                  "Previous Balance",
                  lastLedger.getBalance()
          };
        tableModel.addRow(rowData2);
        Object[] rowData3={"",
                   "",
                   "",
                   "Total Balance",
                   ledger.getBillAmount()+lastLedger.getBalance()
           };
        tableModel.addRow(rowData3);
        Object[] rowData4={"",
                  "",
                  "",
                  "Paid Amount",
                  ledger.getPaidAmount()
          };
        tableModel.addRow(rowData4);
        
        JTable table=new JTable(tableModel);
        table.setBackground(Color.WHITE);
        table.setBorder(new LineBorder(Color.BLACK));
        return table;
    }
    
    private JTable getQuotationTable(){
    
        Set<QuotationItem> quotationItems=quotation.getQuotationItem();
        Object[] columnNames={
                            "SrNo",
                            "Item Name",
                            "Price",
                            "Quantity",
                            "Total"
                         };
        DefaultTableModel tableModel=new DefaultTableModel(columnNames, quotationItems.size()+2);
        tableModel.setRowCount(0);

        int i=0;

        for(QuotationItem quotationItem:quotationItems){
            Double grossTotal=quotationItem.getPrice()*quotationItem.getQuantity();
            grossTotal=Double.parseDouble(new DecimalFormat(".##").format(grossTotal));
            Object[] rowData={i+1,
                    quotationItem.getItemName(),
                    quotationItem.getPrice(),
                    quotationItem.getQuantity(),
                    grossTotal
            };
            tableModel.addRow(rowData);
            i++;
        }
         Object[] rowData1={"",
                    "",
                    "",
                    "Bill Amount",
                    quotation.getQuotationAmount()
            };
         tableModel.addRow(rowData1);
        
         JTable table=new JTable(tableModel);
         table.setBackground(Color.WHITE);
        return table;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBillContainer = new javax.swing.JPanel();
        billHeader = new view.sales.BillPanel();

        setLayout(new java.awt.BorderLayout());

        panelBillContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelBillContainer.setLayout(null);

        if(billType.equals("SALE"))
        billHeader = new view.sales.BillPanel(customerId,invoiceNumber,containerFrame);
        if(billType.equals("QUOTATION"))
        billHeader = new view.sales.BillPanel(quotationId,containerFrame);
        panelBillContainer.add(billHeader);
        billHeader.setBounds(20, 10, 590, 140);

        add(panelBillContainer, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.sales.BillPanel billHeader;
    private javax.swing.JPanel panelBillContainer;
    // End of variables declaration//GEN-END:variables
}
