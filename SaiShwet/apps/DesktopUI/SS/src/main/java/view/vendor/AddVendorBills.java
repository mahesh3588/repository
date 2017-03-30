/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.vendor;

import beans.Item;
import beans.Vendor;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.stream.Collectors;
import javafx.scene.input.KeyCode;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import services.item.ItemClient;
import services.vendor.VendorClient;

/**
 *
 * @author Owner
 */
public class AddVendorBills extends javax.swing.JPanel {

    List<Vendor> vendorList;
    List<Item>  itemList;
    
    public AddVendorBills() {
        initComponents();
        setUp();
    }
    
    private void setUp(){
        try {
            comboVendorName.removeAllItems();
            comboVendorName.addItem("Select Vendor");
            listItemNames.removeAll();
            List<Object> list= new VendorClient().get();
            vendorList=list.stream().map(object->(Vendor)object).collect(Collectors.toList());
            vendorList.forEach(v->{
               comboVendorName.addItem(v.getName());
            });
            
            list=null;
            list=new ItemClient().get();
            itemList=list.stream().map(object->(Item)object).collect(Collectors.toList());
            Vector<String> itemsNamesVector=new Vector<String>();
            itemList.forEach(i->{
                itemsNamesVector.add(i.getName());
            });
            loadItemNameList(itemsNamesVector);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void loadItemNameList(Vector<String> itemsNamesVector){
        listItemNames.removeAll();
        listItemNames.setListData(itemsNamesVector);
    }
    private void loadItemNameList(List<Item> itemList){
        Vector<String> itemsNamesVector=new Vector<String>();
        itemList.forEach(i->{
            itemsNamesVector.add(i.getName());
        });
        loadItemNameList(itemsNamesVector);
    }
    
    private void searchItemAndLoadList(){
        String searchKey=itemSearchKey.getText();
//        if(searchKey.length()<2){
//           loadItemNameList(itemList);
//        }
        if(searchKey.length()<2){
            List<Item> filteredList = itemList.stream().filter(i->i.getName().startsWith(searchKey)).collect(Collectors.toList());
            loadItemNameList(filteredList);
        }
    }
    
    private void addItemToBillingItem(List<Item> itemsList){
        DefaultTableModel billingTablemodel = (DefaultTableModel) tableBillingItems.getModel();
        Object[] rowdata={0,0,0,0,0};
        billingTablemodel.addRow(rowdata);
        
    }
    
    private void showItemDetails(String itemName){
        try{
            Optional<Item> itemOptional = itemList.stream().filter(i->i.getName().equalsIgnoreCase(itemName)).findFirst();
            Item item=itemOptional.get();
            textItemId.setText(item.getId().toString());
            textItemName.setText(item.getName());
            textPurchasePrise.setText(item.getPurchasePrice().toString());
            textQuantity.setText("0");
            textSalePrise.setText(item.getSalePrice().toString());
            textVat.setText(item.getVat().toString());
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        comboVendorName = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listItemNames = new javax.swing.JList<>();
        itemSearchKey = new javax.swing.JTextField();
        panelItemDetails = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textItemName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textPurchasePrise = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textSalePrise = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textVat = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textItemId = new javax.swing.JTextField();
        buttonAddItem = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBillingItems = new javax.swing.JTable();

        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Vendor")));

        jLabel1.setText("Name");

        comboVendorName.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(comboVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboVendorName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 49, Short.MAX_VALUE))
        );

        add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 11, -1, -1));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Item"));

        listItemNames.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listItemNames.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listItemNamesMouseClicked(evt);
            }
        });
        listItemNames.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                listItemNamesKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(listItemNames);

        itemSearchKey.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                itemSearchKeyFocusLost(evt);
            }
        });
        itemSearchKey.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSearchKeyActionPerformed(evt);
            }
        });
        itemSearchKey.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemSearchKeyKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(itemSearchKey, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 200));

        panelItemDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Purchase Details"));

        jLabel2.setText("Item Name");

        textItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textItemNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Purchase Prise");

        jLabel4.setText("Sale Prise");

        jLabel5.setText("VAT %");

        jLabel6.setText("Discount");

        jLabel7.setText("Quantity");

        jLabel8.setText("ID");

        textItemId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textItemIdActionPerformed(evt);
            }
        });

        buttonAddItem.setText("Add");
        buttonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddItemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelItemDetailsLayout = new javax.swing.GroupLayout(panelItemDetails);
        panelItemDetails.setLayout(panelItemDetailsLayout);
        panelItemDetailsLayout.setHorizontalGroup(
            panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemDetailsLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItemDetailsLayout.createSequentialGroup()
                        .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textSalePrise, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                            .addComponent(jTextField1)
                            .addComponent(textItemId))
                        .addGap(198, 198, 198))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItemDetailsLayout.createSequentialGroup()
                            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                                .addComponent(textVat, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(textPurchasePrise, javax.swing.GroupLayout.Alignment.LEADING))
                            .addGap(198, 198, 198))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelItemDetailsLayout.createSequentialGroup()
                            .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(buttonAddItem)
                                .addComponent(textItemName, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addContainerGap()))))
        );
        panelItemDetailsLayout.setVerticalGroup(
            panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelItemDetailsLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textItemName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textPurchasePrise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textVat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textSalePrise, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(panelItemDetailsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(textItemId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonAddItem)))
                .addContainerGap(65, Short.MAX_VALUE))
        );

        add(panelItemDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 11, 307, 350));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Items"));

        tableBillingItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "Item", "Purchase Prise", "VAT", "Quantity", "Total"
            }
        ));
        jScrollPane2.setViewportView(tableBillingItems);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 62, Short.MAX_VALUE))
        );

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 570, 350));
    }// </editor-fold>//GEN-END:initComponents

    private void itemSearchKeyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSearchKeyActionPerformed
       searchItemAndLoadList();
    }//GEN-LAST:event_itemSearchKeyActionPerformed

    private void textItemNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textItemNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textItemNameActionPerformed

    private void textItemIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textItemIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textItemIdActionPerformed

    private void itemSearchKeyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemSearchKeyKeyPressed
        searchItemAndLoadList();
    }//GEN-LAST:event_itemSearchKeyKeyPressed

    private void itemSearchKeyFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_itemSearchKeyFocusLost
        if(itemSearchKey.getText().equals(""))
            loadItemNameList(itemList);
    }//GEN-LAST:event_itemSearchKeyFocusLost

    private void listItemNamesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_listItemNamesKeyPressed
        if(evt.getKeyCode()== KeyEvent.VK_ENTER){
            showItemDetails(listItemNames.getSelectedValue());
        }
    }//GEN-LAST:event_listItemNamesKeyPressed

    private void listItemNamesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listItemNamesMouseClicked
       if(evt.getSource()==listItemNames){
            showItemDetails(listItemNames.getSelectedValue());
        }
    }//GEN-LAST:event_listItemNamesMouseClicked

    private void buttonAddItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAddItemActionPerformed
       addItemToBillingItem(null);
    }//GEN-LAST:event_buttonAddItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddItem;
    private javax.swing.JComboBox<String> comboVendorName;
    private javax.swing.JTextField itemSearchKey;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JList<String> listItemNames;
    private javax.swing.JPanel panelItemDetails;
    private javax.swing.JTable tableBillingItems;
    private javax.swing.JTextField textItemId;
    private javax.swing.JTextField textItemName;
    private javax.swing.JTextField textPurchasePrise;
    private javax.swing.JTextField textQuantity;
    private javax.swing.JTextField textSalePrise;
    private javax.swing.JTextField textVat;
    // End of variables declaration//GEN-END:variables
}
