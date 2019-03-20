/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.datamodel;

import com.supinfo.rmt.dao.EmployeeDao;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.service.EmployeeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author ccong
 */
public class EmployeeDataModel extends LazyDataModel<Employee> {
    
    private List<Employee> dataList;
    private EmployeeService employeeService;
    
    public EmployeeDataModel() {}
    
    public EmployeeDataModel(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    @Override
    public Employee getRowData(String rowKey) {
        Employee emp = null;
        for (Employee e : dataList) {
            if (Long.toString(e.getId()).equals(rowKey)) {
                emp = e;
            }
        }
        return emp;
    }

    @Override
    public Object getRowKey(Employee t) {
        return t.getId();
    }

    @Override
    public void setRowIndex(int rowIndex) {
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    /**
     * 
     * @param first
     * @param pageSize :how many employees we will display for every page
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return 
     */
    @Override 
    public List<Employee> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        
        System.out.println("load method is called : "+first+","+pageSize);
        List<String> sortKeys = sortField!=null && !sortField.isEmpty()?Arrays.asList(sortField.split("\\.")):new ArrayList<>();
        
        Map<List<String>,Object> filterMap = new HashMap<>();
        
        filters.forEach((k,v)->{
           List<String> keys = Arrays.asList(k.split("\\."));
           filterMap.put(keys, v);
        });
        
        
        this.setPageSize(pageSize);
        dataList = employeeService.findRangeWithMultiAttributeFilterAndSort(filterMap.entrySet(),sortKeys,sortOrder.equals(SortOrder.DESCENDING),first,pageSize);
        if(dataList==null) {
            dataList = new ArrayList<>();
        }
        this.setRowCount(employeeService.countWithMultiFilter(filterMap.entrySet()));
        return dataList;
    }
    
}
