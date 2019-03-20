/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.service;

import com.supinfo.rmt.dao.WorkTimeDao;
import com.supinfo.rmt.entity.WorkTime;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ccong
 */
@Stateless
@LocalBean
public class WorkTimeService implements Serializable {

    private WorkTimeDao workTimeDao;

    @EJB
    public void setWorkTimeDao(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }

    public WorkTimeDao getWorkTimeDao() {
        return workTimeDao;
    }

    public void deleteWorkTime(WorkTime wt) {
        workTimeDao.delete(wt);
    }

    public void createWorkTime(WorkTime wt) {
        workTimeDao.create(wt);
    }   
    
    public void updateWorkTime(WorkTime wt) {
        workTimeDao.update(wt);
    }
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries) {
        return workTimeDao.countWithMultiFilter(entries);
    }
    
    public List<WorkTime> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size) {
        return workTimeDao.findRangeWithMultiAttributeFilterAndSort(entries, sortKeys, desc, first, size);
    }
}
