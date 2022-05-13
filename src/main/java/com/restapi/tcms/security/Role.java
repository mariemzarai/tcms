package com.restapi.tcms.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STAGIAIRE, ROLE_FORMATEUR, ROLE_ADMINISTRATEUR;

    @Override
    public String getAuthority() {
        return name();
    }
}
