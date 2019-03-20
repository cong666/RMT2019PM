/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.service;

import com.supinfo.rmt.dao.EmployeeDao;
import com.supinfo.rmt.dao.ManagerDao;
import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ccong
 */
@Stateless
public class ManagerService {

    @EJB
    private EmployeeDao employeeDao;

    @EJB
    private ManagerDao managerDao;

    public List<Employee> findAllEmployees(Manager manager) {
        List<Employee> users = employeeDao.findWhereEquals("manager", manager);
        return users;
    }

    public void updateManager(Manager manager) {
        managerDao.update(manager);
    }

}
