/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.dao;

import com.supinfo.rmt.entity.RmtUser;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author ccong
 */
@Local
public interface RmtUserDao {
    public void create(RmtUser user);
    
    public RmtUser find(Object id);
    
    public int delete(RmtUser user);
    
    public RmtUser update(RmtUser user);
    
    public List<RmtUser> findAll();
    
    public List<RmtUser> findWhereEquals(String key, Object value);
    
    //public List<RmtUser> findWhereEqualsMultiAttributes(Set<Map.Entry<SingularAttribute<RmtUser, String>,Object>> entries, boolean and);
    public List<RmtUser> findWhereEqualsMultiAttributes(Set<Map.Entry<String,Object>> entries, boolean and);
}
