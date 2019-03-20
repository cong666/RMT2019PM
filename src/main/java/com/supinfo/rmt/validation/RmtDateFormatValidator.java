/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *
 * @author ccong
 */
public class RmtDateFormatValidator implements ConstraintValidator<RmtDateFormat, String>{

    private static final Logger LOGGER = Logger.getLogger(RmtDateFormatValidator.class.getName());
    private String dateformat;
    
    @Override
    public void initialize(RmtDateFormat constraintAnnotation) {
        dateformat = constraintAnnotation.dateformat();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null) {
            return true;
        }

        try {
            Date date = new SimpleDateFormat(dateformat).parse(value);
            return true;
        } catch (ParseException e) {
            System.out.println("*************************");
            LOGGER.log(Level.WARNING, "", e);
            return false;
        } catch (Exception e) {
            System.out.println("*************************");
            LOGGER.log(Level.WARNING, "", e);
            return false;
        }
    }    
}
