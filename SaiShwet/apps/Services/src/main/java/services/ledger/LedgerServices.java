package services.ledger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

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
import dao.ledger.quotation.Quotation;
import dao.ledger.quotation.QuotationItem;
import dao.ledger.quotation.QuotationUtil;

@Path("/ledgerServices")
public class LedgerServices {

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response add(Ledger ledgerEntry){
		try {
			new LedgerUtil().add(ledgerEntry);
		} catch (DAOException e) {
			// TODO: handle exception
		}
		return null;
	}
	
	@GET
	@Path("/customerLedger")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerLedger(@QueryParam("customerId") String customerId){
		System.out.println("ledgg  =  "+customerId);
		CustomerLedger customerLedger=null;
		int status=500;
		try {
			Customer customer=new CustomerUtil().get(Integer.parseInt(customerId));
			List<Ledger> ledger=new LedgerUtil().getCustomerLedger(Integer.parseInt(customerId));
			customerLedger=new CustomerLedger();
			customerLedger.setCustomerDetails(customer);
			customerLedger.setCustomerLedger(ledger);
			status=200;
		} catch (DAOException e) {
			status=500;
			e.printStackTrace();
		}
		return Response.status(status).entity(customerLedger).build();
	}
	
	
	@GET
	@Path("/getQuotations")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getQuotations(){
		int status=500;
		System.out.println("getQuotation");
		List<Quotation> quotations=new ArrayList<Quotation>();
		try {
			quotations=new QuotationUtil().get();
			
			status=200;
			System.out.println("size "+quotations.size());
		} catch (Exception e) {
			status=500;
			e.printStackTrace();
		}
		return Response.status(status).entity(quotations).build();
	}
	
	
	
	
	
}
