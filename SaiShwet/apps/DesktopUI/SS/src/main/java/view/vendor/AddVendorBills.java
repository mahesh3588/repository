/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.vendor;

import beans.Item;
import beans.Vendor;
import beans.VendorItem;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
    List<VendorItem>  billItemList;
    Double totalPurchaseAmount=0.0;
    Double totalDiscountAmount=0.0;
    Double totalBillAmount=0.0;
    
    public AddVendorBills() {
        initComponents();
        setUp();
    }
    
    private void setUp(){
        try {
            billItemList=new ArrayList<>();
            
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
    
    private void addItemToBillingItem(List<VendorItem> itemsList){
        itemsList.forEach(item->addItemToBillingItem(item));
    }
    
    private void addItemToBillingItem(VendorItem vendorItem){
        DefaultTableModel billingTablemodel = (DefaultTableModel) tableBillingItems.getModel();
        Object[] rowdata={  
                            vendorItem.getName(),
                            vendorItem.getPurchasePrice(),
                            vendorItem.getDiscount(),
                            vendorItem.getQuantity(),
                            vendorItem.getTotalPurchaseAmount()
                        };
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
    
    private void resetItemTexts(){
        textDiscount.setText("");
        textItemId.setText("");
        textItemName.setText("");
        textPurchasePrise.setText("");
        textQuantity.setText("");
        textSalePrise.setText("");
        textVat.setText("");
        textTotalPurchasePrise.setText("");
    }
    
    private Boolean validateEmpty(){
        if(textDiscount.getText().equalsIgnoreCase(""))
            return false;
        if(textItemId.getText().equalsIgnoreCase(""))
            return false;
        if(textItemName.getText().equalsIgnoreCase(""))
            return false;
        if(textPurchasePrise.getText().equalsIgnoreCase(""))
            return false;
        if(textQuantity.getText().equalsIgnoreCase(""))
            return false;
        if(textSalePrise.getText().equalsIgnoreCase(""))
            return false;
        if(textTotalPurchasePrise.getText().equalsIgnoreCase(""))
            return false;
        if(textVat.getText().equalsIgnoreCase(""))
            return false;
        
        return true;
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
        textDiscount = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textQuantity = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        textItemId = new javax.swing.JTextField();
        buttonAddItem = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        textTotalPurchasePrise = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableBillingItems = new javax.swing.JTable();
        textTotalBillAmout = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        totalBillDiscount = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        textBillAmount = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        textBillVat = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        textPayableBillAmount = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
                .addContainerGap())
        );

        add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, -1, 230));

        panelItemDetails.setBorder(javax.swing.BorderFactory.createTitledBorder("Item Purchase Details"));
        panelItemDetails.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Item Name");
        panelItemDetails.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 30, 76, -1));

        textItemName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textItemNameActionPerformed(evt);
            }
        });
        panelItemDetails.add(textItemName, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 27, 183, -1));

        jLabel3.setText("Purchase Prise");
        panelItemDetails.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 68, 76, -1));

        textPurchasePrise.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textPurchasePriseFocusLost(evt);
            }
        });
        textPurchasePrise.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textPurchasePriseKeyPressed(evt);
            }
        });
        panelItemDetails.add(textPurchasePrise, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 65, 79, -1));

        jLabel4.setText("Total");
        panelItemDetails.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 179, 60, -1));
        panelItemDetails.add(textSalePrise, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 217, 79, -1));

        jLabel5.setText("% Discount");
        panelItemDetails.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 106, 76, -1));
        panelItemDetails.add(textVat, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 255, 79, -1));

        jLabel6.setText("% VAT");
        panelItemDetails.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 258, 60, -1));

        textDiscount.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textDiscountFocusLost(evt);
            }
        });
        panelItemDetails.add(textDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 103, 79, -1));

        jLabel7.setText("Quantity");
        panelItemDetails.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 144, 76, -1));

        textQuantity.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textQuantityFocusLost(evt);
            }
        });
        panelItemDetails.add(textQuantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 141, 79, -1));

        jLabel8.setText("ID");
        panelItemDetails.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 297, 25, -1));

        textItemId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textItemIdActionPerformed(evt);
            }
        });
        panelItemDetails.add(textItemId, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 294, 79, -1));

        buttonAddItem.setText("Add");
        buttonAddItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAddItemActionPerformed(evt);
            }
        });
        panelItemDetails.add(buttonAddItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(238, 293, -1, -1));

        jLabel9.setText("Sale Prise");
        panelItemDetails.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 217, -1, -1));
        panelItemDetails.add(textTotalPurchasePrise, new org.netbeans.lib.awtextra.AbsoluteConstraints(106, 179, 79, -1));

        add(panelItemDetails, new org.netbeans.lib.awtextra.AbsoluteConstraints(299, 11, 307, 370));

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Bill Items"));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tableBillingItems.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Purchase Prise", "Discount %", "Quantity", "Total"
            }
        ));
        jScrollPane2.setViewportView(tableBillingItems);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 538, 232));
        jPanel3.add(textTotalBillAmout, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 250, 110, -1));

        jLabel10.setText("Total Amount");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 250, 90, 20));
        jPanel3.add(totalBillDiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 280, 110, -1));

        jLabel11.setText("Total Discount");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 280, 70, 20));
        jPanel3.add(textBillAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 312, 110, -1));

        jLabel12.setText("Bill Amount");
        jPanel3.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 312, 90, 20));

        textBillVat.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textBillVatFocusLost(evt);
            }
        });
        textBillVat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBillVatKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                textBillVatKeyTyped(evt);
            }
        });
        jPanel3.add(textBillVat, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 343, 48, -1));

        jLabel13.setText("VAT  %");
        jPanel3.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 343, 90, 20));
        jPanel3.add(textPayableBillAmount, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 374, 110, -1));

        jLabel14.setText("Payable Amount");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 377, 90, 17));

        add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 10, 570, 410));
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
       VendorItem vendorItem=null;
       
