package br.tec.didiproject.queueserviceapi.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
public class Usuario {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nomeUsuario", nullable = false)
    private String nomeUsuario;

    @Column(name = "senha", nullable = false)
    private String senha;

    @ManyToMany
    @JoinTable(
            name = "usuariosPerfis"
            , joinColumns = {@JoinColumn(name = "usuarioId")}
            , inverseJoinColumns = {@JoinColumn(name = "perfilId")}
    )
    private Set<Perfil> perfis = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "atendenteId")
    private Atendente atendente;

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nomeUsuario='" + nomeUsuario + '\'' +
                ", senha='" + senha + '\'' +
                ", perfis=" + perfis +
                ", atendente=" + atendente +
                '}';
    }
}
