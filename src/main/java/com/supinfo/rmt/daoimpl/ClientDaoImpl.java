/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.daoimpl;

import com.supinfo.rmt.dao.ClientDao;
import com.supinfo.rmt.entity.Client;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author ccong
 */
@Stateless
public class ClientDaoImpl extends EntityManagerTemplate<Client> implements ClientDao {
    
    public ClientDaoImpl() {
        super(Client.class);
    }
    
}
