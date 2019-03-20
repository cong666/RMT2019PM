/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.dao;

import com.supinfo.rmt.entity.WorkTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author ccong
 */
@Local
public interface WorkTimeDao {
    public void create(WorkTime workTime);
    
    public WorkTime find(Object id);
    
    public int delete(WorkTime workTime);
    
    public WorkTime update(WorkTime workTime);
    
    public List<WorkTime> findAll();
    
    public List<WorkTime> findWhereEquals(String key, Object value);
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries);
    
    public List<WorkTime> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size);
}
