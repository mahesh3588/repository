package services.vendor;

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

import org.apache.log4j.Logger;

import dao.exception.DAOException;
import dao.vendor.VendorBill;
import dao.vendor.VendorBillUtil;

@Path("/vendorBillService")
public class VendorBillService {
	
	private static Logger log=Logger.getLogger(VendorBillService.class);
	
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addVendor(VendorBill vendorBill){
		System.out.println("vendor bill add service");
		int status = 500;
		Long id =0L;
		try {
			id = new VendorBillUtil().add(vendorBill);
			status=200;
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
	public Response updateVendor(VendorBill vendorBill){
		int status = 500;
		try {
			new VendorBillUtil().update(vendorBill);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).build();
	}


	@GET
	@Path("/getByVID")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getVendorById(@QueryParam("vendorBillId") String vendorId){
		int status = 500;
		List<VendorBill> vendorBills=null;
		try {
			int vid=Integer.parseInt(vendorId);
			vendorBills=new VendorBillUtil().getVendorBills(vid);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(vendorBills).build();
	}
	
	@GET
	@Path("/getAllVendorBills")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		int status = 500;
		log.debug("Into the get() of VendorBillService class");
		List<VendorBill> vendorBills=null;
		try {
			vendorBills =new VendorBillUtil().get();
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		vendorBills.forEach(System.out::println);
		log.debug("End the get() of VendorBillService class");
		return Response.status(status).entity(vendorBills).build();
	}
	
}
