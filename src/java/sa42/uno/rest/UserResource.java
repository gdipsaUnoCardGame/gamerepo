/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.rest;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author BP
 */
@RequestScoped
@Path("/user")
public class UserResource {
    
    @POST
    @Path("/register")
    @Produces("application/json")
    public Response register(){
        
        return null;
    }
    
    
    
}
