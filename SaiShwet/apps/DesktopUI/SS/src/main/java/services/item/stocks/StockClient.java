/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.item.stocks;

import beans.Stock;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.MediaType;
import services.RestClient;

/**
 *
 * @author Owner
 */
public class StockClient extends RestClient{
    private static final String STOCK_SERVICES="/stockServices";
    private static final String ADD_STOCK_SERVICE="/add";
    private static final String GET_STOCK_SERVICE="/getAllStocks";
    private static final String GET_STOCK_OF_ID_SERVICE="/getOfId?itemId=";
    
     private String getStockAddServiceURL(){
        return BASE_URL+STOCK_SERVICES+ADD_STOCK_SERVICE;
    }
    
    private String getStocksServiceURL(){
        return BASE_URL+STOCK_SERVICES+GET_STOCK_SERVICE;
    }
    
    private String getStockByIdServiceURL(){
        return BASE_URL+STOCK_SERVICES+GET_STOCK_OF_ID_SERVICE;
    }
    
   
    
    @Override
    public Integer add(Object object){
        
        WebResource resource=restClient.resource(getStockAddServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,object);
        Integer availableStock=0;
        if(response.getStatus()==200){
            availableStock=response.getEntity(Integer.class);
        }
        return availableStock;
    }   
    
    @Override
    public List<Object> get(){
        List<Object> stocksList=Collections.EMPTY_LIST;
        WebResource webResource=restClient.resource(getStocksServiceURL());
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            String stockListJson=clientResponse.getEntity(String.class);
            ObjectMapper objectMapper=new ObjectMapper();
            try{
                stocksList=objectMapper.readValue(stockListJson, TypeFactory.defaultInstance().constructCollectionType(List.class, Stock.class));
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
        return stocksList;
    }
    
    @Override
    public Object getByID(Integer itemId){
        Object stock=null;
        System.out.println(getStockByIdServiceURL());
        WebResource webResource=restClient.resource(getStockByIdServiceURL()+itemId);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        System.out.println("res  = "+clientResponse.getStatus());
        if(clientResponse.getStatus()==200){
            stock=clientResponse.getEntity(Stock.class);
        }
        return stock;
    }
    
    @Override
    public boolean update(Object entity){
        return false;
    }
    
//    public static void main(String args[]){
//     Stock s=(Stock) new StockClient().getByID(1);
//        System.out.println(s.getAvailableStock());
//    }
    
    
}
