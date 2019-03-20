/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.deploy;

import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.service.ManagerService;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ccong
 */
@Startup
@Singleton
public class DataInit implements Serializable {
    
    @EJB
    private ManagerService managerService;
    
    @PostConstruct
    public void init() {
        
        List<Manager> managers = managerService.findByUserName("admin");
        if(managers!=null && !managers.isEmpty()) {
            return;
        }
        
        Manager manager = new Manager();
        manager.setUsername("admin");
        try {
            manager.setBirdthday(new SimpleDateFormat("yyyy-mm-dd").parse("1992-01-01"));
        } catch (ParseException ex) {
            Logger.getLogger(DataInit.class.getName()).log(Level.SEVERE, null, ex);
        }
        manager.setPassword(DigestUtils.sha1Hex("admin"));
        manager.setFirstname("Administrator");
        manager.setLastname("Administrator");
        manager.setEmail("admin@gmail.com");
        managerService.createManager(manager);
    }
}
