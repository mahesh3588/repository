/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.sun.jersey.api.client.Client;
import java.util.List;

/**
 *
 * @author Owner
 */
public abstract class RestClient {
    protected Client restClient;
    protected String BASE_URL="http://localhost:8080/SaiShwetServices/backend";
    //"http://localhost:8080/SaiShwetServices/backend/customerServices/add"
    
   
   
    
    public RestClient() {
        restClient=Client.create();
       // baseUrlRest="http://localhost:8080/SaiShwetServices/backend";
    }
    
    public abstract Integer add(Object object);
    
    public abstract List<Object> get();
    
    public abstract Object getByID(Integer entityId);
    
    public abstract boolean update(Object entity);
}
