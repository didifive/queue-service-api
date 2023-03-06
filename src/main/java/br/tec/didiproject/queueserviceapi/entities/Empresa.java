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
@Table(name = "empresa"
        , schema = "public"
)
public class Empresa {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpfCnpj")
    private String cpfCnpj;

    @Column(name = "endereco")
    private String endereco;

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nome='" + nome +
                ", cpfCnpj='" + cpfCnpj +
                ", endereco='" + endereco +
                '}';
    }
}
