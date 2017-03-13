/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.vendor;

import beans.Customer;
import beans.Vendor;
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
public class VendorClient extends RestClient{
    private static final String VENDOR_SERVICES="/vendorServices";
    private static final String ADD_VENDOR_SERVICE="/add";
    private static final String GET_VENDOR_SERVICE="/getAllVendor";
    private static final String GET_VENDOR_BY_ID_SERVICE="/getByID?vendorId=";
    private static final String UPDATE_VENDOR_SERVICE="/update";
    
    private String getAddVendorServiceURL(){
        return BASE_URL+VENDOR_SERVICES+ADD_VENDOR_SERVICE;
    }
    
    private String getVendorServiceURL(){
        return BASE_URL+VENDOR_SERVICES+GET_VENDOR_SERVICE;
    }
    
    private String getVendorByIdServiceURL(){
        return BASE_URL+VENDOR_SERVICES+GET_VENDOR_BY_ID_SERVICE;
    }
    
    private String getUpdateVendorServiceURL(){
        return BASE_URL+VENDOR_SERVICES+UPDATE_VENDOR_SERVICE;
    }
    
    @Override
    public Integer add(Object object){
        WebResource resource=restClient.resource(getAddVendorServiceURL());
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
        WebResource webResource=restClient.resource(getVendorServiceURL());
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            String customerListJson=clientResponse.getEntity(String.class);
            ObjectMapper objectMapper=new ObjectMapper();
            try{
                entityList=objectMapper.readValue(customerListJson, TypeFactory.defaultInstance().constructCollectionType(List.class, Vendor.class));
            }catch(Exception e){
                e.printStackTrace();
            } 
        }
        return entityList;
    }
    
    @Override
    public Object getByID(Integer customerId){
        Customer customer=null;
        WebResource webResource=restClient.resource(getVendorByIdServiceURL()+customerId);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            customer=clientResponse.getEntity(Customer.class);
        }
        return customer;
    }
    
    @Override
    public boolean update(Object entity){
        boolean isUpdated=false;
        WebResource resource=restClient.resource(getUpdateVendorServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,entity);
        if(response.getStatus()==200){
            isUpdated=true;
        }
        return isUpdated;
    }
}
