package br.tec.didiproject.queueserviceapi.entities;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
public class Departamento {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @ManyToOne
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
