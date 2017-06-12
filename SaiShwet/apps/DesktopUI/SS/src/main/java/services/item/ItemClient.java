/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.item;

import beans.Item;
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
public class ItemClient extends RestClient{
    private static final String ITEM_SERVICES="/itemService";
    private static final String ADD_ITEM_SERVICE="/add";
    private static final String GET_ITEM_SERVICE="/getAllItem";
    private static final String GET_ITEM_BY_ID_SERVICE="/getByID?itemId=";
    private static final String UPDATE_ITEM_SERVICE="/update";
    
    private String getAddServiceURL(){
        return BASE_URL+ITEM_SERVICES+ADD_ITEM_SERVICE;
    }
    
    private String getItemsServiceURL(){
        return BASE_URL+ITEM_SERVICES+GET_ITEM_SERVICE;
    }
    
    private String getItemByIdServiceURL(){
        return BASE_URL+ITEM_SERVICES+GET_ITEM_BY_ID_SERVICE;
    }
    
    private String getUpdateItemServiceURL(){
        return BASE_URL+ITEM_SERVICES+UPDATE_ITEM_SERVICE;
    }
    
    @Override
    public Integer add(Object object){
        WebResource resource=restClient.resource(getAddServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,object);
        Integer itemId=0;
        if(response.getStatus()==200){
            itemId=response.getEntity(Integer.class);
        }
        return itemId;
    }   
    
    @Override
    public List<Object> get(){
        List<Object> entityList=Collections.EMPTY_LIST;
        WebResource webResource=restClient.resource(getItemsServiceURL());
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            String itemListJson=clientResponse.getEntity(String.class);
            ObjectMapper objectMapper=new ObjectMapper();
            try{
                entityList=objectMapper.readValue(itemListJson, TypeFactory.defaultInstance().constructCollectionType(List.class, Item.class));
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
        return entityList;
    }
    
    @Override
    public Object getByID(Integer entityId){
        Object entity=null;
        WebResource webResource=restClient.resource(getItemByIdServiceURL()+entityId);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            entity=clientResponse.getEntity(Item.class);
        }
        return entity;
    }
    
    @Override
    public boolean update(Object entity){
        boolean isUpdated=false;
        WebResource resource=restClient.resource(getUpdateItemServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,entity);
        if(response.getStatus()==200){
            isUpdated=true;
        }
        return isUpdated;
    }
}
