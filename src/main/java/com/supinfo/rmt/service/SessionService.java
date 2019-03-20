/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.service;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.RmtUser;
import java.io.Serializable;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author ccong
 */
@Stateless
public class SessionService implements Serializable {
    
    public RmtUser findUserFromSession() {
        Object user = FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("login");
        if(user == null) {
            return new RmtUser();
        }
        if(user instanceof Manager) {
            return (Manager)user;
        } else if(user instanceof Employee) {
            return (Employee)user;
        }
        return new RmtUser();
    }
}
