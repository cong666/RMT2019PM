/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.message;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author ccong
 */
public class RmtMessageHandler {
        
    public static ResourceBundle getRs() {
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle rs=ResourceBundle.getBundle("messages", locale);
        return rs;
    }
    
    public static void displayMessage(FacesMessage.Severity messageType,String summary,String detail) {
        String s;
        String d;
        try {
            s = getRs().getString(summary);
        } catch (MissingResourceException e) {
            s = summary;
        }        
        
        try {
            d = getRs().getString(detail);
        } catch (MissingResourceException e) {
            d = detail;
        }    
        FacesMessage msg = new FacesMessage(messageType,s,d);  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }
}
