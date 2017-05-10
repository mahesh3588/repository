package dao.vendor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Owner
 */
@Entity
@Table(name="TB_Vendor_Bill_Item")
public class VendorItem {
	@Id
	@SequenceGenerator(name="vendor_bill_item__seq",sequenceName="TB_Vendor_Bill_Item_seq",initialValue=1,allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="vendor_bill_item__seq")
   
	private Integer id;
	
    private String name;
	private Double salePrice;
	private Double purchasePrice;
	private Double vat;
	private Double discount;
	
	private Integer quantity;
	private Double totalPurchaseAmount;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="billId")
    private VendorBill vendorBill;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Double getVat() {
		return vat;
	}
	public void setVat(Double vat) {
		this.vat = vat;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}
	
	public void setPurchasePrice(Double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}
	public Double getPurchasePrice() {
		return purchasePrice;
	}
	
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotalPurchaseAmount() {
        return totalPurchaseAmount;
    }

    public void setTotalPurchaseAmount(Double totalPurchaseAmount) {
        this.totalPurchaseAmount = totalPurchaseAmount;
    }
    
    public VendorBill getVendorBill() {
		return vendorBill;
	}
    
    public void setVendorBill(VendorBill vendorBill) {
		this.vendorBill = vendorBill;
	}
    
    
	@Override
	public String toString() {
		return "VendorItem [id=" + id + ", name=" + name + ", salePrice=" + salePrice + ", purchasePrice="
				+ purchasePrice + ", vat=" + vat + ", discount=" + discount + ", quantity=" + quantity
				+ ", totalPurchaseAmount=" + totalPurchaseAmount + "]";
	}

   
    
   /* public static void main(String[] args) {
		
    	try {
			
    		VendorItem v=new VendorItem();
    		v.setDiscount(253.23);
    		v.setName("dsds");
    		v.setPurchasePrice(25.12);
    		v.setQuantity(258);
    		
    		Session session=SessionUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			int id =(Integer) session.save(v);
			System.out.println(id);
			transaction.commit();
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
	}*/
    
}
