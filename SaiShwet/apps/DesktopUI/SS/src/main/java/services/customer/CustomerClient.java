/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.customer;

import beans.Customer;
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
public class CustomerClient extends RestClient{
    private static final String CUSTOMER_SERVICES="/customerServices";
    private static final String ADD_CUSTOMER_SERVICE="/add";
    private static final String GET_CUSTOMER_SERVICE="/getAllCustomer";
    private static final String GET_CUSTOMER_BY_ID_SERVICE="/getByID?customerId=";
    private static final String GET_CUSTOMER_BY_NAME_SERVICE="/getByID?customerName=";
    private static final String UPDATE_CUSTOMER_SERVICE="/update";
    
    private String getAddCustomerServiceURL(){
        return BASE_URL+CUSTOMER_SERVICES+ADD_CUSTOMER_SERVICE;
    }
    
    private String getCustomerServiceURL(){
        return BASE_URL+CUSTOMER_SERVICES+GET_CUSTOMER_SERVICE;
    }
    
    private String getCustomerByIdServiceURL(){
        return BASE_URL+CUSTOMER_SERVICES+GET_CUSTOMER_BY_ID_SERVICE;
    }
    
     private String getCustomerByNameServiceURL(){
        return BASE_URL+CUSTOMER_SERVICES+GET_CUSTOMER_BY_ID_SERVICE;
    }
    
    private String getUpdateCustomerServiceURL(){
        return BASE_URL+CUSTOMER_SERVICES+UPDATE_CUSTOMER_SERVICE;
    }
    
    @Override
    public Integer add(Object object){
        WebResource resource=restClient.resource(getAddCustomerServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,object);
        Integer customerId=0;
        if(response.getStatus()==200){
            customerId=response.getEntity(Integer.class);
        }
        return customerId;
    }   
    
    @Override
    public List<Object> get(){
        List<Object> entityList=Collections.EMPTY_LIST;
        WebResource webResource=restClient.resource(getCustomerServiceURL());
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            String customerListJson=clientResponse.getEntity(String.class);
            ObjectMapper objectMapper=new ObjectMapper();
            try{
                entityList=objectMapper.readValue(customerListJson, TypeFactory.defaultInstance().constructCollectionType(List.class, Customer.class));
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
        return entityList;
    }
    
    @Override
    public Object getByID(Integer customerId){
        Customer customer=null;
        WebResource webResource=restClient.resource(getCustomerByIdServiceURL()+customerId);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            customer=clientResponse.getEntity(Customer.class);
        }
        return customer;
    }
    
    public Object getByName(String customerName){
        Customer customer=null;
        WebResource webResource=restClient.resource(getCustomerByNameServiceURL()+customerName);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            customer=clientResponse.getEntity(Customer.class);
        }
        return customer;
    }
    
    @Override
    public boolean update(Object entity){
        boolean isUpdated=false;
        WebResource resource=restClient.resource(getUpdateCustomerServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,entity);
        if(response.getStatus()==200){
            isUpdated=true;
        }
        return isUpdated;
    }
}
