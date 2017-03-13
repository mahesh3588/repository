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
import java.awt.Dimension;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.billing.BillingClient;
import services.customer.CustomerClient;
import services.ledger.LedgerClient;
import view.PrintUtil;

/**
 *
 * @author Owner
 */
public class BillPanel extends javax.swing.JPanel {

    private CustomerLedger customerLedger;
    private Customer customerDetails;
    private Ledger ledger,lastLedger;
    private Quotation quotation;
    private Set<QuotationItem> quotationItems;
    private String billType;
    /**
     * Creates new form BillPanel
     */
    public BillPanel() {
        initComponents();
    }

    public BillPanel(Integer customerId,Integer invoiceNumber) {
        initComponents();
        customerLedger=new LedgerClient().getCustomerLedger(customerId);
        customerDetails= customerLedger.getCustomerDetails();
        List<Ledger> ledgerList=customerLedger.getCustomerLedger();
        ledger=ledgerList.stream().filter(t->t.getInvoiceNumber()==invoiceNumber).findFirst().get();
        lastLedger=ledgerList.get(ledgerList.size()-2);
        loadBillItemTable();
        loadCustomerDetails();
        loadLedgerDetails();
    }
    
  
    
    public BillPanel(Integer quotationNumber){
        initComponents();
        try {
            quotation=new BillingClient().getQuotation(quotationNumber);
            customerDetails= (Customer)new CustomerClient().getByID(quotation.getCustomerId());
            loadQuotationItemTable();
            loadCustomerDetails();
            loadQuotationDetails();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
    }

    
    private void loadCustomerDetails(){
        try {
            labelCustomerName.setText(customerDetails.getName());
            labelCustomerEmail.setText(customerDetails.getEmailId());
            labelCustomerMobile.setText(customerDetails.getContact1());
            labelCustomerAddress.setText(customerDetails.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadLedgerDetails(){
        try {
            
            labelInvoiceNo.setText(ledger.getInvoiceNumber().toString());
            labelInvoiceDate.setText(ledger.getDate().toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void loadBillItemTable(){
        try {
            Set<SoldItem> soldItems=ledger.getSoldItems();
            DefaultTableModel defaultTableModel=(DefaultTableModel)tableBillItems.getModel();
            int i=0;
            Dimension tableSize=tableBillItems.getMaximumSize();
            
            for(SoldItem soldItem:soldItems){
                System.out.println(soldItem.getItemName()+"  "+soldItem.getQuantity());
                Double grossTotal=soldItem.getPrice()*soldItem.getQuantity();
                grossTotal=Double.parseDouble(new DecimalFormat(".##").format(grossTotal));
                Object[] rowData={i+1,
                        soldItem.getItemName(),
                        soldItem.getPrice(),
                        soldItem.getQuantity(),
                        grossTotal
                };
                jScrollPane2.setSize(((Double)tableSize.getWidth()).intValue(), ((Double)tableSize.getHeight()).intValue()+5 );
                defaultTableModel.addRow(rowData);
                i++;
            }
            Object[] rowData1={"",
                       "",
                       "",
                       "Bill Amount",
                       ledger.getBillAmount()
               };
            defaultTableModel.addRow(rowData1);
            Object[] rowData2={"",
                      "",
                      "",
                      "Previous Balance",
                      lastLedger.getBalance()
              };
            defaultTableModel.addRow(rowData2);
            Object[] rowData3={"",
                       "",
                       "",
                       "Total Balance",
                       ledger.getBillAmount()+lastLedger.getBalance()
               };
            defaultTableModel.addRow(rowData3);
            Object[] rowData4={"",
                      "",
                      "",
                      "Paid Amount",
                      ledger.getPaidAmount()
              };
            defaultTableModel.addRow(rowData4);
            repaint();
            this.updateUI();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to load bill items");
        }
    }
    
    
    private void loadQuotationItemTable(){
        try {
            Set<QuotationItem> quotationItems=quotation.getQuotationItem();
            DefaultTableModel defaultTableModel=(DefaultTableModel)tableBillItems.getModel();
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
                defaultTableModel.addRow(rowData);
                i++;
            }
             Object[] rowData1={"",
                        "",
                        "",
                        "Bill Amount",
                        quotation.getQuotationAmount()
                };
             defaultTableModel.addRow(rowData1);
              
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to load Quotation items");
        }
    } 
    
    private void loadQuotationDetails(){
        try {
            lableType.setText("Quotation No");
            labelInvoiceNo.setText(quotation.getId().toString());
            labelInvoiceDate.setText(quotation.getDate().toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelCustomer = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        labelCustomerName = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelCustomerAddress = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelCustomerMobile = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        labelCustomerEmail = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        lableType = new javax.swing.JLabel();
        labelInvoiceNo = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        labelInvoiceDate = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBillItems = new javax.swing.JTable();
        buttonPrint = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();

        panelBillContainer.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Saishwet sales corporation and Hardware");

        panelCustomer.setBackground(new java.awt.Color(255, 255, 255));
        panelCustomer.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Customer");

        labelCustomerName.setText("Mahesh Temak");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Address");

        labelCustomerAddress.setText("Karajgaon, Newasa, Ahmednagar");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Mobile");

        labelCustomerMobile.setText("0000000000");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Email");

        labelCustomerEmail.setText("mahesh3599@gmail.com");

        javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
        panelCustomer.setLayout(panelCustomerLayout);
        panelCustomerLayout.setHorizontalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addComponent(labelCustomerName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(22, 22, 22))
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addComponent(labelCustomerAddress, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addComponent(labelCustomerMobile, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCustomerEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        panelCustomerLayout.setVerticalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(labelCustomerName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(labelCustomerAddress))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelCustomerMobile)
                    .addComponent(jLabel8)
                    .addComponent(labelCustomerEmail))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        lableType.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lableType.setText("Invoice no");

        labelInvoiceNo.setText("0000000000");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Date");

        labelInvoiceDate.setText("00/00/0000");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelInvoiceDate, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(lableType, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(labelInvoiceNo, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 2, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lableType)
                    .addComponent(labelInvoiceNo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(labelInvoiceDate))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(176, 176, 176))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(panelCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(panelCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        tableBillItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Sr No", "Item Name", "Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tableBillItems.setSelectionBackground(new java.awt.Color(153, 255, 153));
        jScrollPane2.setViewportView(tableBillItems);
        if (tableBillItems.getColumnModel().getColumnCount() > 0) {
            tableBillItems.getColumnModel().getColumn(0).setPreferredWidth(8);
            tableBillItems.getColumnModel().getColumn(2).setPreferredWidth(9);
            tableBillItems.getColumnModel().getColumn(3).setPreferredWidth(8);
            tableBillItems.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        javax.swing.GroupLayout panelBillContainerLayout = new javax.swing.GroupLayout(panelBillContainer);
        panelBillContainer.setLayout(panelBillContainerLayout);
        panelBillContainerLayout.setHorizontalGroup(
            panelBillContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        panelBillContainerLayout.setVerticalGroup(
            panelBillContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBillContainerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                .addGap(170, 170, 170))
        );

        buttonPrint.setText("Print");
        buttonPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrintActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(buttonPrint)
                .addGap(18, 18, 18)
                .addComponent(buttonCancel)
                .addContainerGap(160, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBillContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelBillContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonPrint)
                    .addComponent(buttonCancel))
                .addGap(0, 153, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrintActionPerformed
        try {
            
           // panelBillContainer.printAll(panelBillContainer.getGraphics());
            PrintUtil.printComponent(panelBillContainer);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonPrintActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        try {
            //this.getParent().fire
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_buttonCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonPrint;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelCustomerAddress;
    private javax.swing.JLabel labelCustomerEmail;
    private javax.swing.JLabel labelCustomerMobile;
    private javax.swing.JLabel labelCustomerName;
    private javax.swing.JLabel labelInvoiceDate;
    private javax.swing.JLabel labelInvoiceNo;
    private javax.swing.JLabel lableType;
    private javax.swing.JPanel panelBillContainer;
    private javax.swing.JPanel panelCustomer;
    private javax.swing.JTable tableBillItems;
    // End of variables declaration//GEN-END:variables
}
