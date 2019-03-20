/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.service;

import com.supinfo.rmt.dao.EmployeeDao;
import com.supinfo.rmt.dao.WorkTimeDao;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.WorkTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ccong
 */
@Stateless
public class EmployeeService {
    
    @EJB
    private EmployeeDao employeeDao;
    
    private WorkTimeDao workTimeDao;

    @EJB
    public void setWorkTimeDao(WorkTimeDao workTimeDao) {
        this.workTimeDao = workTimeDao;
    }         
    
    public void createEmployee(Employee employee) {
        employeeDao.create(employee);
    }
    
    public void deleteEmployee(Employee employee) {
        employeeDao.delete(employee);
    }
    
    public void updateEmployee(Employee employee) {
        employeeDao.update(employee);
    }
    
    public List<WorkTime> findAllWorkTimes(Employee employee) {
        return workTimeDao.findWhereEquals("employee", employee);
    }
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries) {
        return employeeDao.countWithMultiFilter(entries);
    }
    
    public List<Employee> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size) {
        return employeeDao.findRangeWithMultiAttributeFilterAndSort(entries, sortKeys, desc, first, size);
    }
}
