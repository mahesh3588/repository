package dao.vendor;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Owner
 */
@Entity
@Table(name="TB_Vendor_Bill")
public class VendorBill {
    
	@Id
	@Column(name="billId")
	@SequenceGenerator(sequenceName="seq_vendor_bill", name="seq_vendor_bill", allocationSize=1,initialValue=1)
    private Long billId;
	
	@OneToMany
    private List<VendorItem>  billItemList;
    private Double totalPurchaseAmount=0.0;
    private Double totalDiscountAmount=0.0;
    private Double totalBillAmount=0.0;
    private Double totalPayableBillAmount=0.0;
    private Double vat=0.0;
    private Vendor vendor;
    
    public void setBillId(Long billId) {
        this.billId = billId;
    }
    public Long getBillId() {
        return billId;
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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }
    
    
    
}
