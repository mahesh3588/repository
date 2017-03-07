package dao.ledger;

import java.sql.Date;
import java.util.Set;

public class Ledger{
	
	private Integer invoiceNumber;
	private Integer customerId;
	private Date date;
	private Double billAmount;
	private String billingType;
	private Double paidAmount;
	private Double debit;
	private Double credit;
	private Double balance;
	private String description;
	private Set<SoldItem> soldItems;
	
	
	public Ledger() {
	}
	
	public Ledger(Integer invoiceNumber, Integer customerId, Date date, Double billAmount, String billingType,
			Double paidAmount, Double debit, Double credit, Double balance, String description) {
		super();
		this.invoiceNumber = invoiceNumber;
		this.customerId = customerId;
		this.date = date;
		this.billAmount = billAmount;
		this.billingType = billingType;
		this.paidAmount = paidAmount;
		this.debit = debit;
		this.credit = credit;
		this.balance = balance;
		this.description = description;
	}
	public void setInvoiceNumber(Integer invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}
	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getDate() {
		return date;
	}
	
	
	public void setBillingType(String billingType) {
		this.billingType = billingType;
	}
	public String getBillingType() {
		return billingType;
	}
	
	public void setBillAmount(Double billAmount) {
		this.billAmount = billAmount;
	}
	public Double getBillAmount() {
		return billAmount;
	}
	
	
	public void setPaidAmount(Double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public Double getPaidAmount() {
		return paidAmount;
	}
	
	
	
	public void setDebit(Double debit) {
		this.debit = debit;
	}
	public Double getDebit() {
		return debit;
	}
	
	
	public void setCredit(Double credit) {
		this.credit = credit;
	}
	public Double getCredit() {
		return credit;
	}
	
	
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public Double getBalance() {
		return balance;
	}
	
	
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	
	public void setSoldItems(Set<SoldItem> soldItems) {
		this.soldItems = soldItems;
	}
	public Set<SoldItem> getSoldItems() {
		return soldItems;
	}
}
