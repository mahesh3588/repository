/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.List;

/**
 *
 * @author Owner
 */
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
