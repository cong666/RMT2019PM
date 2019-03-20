/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supinfo.rmt.entity;

import com.supinfo.rmt.validation.RmtDateFormat;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

/**
 *
 * @author ccong
 */
@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class RmtUser implements Serializable {
    private static final Logger LOGGER = Logger.getLogger(RmtUser.class.getName());
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "Please enter the username!")
    private String username;
    @NotNull(message = "Please enter the password!")
    private String password;
    private String firstname;
    private String lastname;
    @NotNull(message = "Please enter the email!")
    private String email;
    
    @Temporal(value = TemporalType.DATE)
    @Past
    @NotNull
    private Date birdthday;
    
    /*
    @Transient
    @NotNull(message = "Please enter the birthday!")
    @RmtDateFormat(dateformat = "yyyy-mm-dd",message = "Please enter the birthday with good format!")
    private String birthdayString;
    */
         
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirdthday() {
        return birdthday;
    }

    public void setBirdthday(Date birdthday) {
        this.birdthday = birdthday;
    }

    /*
    public String getBirthdayString() {
        return birthdayString;
    }

    public void setBirthdayString(String birthdayString) {
        this.birthdayString = birthdayString;
        try {
            Date date = new SimpleDateFormat("yyyy-mm-dd").parse(this.birthdayString);
            this.setBirdthday(date);
        } catch (ParseException ex) {
            Logger.getLogger(RmtUser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RmtUser other = (RmtUser) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
}
