package services.customer;

import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.customer.Customer;
import dao.customer.CustomerUtil;
import dao.exception.DAOException;
import dao.ledger.Ledger;
import dao.ledger.LedgerUtil;

@Path("/customerServices")
public class CustomerServices {
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addCustomer(Customer customer){
		int status = 500;
		Integer id =0;
		try {
			id = new CustomerUtil().add(customer);
			if(id>0){
				status=200;
				Ledger firstLedgerEntry=new Ledger();
				firstLedgerEntry.setBalance(0.0);
				firstLedgerEntry.setBillAmount(0.0);
				firstLedgerEntry.setBillingType("First");
				firstLedgerEntry.setCredit(0.0);
				firstLedgerEntry.setCustomerId(id);
				firstLedgerEntry.setDebit(0.0);
				firstLedgerEntry.setDescription("FirstEntry");
				firstLedgerEntry.setPaidAmount(0.0);
				new LedgerUtil().add(firstLedgerEntry);
				
			}	
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(id).build();
	}
	
	

	@POST
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateCustomer(Customer customer){
		int status = 500;
		try {
			new CustomerUtil().update(customer);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).build();
	}


	@GET
	@Path("/getByID")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCustomerById(@QueryParam("customerId") String customerId){
		int status = 500;
		Customer customer=null;
		try {
			int id=Integer.parseInt(customerId);
			customer=new CustomerUtil().get(id);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(customer).build();
	}
	
	@GET
	@Path("/getByName")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getCustomerByName(@QueryParam("customerName") String customerName){
		int status = 500;
		Customer customer=null;
		try {
			customer=new CustomerUtil().get(customerName);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(customer).build();
	}
	
	@GET
	@Path("/getAllCustomer")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		int status = 500;
		List<Customer> customerList=Collections.EMPTY_LIST;
		try {
			customerList =new CustomerUtil().get();
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(customerList).build();
	}
	
}
