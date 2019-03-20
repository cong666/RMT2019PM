/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.daoimpl;

import com.supinfo.rmt.dao.EmployeeDao;
import javax.ejb.Stateless;
import com.supinfo.rmt.entity.Employee;

/**
 *
 * @author ccong
 */
@Stateless
public class EmployeeDaoImpl extends EntityManagerTemplate<Employee> implements EmployeeDao {
    
    public EmployeeDaoImpl() {
        super(Employee.class);
    }
    
}
