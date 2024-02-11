package com.example.ecommerce_mobile_app.model.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ForgotPasswordRequest implements Serializable {

    private String email;

    public ForgotPasswordRequest() {
        this.email = email;
    }
}


