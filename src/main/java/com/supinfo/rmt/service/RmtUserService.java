/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.service;

import com.supinfo.rmt.dao.RmtUserDao;
import com.supinfo.rmt.entity.RmtUser;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ccong
 */
@Stateless
@LocalBean
public class RmtUserService implements Serializable {
    
    @EJB
    private RmtUserDao rmtUserDao;
    
    public RmtUser authenticate(RmtUser user) {
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("username", user.getUsername());
        queryParams.put("password", user.getPassword());
        List<RmtUser> users = rmtUserDao.findWhereEqualsMultiAttributes(queryParams.entrySet(), true);
        if (users==null || users.isEmpty()
                || users.size() > 1) {
            return new RmtUser();
        }
        return users.get(0);
    }
}
