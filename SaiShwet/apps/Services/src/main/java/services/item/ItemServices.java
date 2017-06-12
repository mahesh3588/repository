package services.item;

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
import dao.exception.DAOException;
import dao.item.Item;
import dao.item.ItemUtil;

@Path("/itemService")
public class ItemServices {
	
	/*@GET
	@Path("/test")
	public Response testService(){
		System.out.println("test service");
		return Response.status(200).build();
	}*/
	
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItem(Item item){
		int status = 500;
		Integer id =0;
		try {
			id = new ItemUtil().add(item);
			if(id>0){
				status=200;
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
	public Response updateItem(Item item){
		int status = 500;
		try {
			new ItemUtil().update((item));
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
	public Response getItemById(@QueryParam("itemId") String itemId){
		System.out.println("getItemById");
		int status = 500;
		Item item=null;
		try {
			int id=Integer.parseInt(itemId);
			item=new ItemUtil().get(id);
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(item).build();
	}
	
	@GET
	@Path("/getAllItem")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		int status = 500;
		List<Item> itemList=Collections.EMPTY_LIST;
		try {
			itemList = new ItemUtil().get();
			status=200;
		} catch (DAOException e) {
			status = 500;
			e.printStackTrace();
		}
		return Response.status(status).entity(itemList).build();
	}
	
}