//        if (billItemList.size() > 0) {
//            vendorItem = billItemList.stream()
//                    .filter(item -> item.getId() == Integer.parseInt(textItemId.getText()))
//                    .findFirst()
//                    .get();
//        }
       
       if(vendorItem==null){
            vendorItem=new VendorItem();
            if(validateEmpty()){
                 vendorItem.setId(Integer.parseInt(textItemId.getText()));
                 vendorItem.setDiscount(Double.parseDouble(textDiscount.getText()));
                 vendorItem.setName(textItemName.getText());
                 vendorItem.setPurchasePrice(Double.parseDouble(textPurchasePrise.getText()));
                 vendorItem.setQuantity(Integer.parseInt(textQuantity.getText()));
                 vendorItem.setSalePrice(Double.parseDouble(textSalePrise.getText()));
                 vendorItem.setTotalPurchaseAmount(Double.parseDouble(textTotalPurchasePrise.getText()));
                 vendorItem.setVat(Double.parseDouble(textVat.getText()));

                 billItemList.add(vendorItem);
                 addItemToBillingItem(vendorItem);
                 calculateTotals();
                 resetItemTexts();
                 
            }else{
                JOptionPane.showMessageDialog(null, "Please enter all information about the Item");
            }
       }
       
    }//GEN-LAST:event_buttonAddItemActionPerformed

    private void textPurchasePriseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textPurchasePriseFocusLost
       calculate();
    }//GEN-LAST:event_textPurchasePriseFocusLost

    private void textPurchasePriseKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textPurchasePriseKeyPressed
      // calculate();
    }//GEN-LAST:event_textPurchasePriseKeyPressed

    private void textDiscountFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textDiscountFocusLost
        calculate();
    }//GEN-LAST:event_textDiscountFocusLost

    private void textQuantityFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textQuantityFocusLost
       calculate();
    }//GEN-LAST:event_textQuantityFocusLost

    private void textBillVatFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textBillVatFocusLost
        calculatePayableAmount();
        
    }//GEN-LAST:event_textBillVatFocusLost

    private void textBillVatKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBillVatKeyPressed
        //calculatePayableAmount();
    }//GEN-LAST:event_textBillVatKeyPressed

    private void textBillVatKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBillVatKeyTyped
        calculatePayableAmount();
    }//GEN-LAST:event_textBillVatKeyTyped

    
    
    private void calculateTotals(){
        
        totalPurchaseAmount=0.0;
        totalDiscountAmount=0.0;
       
        billItemList.stream()
                    .forEach(item->{
                        totalPurchaseAmount=totalPurchaseAmount+(item.getPurchasePrice()*item.getQuantity());
                        totalDiscountAmount=totalDiscountAmount+((item.getDiscount()*item.getPurchasePrice())/100)*item.getQuantity();
                    });
        totalBillAmount=totalPurchaseAmount-totalDiscountAmount;
        
        textTotalBillAmout.setText(totalPurchaseAmount.toString());
        totalBillDiscount.setText(totalDiscountAmount.toString());
        textBillAmount.setText(totalBillAmount+"");
        textBillVat.setText("0.0");
        textPayableBillAmount.setText(totalBillAmount+"");
                    
    }

    private void calculatePayableAmount(){
        if(!textBillVat.getText().equals("")){
            Double vat=Double.parseDouble(textBillVat.getText());
            textPayableBillAmount.setText((totalBillAmount+(totalBillAmount*vat)/100)+"");
        }
    }
    
    private void calculate(){
        Double purchasePrise=0.0;
        Double quantity=0.0;
        Double discount=0.0;
        Double salePriseToCustomer=0.0;
        
        if (!textDiscount.getText().equals("") 
                && !textPurchasePrise.getText().equals("") 
                && !textQuantity.getText().equals("")) {
            purchasePrise = Double.parseDouble(textPurchasePrise.getText());
            discount = Double.parseDouble(textDiscount.getText());
            quantity = Double.parseDouble(textQuantity.getText());

            salePriseToCustomer = purchasePrise-((discount * purchasePrise) / 100);
            Double totalPurchasePrise = salePriseToCustomer * quantity;

            textTotalPurchasePrise.setText(totalPurchasePrise.toString());
            textSalePrise.setText(salePriseToCustomer.toString());
        }
        
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAddItem;
    private javax.swing.JComboBox<String> comboVendorName;
    private javax.swing.JTextField itemSearchKey;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<String> listItemNames;
    private javax.swing.JPanel panelItemDetails;
    private javax.swing.JTable tableBillingItems;
    private javax.swing.JTextField textBillAmount;
    private javax.swing.JTextField textBillVat;
    private javax.swing.JTextField textDiscount;
    private javax.swing.JTextField textItemId;
    private javax.swing.JTextField textItemName;
    private javax.swing.JTextField textPayableBillAmount;
    private javax.swing.JTextField textPurchasePrise;
    private javax.swing.JTextField textQuantity;
    private javax.swing.JTextField textSalePrise;
    private javax.swing.JTextField textTotalBillAmout;
    private javax.swing.JTextField textTotalPurchasePrise;
    private javax.swing.JTextField textVat;
    private javax.swing.JTextField totalBillDiscount;
    // End of variables declaration//GEN-END:variables
}
