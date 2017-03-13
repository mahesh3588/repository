/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.customer;

import beans.Customer;
import java.awt.Event;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import services.customer.CustomerClient;

/**
 *
 * @author Owner
 */
public class CustomerSearch extends javax.swing.JPanel {
    Customer customer;
    List<Customer> customerList=new ArrayList<Customer>();
    /**
     * Creates new form CustomerSearch
     */
    public CustomerSearch() {
        initComponents();
        try{
            List<Object> list=new CustomerClient().get();
            list.stream().forEach(object->customerList.add((Customer)object));
            String[] customerNames=new String[customerList.size()];
            int i=0;
            for(Customer c:customerList){
                customerNames[i]=c.getName();
                i++;
            }
            listCustomerNames.setListData(customerNames);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.s
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textCustomerName = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        listCustomerNames = new javax.swing.JList<>();
        jLabel1 = new javax.swing.JLabel();
        textCustomerIdSearched = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 102, 102)));

        listCustomerNames.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listCustomerNamesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(listCustomerNames);

        jLabel1.setText("Customer Id");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textCustomerName)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textCustomerIdSearched, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(textCustomerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textCustomerIdSearched, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void listCustomerNamesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listCustomerNamesKeyPressed
        if(evt.getKeyCode()==Event.ENTER){
            //JOptionPane.showMessageDialog(null, "Clicked");
            String customerName= listCustomerNames.getSelectedValue();
            for(Customer c : customerList){
                if(c.getName().equals(customerName)){
                    textCustomerIdSearched.setText(c.getId().toString());
                }
            }
            
        }
    }//GEN-LAST:event_listCustomerNamesKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> listCustomerNames;
    private javax.swing.JTextField textCustomerIdSearched;
    private javax.swing.JTextField textCustomerName;
    // End of variables declaration//GEN-END:variables
}