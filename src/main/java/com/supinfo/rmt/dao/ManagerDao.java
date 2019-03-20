/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.dao;

import com.supinfo.rmt.entity.Manager;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author ccong
 */
@Local
public interface ManagerDao {
    public void create(Manager user);
    
    public Manager find(Object id);
    
    public int delete(Manager user);
    
    public Manager update(Manager user);
    
    public List<Manager> findAll();
    
    public List<Manager> findWhereEquals(String key, Object value);
    
    //public List<Manager> findWhereEqualsMultiAttributes(Set<Map.Entry<SingularAttribute<Manager, String>,Object>> entries, boolean and);
    public List<Manager> findWhereEqualsMultiAttributes(Set<Map.Entry<String,Object>> entries, boolean and);
}
