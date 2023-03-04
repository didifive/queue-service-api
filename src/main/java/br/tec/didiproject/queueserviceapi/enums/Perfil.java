package br.tec.didiproject.queueserviceapi.enums;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
public enum Perfil implements GrantedAuthority {
    ADMIN,
    USUARIO,
    ATENDENTE;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public String getAuthority() {
        return name();
    }
}
