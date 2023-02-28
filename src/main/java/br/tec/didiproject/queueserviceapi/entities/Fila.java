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
    @JoinColumn(name = "departamentoId", nullable = false)
    private Departamento departamento;

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
                ", departamento=" + departamento +
                ", tiposAtendimento=" + tiposAtendimento +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fila fila = (Fila) o;
        return Objects.equals(id, fila.id) && Objects.equals(nome, fila.nome) && Objects.equals(sigla, fila.sigla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sigla);
    }
}