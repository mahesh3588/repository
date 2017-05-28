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
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import services.ledger.LedgerClient;

/**
 *
 * @author Owner
 */
public class BillFrame extends javax.swing.JFrame {
    
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
    /**
     * Creates new form BillFrame
     */
    public BillFrame(Integer customerId,Integer invoiceNumber,JFrame containerFrame) {
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
        
        panelBillContainer.setLayout(null);
        
        Double x_billHeader=billHeader.getBounds().getX();
        Double y_billHeader=billHeader.getBounds().getY();
        Double width_billHeader=billHeader.getBounds().getWidth();
        Double height_billHeader=billHeader.getBounds().getHeight();
        
        JPanel panelTable=new JPanel();
        panelTable.setLayout(new BorderLayout());
        JTable table=getLedgerTable();
        Double tableHeight=table.getMaximumSize().getHeight();
        JScrollPane tableScrollPane=new JScrollPane(table);
        tableScrollPane.setBounds(x_billHeader.intValue(), y_billHeader.intValue()+height_billHeader.intValue()+3, width_billHeader.intValue(),tableHeight.intValue()+40);
        //int rowCount=table.getRowCount();
        //System.out.println("rocount : "+rowCount);
        //table.setBounds(1,1,x_billHeader.intValue(), rowCount*10);
        //panelTable.setBounds(x_billHeader.intValue(), y_billHeader.intValue()+height_billHeader.intValue()+3, width_billHeader.intValue(),rowCount*20);
        
        //panelTable.setBackground(Color.BLUE);
        //panelTable.setBorder(new LineBorder(Color.BLACK, 1));
        //panelTable.add(table);
        //panelBillContainer.add(panelTable);
        panelBillContainer.add(tableScrollPane);
        
    }
    
    
    public BillFrame(Integer quotationNumber,JFrame containerFrame) {
        this.containerFrame=containerFrame;
        billType="QUOTATION";
        this.quotationId=quotationNumber;
        initComponents();
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
        System.out.println("Rowss  : "+tableModel.getRowCount());
        
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
        JTable table=new JTable(tableModel);
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
        if(billType.equals("SALE"))
        billHeader = new view.sales.BillPanel(customerId,invoiceNumber,containerFrame);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panelBillContainer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        if(billType.equals("QUOTATION"))
        billHeader = new view.sales.BillPanel(quotationId,containerFrame);

        javax.swing.GroupLayout panelBillContainerLayout = new javax.swing.GroupLayout(panelBillContainer);
        panelBillContainer.setLayout(panelBillContainerLayout);
        panelBillContainerLayout.setHorizontalGroup(
            panelBillContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelBillContainerLayout.setVerticalGroup(
            panelBillContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(billHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(147, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBillContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(panelBillContainer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.sales.BillPanel billHeader;
    private javax.swing.JPanel panelBillContainer;
    // End of variables declaration//GEN-END:variables
}
