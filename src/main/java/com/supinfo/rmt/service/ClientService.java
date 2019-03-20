/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.service;

import com.supinfo.rmt.dao.ClientDao;
import com.supinfo.rmt.entity.Client;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author ccong
 */
@Stateless
@LocalBean
public class ClientService implements Serializable {

    private ClientDao clientDao;

    @EJB
    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> findAllClients() {
        return clientDao.findAll();
    }

    public int deleteClient(Client client) {
        return clientDao.delete(client);
    }
    
    public void createClient(Client client) {
        clientDao.create(client);
    }
    
    public void updateClient(Client client) {
        clientDao.update(client);
    }
    
    public Client findByName(String name) {
        List<Client> clients = clientDao.findWhereEquals("name", name);
        if(clients!=null && !clients.isEmpty()) {
            return clients.get(0);
        }
        return new Client();
    }
    
    public int countWithMultiFilter(Set<Map.Entry<List<String>, Object>> entries) {
        return clientDao.countWithMultiFilter(entries);
    }
    
    public List<Client> findRangeWithMultiAttributeFilterAndSort(Set<Map.Entry<List<String>, Object>> entries, List<String> sortKeys, boolean desc, int first, int size) {
        return clientDao.findRangeWithMultiAttributeFilterAndSort(entries, sortKeys, desc, first, size);
    }
}
