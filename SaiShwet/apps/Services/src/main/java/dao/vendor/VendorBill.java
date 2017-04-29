package dao.vendor;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.Session;
import org.hibernate.Transaction;

import dao.SessionUtil;

/**
 *
 * @author Owner
 */

@Entity
@Table(name="TB_VENDOR_BILL")
public class VendorBill {
    
	@Id
	@SequenceGenerator(name="vendor_bill_seq",sequenceName="TB_VENDOR_BILL_SEQ",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="vendor_bill_seq")
    private Long billId;
	
    
    private Double totalPurchaseAmount=0.0;
    private Double totalDiscountAmount=0.0;
    private Double totalBillAmount=0.0;
    private Double totalPayableBillAmount=0.0;
    private Double vat=0.0;
    private Integer vendorId;
    private String vendorName;
    
    @OneToMany(mappedBy="vendorBill",cascade=CascadeType.ALL)
    private List<VendorItem>  billItemList;
    
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

    
	
	
	
	
	 @Override
	public String toString() {
		return "VendorBill [billId=" + billId + ", totalPurchaseAmount=" + totalPurchaseAmount
				+ ", totalDiscountAmount=" + totalDiscountAmount + ", totalBillAmount=" + totalBillAmount
				+ ", totalPayableBillAmount=" + totalPayableBillAmount + ", vat=" + vat + ", vendorId=" + vendorId
				+ ", vendorName=" + vendorName + ", billItemList=" + billItemList + "]";
	}
	public static void main(String[] args) {
			
	    	try {
				
	    		VendorItem v=new VendorItem();
	    		v.setDiscount(253.23);
	    		v.setName("dsds");
	    		v.setPurchasePrice(25.12);
	    		v.setQuantity(258);
	    		
	    		VendorItem v2=new VendorItem();
	    		v2.setDiscount(253.23);
	    		v2.setName("dsds");
	    		v2.setPurchasePrice(25.12);
	    		v2.setQuantity(258);
	    		
	    		List<VendorItem> list=new ArrayList<>();
	    		list.add(v);
	    		list.add(v2);
	    		
	    		VendorBill bill=new VendorBill();
	    		bill.setBillItemList(list);
	    		bill.setTotalBillAmount(699.8);
	    		bill.setTotalDiscountAmount(23.3);
	    		bill.setVat(2.3);
	    		
	    		list.forEach(p->p.setVendorBill(bill));
	    		
	    		Session session=SessionUtil.getSessionFactory().openSession();
				Transaction transaction = session.beginTransaction();
				session.save(bill);
				//System.out.println(id);
				transaction.commit();
	    		session.close();
	    		
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
		}
    
    
}
