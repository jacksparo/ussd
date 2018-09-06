/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ussd.model;

/**
 *
 * @author jgitundu
 */
public class UssdResponse {

    private String Type;
    private String Message;
    private String ClientState;

    public UssdResponse() {
    }

    public String getType() {
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public String getClientState() {
        return ClientState;
    }

    public void setClientState(String ClientState) {
        this.ClientState = ClientState;
    }

}
