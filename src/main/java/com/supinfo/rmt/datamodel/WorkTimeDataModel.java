/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.datamodel;

import com.supinfo.rmt.entity.WorkTime;
import com.supinfo.rmt.service.EmployeeService;
import com.supinfo.rmt.service.WorkTimeService;
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
public class WorkTimeDataModel extends LazyDataModel<WorkTime> {
    
    private List<WorkTime> dataList;
    private WorkTimeService workTimeService;
    
    public WorkTimeDataModel() {}
    
    public WorkTimeDataModel(WorkTimeService workTimeService) {
        this.workTimeService = workTimeService;
    }
    
    @Override
    public WorkTime getRowData(String rowKey) {
        WorkTime wt = null;
        for (WorkTime e : dataList) {
            if (Long.toString(e.getId()).equals(rowKey)) {
                wt = e;
            }
        }
        return wt;
    }

    @Override
    public Object getRowKey(WorkTime t) {
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
     * @param pageSize :how many worktimes we will display for every page
     * @param sortField
     * @param sortOrder
     * @param filters
     * @return 
     */
    @Override 
    public List<WorkTime> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        
        System.out.println("load method is called : "+first+","+pageSize);
        List<String> sortKeys = sortField!=null && !sortField.isEmpty()?Arrays.asList(sortField.split("\\.")):new ArrayList<>();
        
        Map<List<String>,Object> filterMap = new HashMap<>();
        
        filters.forEach((k,v)->{
           List<String> keys = Arrays.asList(k.split("\\."));
           filterMap.put(keys, v);
        });
        
        
        this.setPageSize(pageSize);
        dataList = workTimeService.findRangeWithMultiAttributeFilterAndSort(filterMap.entrySet(),sortKeys,sortOrder.equals(SortOrder.DESCENDING),first,pageSize);
        if(dataList==null) {
            dataList = new ArrayList<>();
        }
        this.setRowCount(workTimeService.countWithMultiFilter(filterMap.entrySet()));
        return dataList;
    }
    
}
