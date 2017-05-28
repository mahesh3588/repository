package services.billing;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import dao.DBUtil;

@Path("/dbService")
public class AdminDBServices {

	@GET
	@Path("/deleteDb")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response clearDatabse(){
		try {
			if( new DBUtil().deleteAllTables())
				return Response.status(200).entity("true").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("false").build();
		}
		return Response.status(500).entity("false").build();
	}
	
	@GET
	@Path("/dbBackup")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createBackup(){
		try {
			if(new DBUtil().getBackUp())
				return Response.status(200).entity("true").build();
		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("false").build();
		}
		return Response.status(500).entity("false").build();
	}
}
