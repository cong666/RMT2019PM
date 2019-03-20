/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.daoimpl;

import com.supinfo.rmt.dao.ClientDao;
import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.entity.RmtUser;
import java.util.List;
import javax.ejb.Stateless;
import com.supinfo.rmt.dao.RmtUserDao;
import java.util.Map;
import java.util.Set;
import javax.persistence.metamodel.SingularAttribute;

/**
 *
 * @author ccong
 */
@Stateless
public class RmtUserDaoImpl extends EntityManagerTemplate<RmtUser> implements RmtUserDao {
    
    public RmtUserDaoImpl() {
        super(RmtUser.class);
    }
    
}
