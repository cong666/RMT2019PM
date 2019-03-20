/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.WorkTime;
import com.supinfo.rmt.service.ClientService;
import com.supinfo.rmt.service.EmployeeService;
import com.supinfo.rmt.service.ManagerService;
import com.supinfo.rmt.service.SessionService;
import com.supinfo.rmt.service.WorkTimeService;
import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author ccong
 */
@Named
@ViewScoped
public class WorkTimeController implements Serializable {
    
    private static final Logger LOGGER = Logger.getLogger(WorkTimeController.class.getName());
    
    private WorkTime newWorkTime = new WorkTime();
    
    private Client selectedClient = new Client();

    @EJB
    private ManagerService managerService;
    
    @EJB
    private EmployeeService employeeService;

    @EJB
    private SessionService sessionService;
    
    @EJB
    private ClientService clientService;
    @EJB
    private WorkTimeService workTimeService;

    public WorkTime getNewWorkTime() {
        return newWorkTime;
    }

    public void setNewWorkTime(WorkTime newWorkTime) {
        this.newWorkTime = newWorkTime;
    }

    public Client getSelectedClient() {
        return selectedClient;
    }

    public void setSelectedClient(Client selectedClient) {
        this.selectedClient = selectedClient;
    }

    public String doCreateWorkTime() {
        Employee employee = (Employee)sessionService.findUserFromSession();
        if(employee.getId() == null) {
            return "login?faces-redirect=true";
        }
        newWorkTime.setEmployee(employee);
        Client client = clientService.findByName(selectedClient.getName());
        newWorkTime.setClient(client);
        workTimeService.createWorkTime(newWorkTime);
        employee.getWorkTimes().add(newWorkTime);
        employeeService.updateEmployee(employee);
        return "employee_home?faces-redirect=true";
    }    
    
    public void deleteWorkTime(WorkTime workTime) {
        workTimeService.deleteWorkTime(workTime);
    }
}
