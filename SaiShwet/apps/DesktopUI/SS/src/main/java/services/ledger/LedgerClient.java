/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.ledger;

import beans.CustomerLedger;
import beans.Quotation;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;
import services.RestClient;

/**
 *
 * @author Owner
 */
public class LedgerClient extends RestClient{
    
    
    public CustomerLedger getCustomerLedger(Integer customerId){
        CustomerLedger customerLedger=null;
        try{
            //System.out.println(BASE_URL+"/ledgerServices/customerLedger?customerId="+customerId);
            WebResource webResource=restClient.resource(BASE_URL+"/ledgerServices/customerLedger?customerId="+customerId);
            ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
            System.out.println(clientResponse.getStatus());
            if(clientResponse.getStatus()==200){
                customerLedger=clientResponse.getEntity(CustomerLedger.class);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return customerLedger;
    }

    public List<Quotation> getQuotations(){
        List<Quotation> quotations=null;
        try{
            WebResource webResource=restClient.resource(BASE_URL+"/ledgerServices/getQuotations");
            ClientResponse clientResponse=webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
            if(clientResponse.getStatus()==200){
                String listJson=clientResponse.getEntity(String.class);
                ObjectMapper objectMapper=new ObjectMapper();
                try{
                    quotations=objectMapper.readValue(listJson, TypeFactory.defaultInstance().constructCollectionType(List.class, Quotation.class));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return quotations;
    }
    
    
    @Override
    public Integer add(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
