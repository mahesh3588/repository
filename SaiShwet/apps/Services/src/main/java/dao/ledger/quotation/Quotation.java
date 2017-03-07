package dao.ledger.quotation;

import java.sql.Date;
import java.util.Set;

import dao.ledger.SoldItem;

public class Quotation {

	private Integer id;
	private Integer customerId;
	private Date date;
	private Double quotationAmount;
	private String description;
	private Set<QuotationItem> quotationItem;

	public Quotation() {
	}

	public Quotation(Integer id, Integer customerId, Date date, Double quotationAmount, String description,
			Set<QuotationItem> quotationItem) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.date = date;
		this.quotationAmount = quotationAmount;
		this.description = description;
		this.quotationItem = quotationItem;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getQuotationAmount() {
		return quotationAmount;
	}

	public void setQuotationAmount(Double quotationAmount) {
		this.quotationAmount = quotationAmount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<QuotationItem> getQuotationItem() {
		return quotationItem;
	}

	public void setQuotationItem(Set<QuotationItem> quotationItem) {
		this.quotationItem = quotationItem;
	}

}
