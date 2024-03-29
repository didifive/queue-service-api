package br.tec.didiproject.queueserviceapi.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "tipoAtendimento"
        , schema = "public"
)
public class TipoAtendimento implements Comparable<TipoAtendimento> {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "sigla", unique = true, nullable = false)
    private String sigla;

    @Column(name = "prioridade", nullable = false)
    private Short prioridade;

    @Override
    public String toString() {
        return "TipoAtendimento{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sigla='" + sigla + '\'' +
                ", prioridade=" + prioridade +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TipoAtendimento that = (TipoAtendimento) o;
        return Objects.equals(id, that.id) && Objects.equals(nome, that.nome) && Objects.equals(sigla, that.sigla) && Objects.equals(prioridade, that.prioridade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, sigla, prioridade);
    }

    @Override
    public int compareTo(TipoAtendimento o) {
        return this.getPrioridade().compareTo(o.getPrioridade());
    }
}
