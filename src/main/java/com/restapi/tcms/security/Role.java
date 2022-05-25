package com.restapi.tcms.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_STAGIAIRE,
    ROLE_FORMATEUR,
    ROLE_ADMINISTRATEUR,
    ROLE_S_STAGIAIRE,
    ROLE_S_SCOLARITE;

    @Override
    public String getAuthority() {
        return name();
    }
}
