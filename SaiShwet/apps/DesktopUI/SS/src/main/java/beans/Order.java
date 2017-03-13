/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.util.Map;

/**
 *
 * @author Owner
 */
public class Order {
    
    private Date billDate;
    
    private Integer customerId;
    
    private Double ledgerBalance;
    private Double billAmount;
    private Double paidAmount;
    
    private Map<Integer,Integer> billingItems;

    public Date getBillDate() {
        return billDate;
    }
    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getLedgerBalance() {
        return ledgerBalance;
    }

    public void setLedgerBalance(Double ledgerBalance) {
        this.ledgerBalance = ledgerBalance;
    }

    public Double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(Double billAmount) {
        this.billAmount = billAmount;
    }

    public Double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Map<Integer, Integer> getBillingItems() {
        return billingItems;
    }
    public void setBillingItems(Map<Integer, Integer> billingItems) {
        this.billingItems = billingItems;
    }
    
}
