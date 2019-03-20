/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.controller;

import com.supinfo.rmt.datamodel.WorkTimeDataModel;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.RmtUser;
import com.supinfo.rmt.entity.WorkTime;
import com.supinfo.rmt.message.RmtMessageHandler;
import com.supinfo.rmt.service.EmployeeService;
import com.supinfo.rmt.service.ManagerService;
import com.supinfo.rmt.service.SessionService;
import com.supinfo.rmt.service.WorkTimeService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author ccong
 */
@Named
@ViewScoped
public class EmployeeController implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class.getName());
    
    private Employee newEmployee = new Employee();
    
    private List<WorkTime> workTimes = new ArrayList<>();
    
    private WorkTimeDataModel wtDataModel;

    @EJB
    private ManagerService managerService;
    
    @EJB
    private EmployeeService employeeService;

    @EJB
    private SessionService sessionService;
    
    @EJB
    private WorkTimeService workTimeService;

    public WorkTimeDataModel getWtDataModel() {
        return wtDataModel;
    }

    public void setWtDataModel(WorkTimeDataModel wtDataModel) {
        this.wtDataModel = wtDataModel;
    }
    
    @PostConstruct
    public void init() {
        //updateWorkTimeList();
        wtDataModel = new WorkTimeDataModel(workTimeService);
    }
    
    public void updateWorkTimeList() {
        RmtUser user = sessionService.findUserFromSession();
        
        if (user instanceof Employee) {
            workTimes = employeeService.findAllWorkTimes((Employee) user);
        }
    }

    public Employee getNewEmployee() {
        return newEmployee;
    }

    public void setNewEmployee(Employee newEmployee) {
        this.newEmployee = newEmployee;
    }

    public List<WorkTime> getWorkTimes() {
        return workTimes;
    }

    public void setWorkTimes(List<WorkTime> workTimes) {
        this.workTimes = workTimes;
    }

    public String doCreateEmployee() {
        Manager manager = (Manager)sessionService.findUserFromSession();
        if(manager.getId() == null) {
            return "login?faces-redirect=true";
        }
        newEmployee.setManager(manager);
        employeeService.createEmployee(newEmployee);
        manager.getEmployees().add(newEmployee);
        managerService.updateManager(manager);
        return "manager_home?faces-redirect=true";
    }    
    
    public void onEdit(RowEditEvent event) {
        WorkTime wt = (WorkTime) event.getObject();
        workTimeService.updateWorkTime(wt);
        String message = "WorkTime is modified successfully";
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, message,message);
    }

    public void onCancel(RowEditEvent event) {
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, "Operation cancelled", "Operation cancelled");
    }
}
