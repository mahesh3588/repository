package services.item.stocks;

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
import dao.item.stocks.Stock;
import dao.item.stocks.StocksDBUtil;

@Path("/stockServices")
public class StockServices {

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addStock(Stock stock) throws DAOException {
		int status = 500;
		int newQuantity=0;
		try {
			Stock existingStock = new StocksDBUtil().get(stock.getItemId());
			if (existingStock != null) {
				System.out.println("Stock exist");
				existingStock.setAvailableStock(existingStock.getAvailableStock() + stock.getAvailableStock());
				new StocksDBUtil().update(existingStock);
				newQuantity=existingStock.getAvailableStock();
				status = 200;
			}else{
				System.out.println("Stock not exist");
				int id=new StocksDBUtil().add(stock);
				if(id>0){
					status=200;
					newQuantity=stock.getAvailableStock();
				}
			}
		} catch (DAOException e) {
			throw new DAOException("DAO Exception occurred while getting stock details : " + e.getMessage());
		}
		return Response.status(status).entity(newQuantity).build();
	}

	@GET
	@Path("/getAllStocks")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStock() {
		int status = 500;
		List<Stock> stockList = Collections.EMPTY_LIST;
		try {
			stockList = new StocksDBUtil().get();
			status = 200;
		} catch (DAOException e) {
			e.printStackTrace();
		}

		return Response.status(status).entity(stockList).build();
	}
	
	@GET
	@Path("/getOfId")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getStock(@QueryParam("itemId") String itemId) {
		System.out.println(" get stock "+itemId);
		int status = 500;
		Stock stock = null;
		try {
			stock = new StocksDBUtil().get(Integer.parseInt(itemId));
			System.out.println("qvlbl = "+stock.getAvailableStock());
			status = 200;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return Response.status(status).entity(stock).build();
	}
}
