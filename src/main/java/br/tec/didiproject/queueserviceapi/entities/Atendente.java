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
@Table(name = "atendente"
        , schema = "public"
)
public class Atendente {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToMany
    @JoinTable(
            name = "atendenteDepartamentos"
            , joinColumns = {@JoinColumn(name = "atendenteId")}
            , inverseJoinColumns = {@JoinColumn(name = "departamentoId")}
    )
    private Set<Departamento> departamentos = new HashSet<>();

    @Override
    public String toString() {
        return "Atendente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", departamentos=" + departamentos +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Atendente atendente = (Atendente) o;
        return Objects.equals(id, atendente.id) && Objects.equals(nome, atendente.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }
}