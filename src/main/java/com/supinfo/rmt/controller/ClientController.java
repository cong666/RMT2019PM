/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.controller;

import com.supinfo.rmt.datamodel.ClientDataModel;
import com.supinfo.rmt.entity.Client;
import com.supinfo.rmt.message.RmtMessageHandler;
import com.supinfo.rmt.service.ClientService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author ccong
 */
@Named
@ViewScoped
public class ClientController implements Serializable {
    
    private static Logger LOGGER = Logger.getLogger(ClientController.class.getName());

    private List<Client> clients = new ArrayList<>();
    
    private ClientDataModel clientDataModel;
    
    private Client newClient = new Client();
    
    @EJB
    private ClientService clientService;

    @PostConstruct
    public void init() {
        clientDataModel = new ClientDataModel(clientService);
    }

    public ClientDataModel getClientDataModel() {
        return clientDataModel;
    }

    public void setClientDataModel(ClientDataModel clientDataModel) {
        this.clientDataModel = clientDataModel;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public Client getNewClient() {
        return newClient;
    }

    public void setNewClient(Client newClient) {
        this.newClient = newClient;
    }
    
    public void updateClientList() {
        clients = clientService.findAllClients();
    }

    
    public void deleteClient(Client client) {
        int result = clientService.deleteClient(client);
        if(result == 1) {
            String message = "Client is deleted successfully";
            RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, message, message);
        } else {
            String message = "Error occurred";
            RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_ERROR, message, message);
        }
    }
    
    public String doCreateClient() {
        clientService.createClient(newClient);
        return "clients?faces-redirect=true";
    }
    
    public void onEdit(RowEditEvent event) {
        Client client = (Client) event.getObject();
        clientService.updateClient(client);
        String message = "Client is modified successfully";
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, message,message);
    }

    public void onCancel(RowEditEvent event) {
        RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_INFO, "Operation cancelled", "Operation cancelled");
    }

}
