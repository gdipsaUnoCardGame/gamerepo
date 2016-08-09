/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author BP
 */
@NamedQueries({
	@NamedQuery(name = "Users.findByUserId", 
			query = "select u from Users u where u.userid = :userId"),	
})
@Entity
@Table(name="users")
public class Users implements Serializable{
    @Id
    @Column(name = "userid")
    private String username;
    
    
    private String password;
        
    @ManyToOne
    @JoinColumn(name="groups", referencedColumnName = "groupid")
    private Groups group;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
