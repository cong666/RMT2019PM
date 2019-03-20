/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.dao;

import com.supinfo.rmt.entity.Client;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author ccong
 */
@Local
public interface ClientDao {
    public void create(Client client);
    
    public Client find(Object id);
    
    public int delete(Client client);
    
    public Client update(Client client);
    
    public List<Client> findAll();
    
    public List<Client> findWhereEquals(String key, Object value);
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries);
    
    public List<Client> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size);
}
