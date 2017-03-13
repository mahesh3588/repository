/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sales;

import beans.Customer;
import beans.CustomerLedger;
import beans.Ledger;
import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import services.ledger.LedgerClient;

/**
 *
 * @author Owner
 */
public class LedgerQuotationPanel extends javax.swing.JPanel {
    CustomerLedger customerLedgerDetails;
    QuotationPanel quotationPanel;
    
    JPopupMenu popupBill;
    JPanel popupPanel;
    
    /**
     * Creates new form LedgerQuotationPanel
     */
    public LedgerQuotationPanel() {
        initComponents();
        popupBill=new JPopupMenu();
        popupPanel=new JPanel();
        popupBill.add(popupPanel);
        tableLedger.addMouseListener(new MousePopupListener());
    }

    private void loadCustomerDetail() {
        try {
            if (!textCustomerId.getText().equalsIgnoreCase("")) {
                Integer customerId = Integer.parseInt(textCustomerId.getText());
                customerLedgerDetails = new LedgerClient().getCustomerLedger(customerId);

                if (customerLedgerDetails != null) {
                    Customer customer = customerLedgerDetails.getCustomerDetails();
                    List<Ledger> customerLedger = customerLedgerDetails.getCustomerLedger();

                    textCustomerName.setText(customer.getName());
                    textContact1.setText(customer.getContact1());
                    textContact2.setText(customer.getContact2());
                    textAddress.setText(customer.getAddress());

                    if (customerLedger.size() > 0) {
                        Ledger ledgerEntry = customerLedger.get(customerLedger.size() - 1);
                        if (ledgerEntry != null) {
                            textBalance.setText(ledgerEntry.getBalance().toString());
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to load balance of customer : " + customerId);
                        }
                    }
                    loadLedgerTable(customerLedgerDetails.getCustomerLedger());
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to load detail of customer : " + customerId);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to load detail ");
        }
    }

    
    private void loadLedgerTable(List<Ledger> ledger){
        try {
            DefaultTableModel defaultTableModel =(DefaultTableModel) tableLedger.getModel();
            defaultTableModel.setRowCount(ledger.size());
            tableLedger.setModel(defaultTableModel);
            int row =0;
            Ledger lastEntry=null;
            for(Ledger ledgerEntry:ledger){
                tableLedger.setValueAt(ledgerEntry.getInvoiceNumber(), row, 0);
                tableLedger.setValueAt(ledgerEntry.getDate(), row, 1);
                tableLedger.setValueAt(ledgerEntry.getCustomerId(), row, 2);
                tableLedger.setValueAt(ledgerEntry.getBillAmount(), row, 3);
                tableLedger.setValueAt(ledgerEntry.getPaidAmount(), row, 4);
                if(lastEntry==null){
                    tableLedger.setValueAt(ledgerEntry.getBalance(), row, 5);
                }else{
                    tableLedger.setValueAt(lastEntry.getBalance()+ledgerEntry.getBillAmount(), row, 5);
                }
                tableLedger.setValueAt(ledgerEntry.getBalance(), row, 6);
                lastEntry=ledgerEntry;
                row++;
            }
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

        jPanel1 = new javax.swing.JPanel();
        menuPanel = new javax.swing.JPanel();
        bodyPanel = new javax.swing.JPanel();
        panelCustomer = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textCustomerId = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        textCustomerName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        textContact1 = new javax.swing.JTextField();
        textContact2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textAddress = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textBalance = new javax.swing.JTextField();
        comboViewType = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableLedger = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(102, 255, 255));

        menuPanel.setBackground(new java.awt.Color(51, 153, 255));

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 89, Short.MAX_VALUE)
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        bodyPanel.setBackground(new java.awt.Color(204, 204, 255));

        panelCustomer.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer"));

        jLabel1.setText("Customer Id");

        textCustomerId.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textCustomerIdFocusLost(evt);
            }
        });
        textCustomerId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textCustomerIdKeyPressed(evt);
            }
        });

        jLabel2.setText("Customer Name");

        textCustomerName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textCustomerNameActionPerformed(evt);
            }
        });

        jButton1.setText("Search Customer");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel3.setText("Contact ");

        jLabel4.setText("Address");

        jLabel5.setText("Ledger Balance");

        textBalance.setEditable(false);
        textBalance.setBackground(new java.awt.Color(255, 255, 255));
        textBalance.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        textBalance.setForeground(new java.awt.Color(255, 0, 0));

        comboViewType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ledger", "Quoatations" }));
        comboViewType.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboViewTypeItemStateChanged(evt);
            }
        });
        comboViewType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboViewTypeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelCustomerLayout = new javax.swing.GroupLayout(panelCustomer);
        panelCustomer.setLayout(panelCustomerLayout);
        panelCustomerLayout.setHorizontalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelCustomerLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(172, 172, 172)
                                .addComponent(jLabel3))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(124, 124, 124)
                        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)))
                    .addComponent(comboViewType, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(panelCustomerLayout.createSequentialGroup()
                        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(panelCustomerLayout.createSequentialGroup()
                                .addComponent(textCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton1)
                                .addGap(47, 47, 47)
                                .addComponent(textContact1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelCustomerLayout.createSequentialGroup()
                                .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(textContact2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(34, 34, 34)
                        .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textBalance, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                            .addComponent(textAddress))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelCustomerLayout.setVerticalGroup(
            panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelCustomerLayout.createSequentialGroup()
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textCustomerId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1)
                    .addComponent(textContact1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textAddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelCustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textBalance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textContact2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboViewType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel6.setText("Ledger");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Ledger"));
        jPanel2.setLayout(new java.awt.BorderLayout());

        tableLedger.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Invoice", "Date", "Customer Name", "Bill Amount", "Paid Amount", "Total Balance", "Closing Balance"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableLedger.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableLedgerKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableLedger);
        if (tableLedger.getColumnModel().getColumnCount() > 0) {
            tableLedger.getColumnModel().getColumn(0).setPreferredWidth(7);
            tableLedger.getColumnModel().getColumn(1).setPreferredWidth(10);
            tableLedger.getColumnModel().getColumn(2).setPreferredWidth(20);
            tableLedger.getColumnModel().getColumn(3).setPreferredWidth(10);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addGap(340, 340, 340)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 790, Short.MAX_VALUE)
                    .addComponent(panelCustomer, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void textCustomerIdFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textCustomerIdFocusLost
        loadCustomerDetail();
    }//GEN-LAST:event_textCustomerIdFocusLost

    private void textCustomerIdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textCustomerIdKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadCustomerDetail();
        }
    }//GEN-LAST:event_textCustomerIdKeyPressed

    private void textCustomerNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textCustomerNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textCustomerNameActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void comboViewTypeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboViewTypeItemStateChanged
        //JOptionPane.showMessageDialog(null  , comboViewType.getSelectedItem().toString());
    }//GEN-LAST:event_comboViewTypeItemStateChanged

    private void comboViewTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboViewTypeActionPerformed
        if (comboViewType.getSelectedItem().toString().equalsIgnoreCase("Quoatations")) {
            jPanel2.remove(jScrollPane1);
            jPanel2.setLayout(new BorderLayout());
            quotationPanel = new QuotationPanel();
            jPanel2.add(quotationPanel);
            repaint();
            revalidate();
        } else if (comboViewType.getSelectedItem().toString().equalsIgnoreCase("Ledger")) {
            if (!textCustomerId.getText().equalsIgnoreCase("")) {
                jPanel2.setLayout(new BorderLayout());
                jPanel2.removeAll();
                jPanel2.add(jScrollPane1);
                tableLedger.setVisible(true);
                loadCustomerDetail();
                repaint();
                revalidate();
            } else {
                JOptionPane.showMessageDialog(null, "Please enter customer ID ");
            }
        }
    }//GEN-LAST:event_comboViewTypeActionPerformed

    private void tableLedgerKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableLedgerKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            Integer customerId=0;
            if(!textCustomerId.getText().equalsIgnoreCase("")){
               customerId=Integer.parseInt(textCustomerId.getText());
               int selectedRow=tableLedger.getSelectedRow();
               System.out.println("row = "+selectedRow);
               Integer invcNo=(Integer)tableLedger.getValueAt(selectedRow, 0);
               System.out.println("invc = "+invcNo);
               BillPanel billS=new BillPanel(customerId, invcNo);
               JFrame bill = new JFrame();
               bill.setSize(Toolkit.getDefaultToolkit().getScreenSize());
               bill.add(billS);
               bill.setVisible(true);
            }
        }
        
    }//GEN-LAST:event_tableLedgerKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JComboBox<String> comboViewType;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel menuPanel;
    private javax.swing.JPanel panelCustomer;
    private javax.swing.JTable tableLedger;
    private javax.swing.JTextField textAddress;
    private javax.swing.JTextField textBalance;
    private javax.swing.JTextField textContact1;
    private javax.swing.JTextField textContact2;
    private javax.swing.JTextField textCustomerId;
    private javax.swing.JTextField textCustomerName;
    // End of variables declaration//GEN-END:variables


    
   class MousePopupListener extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      checkPopup(e);
    }

    public void mouseClicked(MouseEvent e) {
      checkPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
      checkPopup(e);
    }

    private void checkPopup(MouseEvent e) {
      if (e.isPopupTrigger()) {
        popupBill.show(LedgerQuotationPanel.this, e.getX(), e.getY());
      }
    }
  }  
    
}