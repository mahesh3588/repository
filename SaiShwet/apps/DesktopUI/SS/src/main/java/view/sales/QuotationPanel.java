/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.sales;

import beans.Quotation;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import services.ledger.LedgerClient;

/**
 *
 * @author Owner
 */
public class QuotationPanel extends javax.swing.JPanel {

    /**
     * Creates new form QuotationPanel
     */
    public QuotationPanel() {
        initComponents();
        loadQuotationTable(getQuotations());
    }
    
    private List<Quotation> getQuotations(){
        return  new LedgerClient().getQuotations();
    }

    private void loadQuotationTable(List<Quotation> quoutationList){
        try {
            DefaultTableModel defaultTableModel=(DefaultTableModel)tableQuotations.getModel();
            if(quoutationList.size()>0){
                defaultTableModel.setRowCount(quoutationList.size());
                int i=0;
                for(Quotation quotation:quoutationList){
                    if(quotation!=null){
                        tableQuotations.setValueAt(quotation.getId(), i, 0);
                        tableQuotations.setValueAt(quotation.getDate(), i, 1);
                        tableQuotations.setValueAt(quotation.getCustomerId(), i, 2);
                        tableQuotations.setValueAt(quotation.getQuotationAmount(), i, 3);
                        tableQuotations.setValueAt(quotation.getDescription(), i, 4);
                        i++;
                    }
                }
                repaint();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tableQuotations = new javax.swing.JTable();

        setBackground(new java.awt.Color(204, 204, 255));

        tableQuotations.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Date", "Customer", "Amount", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableQuotations.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableQuotationsKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tableQuotations);
        if (tableQuotations.getColumnModel().getColumnCount() > 0) {
            tableQuotations.getColumnModel().getColumn(0).setPreferredWidth(15);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(133, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tableQuotationsKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableQuotationsKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                Integer qid=(Integer)tableQuotations.getValueAt(tableQuotations.getSelectedRow(), 0);
                JFrame frameBill=new JFrame();
                frameBill.setSize(Toolkit.getDefaultToolkit().getScreenSize());
                frameBill.add(new BillPanel(qid));
                frameBill.setVisible(true);
            } catch (Exception e) {
            }
            
            
        }

    }//GEN-LAST:event_tableQuotationsKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableQuotations;
    // End of variables declaration//GEN-END:variables
}
