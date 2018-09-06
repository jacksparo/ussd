/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ussd.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jgitundu
 */
@Entity
@Table(catalog = "ussd", schema = "public")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findBySessionId", query = "SELECT r FROM Request r WHERE r.sessionId = :sessionId")
    , @NamedQuery(name = "Request.findByRequest", query = "SELECT r FROM Request r WHERE r.input = :input")
    , @NamedQuery(name = "Request.findByPhoneNumber", query = "SELECT r FROM Request r WHERE r.phoneNumber = :phoneNumber")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
   
    @Id
    @Size(max = 200)
    @Column(name = "session_id", length = 200)
    private String sessionId;
    @Size(max = 1000)
    @Column(length = 1000)
    private String input;
    @Size(max = 25)
    @Column(name = "phone_number", length = 25)
    private String phoneNumber;

    public Request() {
    }

    public Request(String sessionId, String input, String phoneNumber) {
        this.sessionId = sessionId;
        this.input = input;
        this.phoneNumber = phoneNumber;
    }
    
    

  
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }



    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.sessionId);
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
        final Request other = (Request) obj;
        if (!Objects.equals(this.sessionId, other.sessionId)) {
            return false;
        }
        return true;
    }

 
    
}
