package br.tec.didiproject.queueserviceapi.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "departamento"
        , schema = "public"
)
public class Departamento implements Serializable {
    @Serial
    private static final long serialVersionUID = 1905122047L;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
    @LazyCollection(LazyCollectionOption.TRUE)
    @JoinColumn(name = "empresaId")
    private Empresa empresa;

    @Override
    public String toString() {
        return "Departamento [id=" + id
                + ", nome=" + nome
                + ", empresa=" + empresa
                + "]";
    }
}
