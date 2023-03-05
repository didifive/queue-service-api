package br.tec.didiproject.queueserviceapi.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Perfil implements GrantedAuthority {
    ADMIN,
    USUARIO,
    ATENDENTE;

    @Override
    public String getAuthority() {
        return name();
    }
}
