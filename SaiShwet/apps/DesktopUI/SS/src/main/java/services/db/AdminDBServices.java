/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services.db;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.util.List;
import javax.ws.rs.core.MediaType;
import services.RestClient;

/**
 *
 * @author Owner
 */
public class AdminDBServices extends RestClient{
    private static final String DB_SERVICES="/dbService";
    private static final String CREATE_BACKUP="/dbBackup";
    private static final String CLEAR_DATABASE="/deleteDb";

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
    
    public Boolean createBackup(){
        WebResource webResource=restClient.resource(BASE_URL+DB_SERVICES+CREATE_BACKUP);
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse!=null){
            if(clientResponse.getStatus()==200)
                return true;
        }
        return false;
    }
    
    public Boolean clearDatabase(){
        WebResource webResource=restClient.resource(BASE_URL+DB_SERVICES+CLEAR_DATABASE);
        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_JSON).get(ClientResponse.class);
        if(clientResponse!=null){
            if(clientResponse.getStatus()==200)
                return true;
        }
        return false;
    }
}
