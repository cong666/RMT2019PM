/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.controller;

import com.supinfo.rmt.datamodel.EmployeeDataModel;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.RmtUser;
import com.supinfo.rmt.message.RmtMessageHandler;
import com.supinfo.rmt.service.EmployeeService;
import com.supinfo.rmt.service.ManagerService;
import com.supinfo.rmt.service.SessionService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ccong
 */
@Named
@ViewScoped
public class ManagerController implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(ManagerController.class.getName());

    private List<Employee> employees = new ArrayList<>();
    
    private EmployeeDataModel employeeDataModel;

    private ManagerService managerService;
    
    private EmployeeService employeeService;

    private SessionService sessionService;

    @EJB
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @EJB
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @EJB
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public EmployeeDataModel getEmployeeDataModel() {
        return employeeDataModel;
    }

    public void setEmployeeDataModel(EmployeeDataModel employeeDataModel) {
        this.employeeDataModel = employeeDataModel;
    }

    @PostConstruct
    public void init() {
        //updateEmployeeList();
        employeeDataModel = new EmployeeDataModel(employeeService);
    }
    
    /**
     * Not used 
     */
    public void updateEmployeeList() {
        
        LOGGER.info("updateEmployeeList");
        RmtUser user = sessionService.findUserFromSession();
        
        if (user instanceof Manager) {
            employees = managerService.findAllEmployees((Manager) user);
        }
        LOGGER.info("employee list size : "+employees.size());
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
    
    public void deleteEmployee(Employee employee) {
        employeeService.deleteEmployee(employee);
        LOGGER.info("employee is deleted");
        updateEmployeeList();
        String message="Employee "+employee.getUsername()+ " is deleted successfully";
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, message, message);
    }
    
    public void onEdit(RowEditEvent event) {
        Employee emp = (Employee) event.getObject();
        employeeService.updateEmployee(emp);
        String message = "Employee is modified successfully";
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, message,message);
    }

    public void onCancel(RowEditEvent event) {
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, "Operation cancelled", "Operation cancelled");
    }

}
