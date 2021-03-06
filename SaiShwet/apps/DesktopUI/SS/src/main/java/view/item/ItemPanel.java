/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.item;



import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Owner
 */
public class ItemPanel extends JPanel {
    
    /**
     * Creates new form ItemPanel
     */
    public ItemPanel() {
          
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

        menuPanel = new javax.swing.JPanel();
        buttonAdd = new javax.swing.JButton();
        buttonView = new javax.swing.JButton();
        buttonModify = new javax.swing.JButton();
        buttonDelete = new javax.swing.JButton();
        bodyPanel = new javax.swing.JPanel();

        setBackground(new java.awt.Color(102, 255, 255));

        menuPanel.setBackground(new java.awt.Color(51, 153, 255));

        buttonAdd.setText("Add");
        buttonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddActionPerformed(evt);
            }
        });

        buttonView.setText("View");
        buttonView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonViewActionPerformed(evt);
            }
        });

        buttonModify.setText("Modify");
        buttonModify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModifyActionPerformed(evt);
            }
        });

        buttonDelete.setText("Delete");

        javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
        menuPanel.setLayout(menuPanelLayout);
        menuPanelLayout.setHorizontalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(buttonAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonModify, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonView, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(14, Short.MAX_VALUE))
        );
        menuPanelLayout.setVerticalGroup(
            menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(buttonAdd)
                .addGap(18, 18, 18)
                .addComponent(buttonView)
                .addGap(18, 18, 18)
                .addComponent(buttonModify)
                .addGap(18, 18, 18)
                .addComponent(buttonDelete)
                .addContainerGap(135, Short.MAX_VALUE))
        );

        bodyPanel.setBackground(new java.awt.Color(204, 204, 255));
        bodyPanel.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void buttonModifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModifyActionPerformed
        String itemId=JOptionPane.showInputDialog("Please Enter Code/Id of item to update").toString();
        JPanel containerPanale=new JPanel();
        containerPanale.setBackground(Color.BLUE);
        containerPanale.setSize(bodyPanel.getSize());
        JPanel modifyItemPanel=new ModifyItemPanel(Integer.parseInt(itemId));
        modifyItemPanel.setSize(containerPanale.getSize());
        containerPanale.add(modifyItemPanel, BorderLayout.CENTER);
        bodyPanel.removeAll();
        bodyPanel.add(containerPanale,BorderLayout.CENTER);
        repaint();
        revalidate();
    }//GEN-LAST:event_buttonModifyActionPerformed

    private void buttonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddActionPerformed

        bodyPanel.removeAll();
        revalidate();
        repaint();
        JPanel panelInner = new JPanel();
        //panelInner.setBackground(Color.BLUE);
        panelInner.setSize(bodyPanel.getSize());
       // panelInner.setLayout(new BorderLayout());
        AddItemPanel addItemPanel=new AddItemPanel();
        addItemPanel.setSize(panelInner.getSize());
        panelInner.add(addItemPanel,BorderLayout.CENTER);
        bodyPanel.add(panelInner, BorderLayout.CENTER);
        revalidate();
        repaint();

        
    }//GEN-LAST:event_buttonAddActionPerformed

    private void buttonViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonViewActionPerformed
        
        //containerPanale.setBackground(Color.BLUE);
        ViewItemPanel viewItemPanel=new ViewItemPanel();
        viewItemPanel.setSize(bodyPanel.getSize());
        bodyPanel.removeAll();
        bodyPanel.setLayout(new BorderLayout());
        bodyPanel.add(viewItemPanel);
        repaint();
        revalidate();
           
    }//GEN-LAST:event_buttonViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JButton buttonAdd;
    private javax.swing.JButton buttonDelete;
    private javax.swing.JButton buttonModify;
    private javax.swing.JButton buttonView;
    private javax.swing.JPanel menuPanel;
    // End of variables declaration//GEN-END:variables
}
