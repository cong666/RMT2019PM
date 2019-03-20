/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.dao;

import com.supinfo.rmt.entity.Employee;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author ccong
 */
@Local
public interface EmployeeDao {
    public void create(Employee user);
    
    public Employee find(Object id);
    
    public int delete(Employee user);
    
    public Employee update(Employee user);
    
    public List<Employee> findAll();
    
    public List<Employee> findWhereEquals(String key, Object value);
    
    //public List<Employee> findWhereEqualsMultiAttributes(Set<Map.Entry<SingularAttribute<Employee, String>,Object>> entries, boolean and);
    public List<Employee> findWhereEqualsMultiAttributes(Set<Map.Entry<String,Object>> entries, boolean and);
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries);
    
    public List<Employee> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size);
}
