/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.vendor;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Owner
 */
public class VendorPanel extends JPanel {

    /**
     * Creates new form CustomerPanel
     */
    public VendorPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        menuPanel2 = new javax.swing.JPanel();
        buttonAdd2 = new javax.swing.JButton();
        buttonView2 = new javax.swing.JButton();
        buttonModify2 = new javax.swing.JButton();
        buttonDelete2 = new javax.swing.JButton();
        buttonAddBiils = new javax.swing.JButton();
        bodyPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jPanel3.setBackground(new java.awt.Color(102, 255, 255));

        menuPanel2.setBackground(new java.awt.Color(51, 153, 255));

        buttonAdd2.setText("Add");
        buttonAdd2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonView2.setText("View");
        buttonView2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });

        buttonModify2.setText("Modify");
        buttonModify2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModifyActionPerformed(evt);
            }
        });

        buttonDelete2.setText("Delete");

        buttonAddBiils.setText("Add Bills");
        buttonAddBiils.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddBiilsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout menuPanel2Layout = new javax.swing.GroupLayout(menuPanel2);
        menuPanel2.setLayout(menuPanel2Layout);
        menuPanel2Layout.setHorizontalGroup(
            menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonAddBiils, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDelete2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonModify2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonView2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonAdd2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        menuPanel2Layout.setVerticalGroup(
            menuPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(buttonAdd2)
                .addGap(18, 18, 18)
                .addComponent(buttonView2)
                .addGap(18, 18, 18)
                .addComponent(buttonModify2)
                .addGap(18, 18, 18)
                .addComponent(buttonDelete2)
                .addGap(18, 18, 18)
                .addComponent(buttonAddBiils)
                .addContainerGap(69, Short.MAX_VALUE))
        );

        bodyPanel.setBackground(new java.awt.Color(204, 204, 255));
        bodyPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel3, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed

        bodyPanel.removeAll();
        revalidate();
        repaint();
        JPanel panelInner = new JPanel();
        panelInner.setBackground(Color.BLUE);
        panelInner.setSize(bodyPanel.getSize());
        panelInner.add(new AddVendorPanel(),BorderLayout.CENTER);
        bodyPanel.add(panelInner, BorderLayout.CENTER);
        revalidate();
        repaint();

    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        JPanel containerPanale=new JPanel();
        containerPanale.setBackground(Color.BLUE);
        containerPanale.setSize(bodyPanel.getSize());
        JPanel viewCustomerPanel=new ViewVendorPanel();
        viewCustomerPanel.setSize(containerPanale.getSize());
        containerPanale.add(viewCustomerPanel, BorderLayout.CENTER);
        bodyPanel.removeAll();
        bodyPanel.add(containerPanale,BorderLayout.CENTER);
        repaint();
        revalidate();

    }//GEN-LAST:event_buttonViewActionPerformed

    private void buttonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModifyActionPerformed
//        String customerId=JOptionPane.showInputDialog("Please Enter Code/Id of customer to update").toString();
//        JPanel containerPanale=new JPanel();
//        containerPanale.setBackground(Color.BLUE);
//        containerPanale.setSize(bodyPanel.getSize());
////        JPanel modifyCustomerPanel=new ModifyCustomerPanel(Integer.parseInt(customerId));
////        modifyCustomerPanel.setSize(containerPanale.getSize());
////        containerPanale.add(modifyCustomerPanel, BorderLayout.CENTER);
//        bodyPanel.removeAll();
//        bodyPanel.add(containerPanale,BorderLayout.CENTER);
//        repaint();
//        revalidate();
    }//GEN-LAST:event_buttonModifyActionPerformed

    private void buttonAddBiilsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddBiilsActionPerformed
        JPanel containerPanale=new JPanel();
        containerPanale.setBackground(Color.BLUE);
        containerPanale.setSize(bodyPanel.getSize());
        containerPanale.setLayout(new BorderLayout());
        bodyPanel.setLayout(new BorderLayout());
        
        JPanel addVendorBills=new AddVendorBills();
        addVendorBills.setSize(containerPanale.getSize());
        containerPanale.add(addVendorBills, BorderLayout.CENTER);
        bodyPanel.removeAll();
        bodyPanel.add(containerPanale);
        repaint();
        revalidate();
    }//GEN-LAST:event_buttonAddBiilsActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton buttonAdd2;
    private javax.swing.JButton buttonAddBiils;
    private javax.swing.JButton buttonDelete2;
    private javax.swing.JButton buttonModify2;
    private javax.swing.JButton buttonView2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel menuPanel2;
    // End of variables declaration//GEN-END:variables
}
