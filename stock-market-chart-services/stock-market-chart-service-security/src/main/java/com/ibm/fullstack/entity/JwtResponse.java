package com.ibm.fullstack.entity;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = -3109062394161728590L;

    private String jwtToken;

    public JwtResponse(String jwtToken){
        this.jwtToken = jwtToken;
    }

    public String getToken(){
        return this.getToken();
    }
}
