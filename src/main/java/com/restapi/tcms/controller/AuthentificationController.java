package com.restapi.tcms.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.restapi.tcms.service.ServiceAuth;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthentificationController {
    private final ServiceAuth serviceAuth;

    public AuthentificationController(ServiceAuth serviceAuth) {
        this.serviceAuth = serviceAuth;
    }

    @GetMapping(path = "/userrole", produces = "application/json")
    public String getUserRole(){
        return "{\"authority\" : \""+serviceAuth.getAuthenticatedUserRole() +"\"}";
    }
}
