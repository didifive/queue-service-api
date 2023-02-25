package br.tec.didiproject.queueserviceapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "fila"
        , schema = "public"
        , uniqueConstraints=@UniqueConstraint(columnNames={"sigla"})
        , indexes = @Index(name = "siglaIndex", columnList = "sigla")
)
public class Fila {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sigla", nullable = false)
    private String sigla;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresaId", nullable = false)
    private Empresa empresa;

    @ManyToMany
    @JoinTable(
            name = "filasTiposAtendimento"
            , joinColumns = {@JoinColumn(name = "filaId")}
            , inverseJoinColumns = {@JoinColumn(name = "tipoAtendimentoId")}
    )
    private Set<TipoAtendimento> tiposAtendimento = new HashSet<>();

    @Override
    public String toString() {
        return "Fila{" +
                "id=" + id +
                ", nome='" + nome +
                ", sigla='" + sigla +
                ", empresa=" + empresa +
                ", tiposAtendimento=" + tiposAtendimento +
                '}';
    }
}