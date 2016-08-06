/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.business;

import javax.ejb.Stateless;

/**
 *
 * @author BP
 */
@Stateless
public class UserManager {
    
    private static final String INSERT_USER = "insert u from authdb.users";
    
    public int registerUser(String username, String password){
        
        
        return 0;
    }
    
}
