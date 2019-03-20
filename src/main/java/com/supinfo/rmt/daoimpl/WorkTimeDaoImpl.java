/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.daoimpl;

import javax.ejb.Stateless;
import com.supinfo.rmt.dao.WorkTimeDao;
import com.supinfo.rmt.entity.WorkTime;

/**
 *
 * @author ccong
 */
@Stateless
public class WorkTimeDaoImpl extends EntityManagerTemplate<WorkTime> implements WorkTimeDao {
    
    public WorkTimeDaoImpl() {
        super(WorkTime.class);
    }
    
}
