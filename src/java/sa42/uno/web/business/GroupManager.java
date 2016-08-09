/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa42.uno.web.business;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sa42.uno.web.model.Groups;

/**
 *
 * @author BP
 */
@Stateless
public class GroupManager {
    
    @PersistenceContext private EntityManager em;
   
    public void addToGroup(String groupid, String userid) {
                Groups g = new Groups();
                g.setGroupId(groupid);
                g.setUserid(userid);
		em.persist(g);
		
		em.getEntityManagerFactory().getCache()
				.evict(Groups.class, g.getGroupId());
	}
}
