/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.converter;

import com.supinfo.rmt.message.RmtMessageHandler;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author ccong
 */
@FacesConverter(value = "rmtTimeStampConverter")
public class RmtTimeStampConverter implements Converter {
    private static final Logger LOGGER = Logger.getLogger(RmtTimeStampConverter.class.getName());
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        LOGGER.info("getAsObject is called");
        String errorMessage = "Invalid Time Format";
        if(value == null || value.isEmpty()) {
            RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            throw new ConverterException("Please enter the time");
        }
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-mm-dd HH:MM:SS").parse(value);
        } catch (ParseException ex) {
            Logger.getLogger(RmtTimeStampConverter.class.getName()).log(Level.SEVERE, null, ex);
            RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_ERROR, errorMessage, errorMessage);
            throw new ConverterException(errorMessage);
        }
        return date;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return null;
    }
}
