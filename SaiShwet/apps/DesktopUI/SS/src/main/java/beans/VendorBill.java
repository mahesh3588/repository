/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Owner
 */
public class VendorBill {
    
    private Long billId;
    private Date date;
    private Double totalPurchaseAmount=0.0;
    private Double totalDiscountAmount=0.0;
    private Double totalBillAmount=0.0;
    private Double totalPayableBillAmount=0.0;
    private Double vat=0.0;
    private Integer vendorId;
    private String vendorName;
    
    private List<VendorItem>  billItemList;
        
    public void setBillId(Long billId) {
        this.billId = billId;
    }
    public Long getBillId() {
        return billId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Date getDate() {
        return date;
    }
    
    public List<VendorItem> getBillItemList() {
        return billItemList;
    }

    public void setBillItemList(List<VendorItem> billItemList) {
        this.billItemList = billItemList;
    }

    public Double getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(Double totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }

    public Double getTotalDiscountAmount() {
        return totalDiscountAmount;
    }

    public void setTotalDiscountAmount(Double totalDiscountAmount) {
        this.totalDiscountAmount = totalDiscountAmount;
    }

    public Double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(Double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public Double getTotalPayableBillAmount() {
        return totalPayableBillAmount;
    }

    public void setTotalPayableBillAmount(Double totalPayableBillAmount) {
        this.totalPayableBillAmount = totalPayableBillAmount;
    }

    public Double getVat() {
        return vat;
    }

    public void setVat(Double vat) {
        this.vat = vat;
    }

    public Integer getVendorId() {
        return vendorId;
    }

    public void setVendorId(Integer vendorId) {
        this.vendorId = vendorId;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    
}
