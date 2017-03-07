package services.ledger;

import java.util.List;

import dao.customer.Customer;
import dao.ledger.Ledger;

public class CustomerLedger {

	private Customer customerDetails;
	private List<Ledger> customerLedger;

	public Customer getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}

	public List<Ledger> getCustomerLedger() {
		return customerLedger;
	}

	public void setCustomerLedger(List<Ledger> customerLedger) {
		this.customerLedger = customerLedger;
	}

}
