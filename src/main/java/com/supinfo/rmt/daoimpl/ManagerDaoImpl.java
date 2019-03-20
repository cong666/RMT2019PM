/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.daoimpl;

import com.supinfo.rmt.dao.ManagerDao;
import javax.ejb.Stateless;
import com.supinfo.rmt.entity.Manager;

/**
 *
 * @author ccong
 */
@Stateless
public class ManagerDaoImpl extends EntityManagerTemplate<Manager> implements ManagerDao {
    
    public ManagerDaoImpl() {
        super(Manager.class);
    }
    
}
