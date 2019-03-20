/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.validation;

import com.supinfo.rmt.message.RmtMessageHandler;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
/**
 *
 * @author ccong
 */
@FacesValidator(value = "emailValidator")
public class EmailValidator implements Validator {
    private final Pattern pattern;

    public EmailValidator() {
        String emailPattern = "^[_A-Za-z0-9-]+(\\."
                + "[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(emailPattern);
    }

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Matcher matcher = pattern.matcher(value.toString());
        if(!matcher.matches()){
            String msg = "Email format error";
            RmtMessageHandler.displayMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
            throw new ValidatorException(new FacesMessage(msg));
        }
    }
}
