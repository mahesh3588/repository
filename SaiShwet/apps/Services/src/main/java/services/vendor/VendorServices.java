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

import dao.vendor.Vendor;
import dao.vendor.VendorUtil;
import dao.exception.DAOException;

@Path("/vendorServices")
public class VendorServices {
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addVendor(Vendor vendor){
		System.out.println("vendor add service");
		int status = 500;
		Integer id =0;
		try {
			id = new VendorUtil().add(vendor);
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
	public Response updateVendor(Vendor vendor){
		int status = 500;
		try {
			new VendorUtil().update(vendor);
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
	public Response getVendorById(@QueryParam("vendorId") String vendorId){
		int status = 500;
		Vendor vendor=null;
		try {
			int id=Integer.parseInt(vendorId);
			vendor=new VendorUtil().get(id);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(vendor).build();
	}
	
	@GET
	@Path("/getAllVendor")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		int status = 500;
		List<Vendor> customerList=Collections.EMPTY_LIST;
		try {
			customerList =new VendorUtil().get();
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(customerList).build();
	}
	
}
