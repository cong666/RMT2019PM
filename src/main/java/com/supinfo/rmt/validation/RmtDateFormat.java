/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author ccong
 */
@Documented
@Constraint(validatedBy = {RmtDateFormatValidator.class})
@Target( {ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,
      ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR})
@Retention(RUNTIME)
public @interface RmtDateFormat {
    String message() default "{com.supinfo.rmt.validation.RmtDateFormat.message}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

    String dateformat();
}
