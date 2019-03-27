/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.controller;

import com.supinfo.rmt.entity.Employee;
import com.supinfo.rmt.entity.Manager;
import com.supinfo.rmt.entity.RmtUser;
import com.supinfo.rmt.service.RmtUserService;
import com.supinfo.rmt.service.SessionService;
import com.supinfo.rmt.message.RmtMessageHandler;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author ccong
 */
@Named
@SessionScoped
public class RmtUserController implements Serializable {

    private RmtUser user = new RmtUser();

    @Inject
    private RmtUserService rmtUserService;

    @Inject
    private SessionService sessionService;

    public RmtUser getUser() {
        return user;
    }

    public void setUser(RmtUser user) {
        this.user = user;
    }

    public void doLogin() {
        if (user.getUsername() == null || user.getPassword() == null) {
            return;
        }

        user = rmtUserService.authenticate(user);
        if (user.getId() != null) {
            try {
                if (user instanceof Employee) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LOGIN_EMPLOYEE", user);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("authentication?userType=EMPLOYEE");
                } else if (user instanceof Manager) {
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("LOGIN_MANAGER", user);
                    FacesContext.getCurrentInstance().getExternalContext().redirect("authentication?userType=MANAGER");
                }
            } catch (IOException ex) {
                Logger.getLogger(RmtUserController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String errorMessage = "Username or password is not correct";
            RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
        }
    }

    public RmtUser getLoginUser() {
        return sessionService.findUserFromSession();
    }

    public void doLogout() {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("login");
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        try {
            ec.redirect(ec.getApplicationContextPath() + "/login.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(RmtUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
