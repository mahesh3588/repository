/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.vendor;

import beans.Customer;
import beans.Vendor;
import beans.VendorBill;
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
public class VendorBillClient extends RestClient{
    private static final String VENDOR_BILL_SERVICES="/vendorBillService";
    private static final String ADD_VENDOR_BILL_SERVICE="/add";
    private static final String GET_VENDOR_BILL_SERVICE="/getAllVendorBills";
    private static final String GET_VENDOR_BILL_BY_VENDOR_ID_SERVICE="/getByVID?vendorBillId=";
    private static final String UPDATE_VENDOR_BILL_SERVICE="/update";
    //
    private String getAddVendorBillServiceURL(){
        return BASE_URL+VENDOR_BILL_SERVICES+ADD_VENDOR_BILL_SERVICE;
    }
    
    private String getVendorBillServiceURL(){
        return BASE_URL+VENDOR_BILL_SERVICES+GET_VENDOR_BILL_SERVICE;
    }
    
    private String getVendorBillByVendorIdServiceURL(){
        return BASE_URL+VENDOR_BILL_SERVICES+GET_VENDOR_BILL_BY_VENDOR_ID_SERVICE;
    }
    
    private String getUpdateVendorBillServiceURL(){
        return BASE_URL+VENDOR_BILL_SERVICES+UPDATE_VENDOR_BILL_SERVICE;
    }
    
    @Override
    public Integer add(Object object){
        WebResource resource=restClient.resource(getAddVendorBillServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,object);
        Integer billId=0;
        if(response.getStatus()==200){
            billId=response.getEntity(Integer.class);
        }
        return billId;
    }   
    
    @Override
    public List<Object> get(){
        List<Object> entityList=Collections.EMPTY_LIST;
        WebResource webResource=restClient.resource(getVendorBillServiceURL());
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
        WebResource webResource=restClient.resource(getVendorBillByVendorIdServiceURL()+customerId);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            customer=clientResponse.getEntity(Customer.class);
        }
        return customer;
    }
    
    public List<VendorBill> getByVendorID(Integer vendorID){
        List<VendorBill> vendorBills=null;
        WebResource webResource=restClient.resource(getVendorBillByVendorIdServiceURL()+vendorID);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse.getStatus()==200){
            String vendorBillsListJSONString=clientResponse.getEntity(String.class);
            ObjectMapper objectMapper=new ObjectMapper();
            try{
                vendorBills=objectMapper.readValue(vendorBillsListJSONString, TypeFactory.defaultInstance().constructCollectionType(List.class, VendorBill.class));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return vendorBills;
    }
        
    @Override
    public boolean update(Object entity){
        boolean isUpdated=false;
        WebResource resource=restClient.resource(getUpdateVendorBillServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,entity);
        if(response.getStatus()==200){
            isUpdated=true;
        }
        return isUpdated;
    }
}
