/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.billing;

import beans.Order;
import beans.Quotation;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;
import services.RestClient;

/**
 *
 * @author Owner
 */
public class BillingClient extends RestClient{
    
    private static final String BILLING_SERVICES="/billingService";
    private static final String GENERATE_BILL_SERVICE="/placeOrder";
    
    private static final String GENERATE_QUOTATION_SERVICE = "/placeQuotation";
    private static final String GET_QUOTATION_SERVICE = "/getQuotation?quotationId=";
   // private static final String GET_ITEM_BY_ID_SERVICE="/getByID?itemId=";
   // private static final String UPDATE_ITEM_SERVICE="/update";

    
    private String getGenerateBillServiceURL(){
        return BASE_URL+BILLING_SERVICES+GENERATE_BILL_SERVICE;
    }
    
    private String getGenerateQuotationServiceURL(){
        return BASE_URL+BILLING_SERVICES+GENERATE_QUOTATION_SERVICE;
    }
    
    private String getGetQuotationServiceURL(){
        return BASE_URL+BILLING_SERVICES+GET_QUOTATION_SERVICE;
    }
    @Override
    public Integer add(Object object) {
        WebResource resource=restClient.resource(getGenerateBillServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,object);
        Integer invoiceNumber=0;
        System.out.println("status = "+response.getStatus());
        if(response.getStatus()==200){
            invoiceNumber=response.getEntity(Integer.class);
        }
        return invoiceNumber;
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public Integer addQuotation(Object object) {
        WebResource resource=restClient.resource(getGenerateQuotationServiceURL());
        ClientResponse response=resource.type(MediaType.APPLICATION_JSON).post(ClientResponse.class,object);
        Integer invoiceNumber=0;
        if(response.getStatus()==200){
            invoiceNumber=response.getEntity(Integer.class);
        }
        return invoiceNumber;
    }

    public Quotation getQuotation(Integer quotationId){
        Quotation quotation=null;
        WebResource webResource=restClient.resource(getGetQuotationServiceURL()+quotationId);
        ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        System.out.println("status : "+clientResponse.getStatus());
        if(clientResponse.getStatus()==200){
            quotation=clientResponse.getEntity(Quotation.class);
        }
        return quotation;
    }
 
    @Override
    public List<Object> get() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object getByID(Integer entityId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Integer generateBill(Order order){
        return add(order);
    }

    public Integer generateQuotation(Order order){
       return addQuotation(order);
    }

   
}
