package br.tec.didiproject.queueserviceapi.entities;


import br.tec.didiproject.queueserviceapi.enums.Perfil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "usuario"
        , schema = "public"
)
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nomeUsuario", nullable = false, unique = true)
    private String nomeUsuario;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    @Enumerated(EnumType.STRING)
    private Set<Perfil> perfis = new HashSet<>();

    @OneToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "atendenteId")
    private Atendente atendente;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", perfis=" + perfis +
                ", atendente=" + atendente +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return perfis;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return nomeUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return ativo && Objects.nonNull(atendente);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return isAccountNonLocked();
    }
}
