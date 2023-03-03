package br.tec.didiproject.queueserviceapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "senha"
        , schema = "public"
)
public class Senha {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "numero", nullable = false)
    private Short numero;

    @JoinColumn(name = "filaId", nullable = false)
    @ManyToOne(optional = false)
    private Fila fila;

    @JoinColumn(name = "tipoAtendimentoId", nullable = false)
    @ManyToOne(optional = false)
    private TipoAtendimento tipoAtendimento;

    @CreationTimestamp
    @Column(name = "geradaEm", nullable = false)
    private Timestamp geradaEm;

    @Column(name = "finalizadaEm")
    private Timestamp finalizadaEm;

    @Column(name = "atendidaEm")
    private Timestamp atendidaEm;

    @ManyToOne
    @JoinColumn(name = "atendenteId")
    private Atendente atendente;

    @Override
    public String toString() {
        return "Senha{" +
                "id=" + id +
                ", numero=" + numero +
                ", fila=" + fila +
                ", tipoAtendimento=" + tipoAtendimento +
                ", geradaEm=" + geradaEm +
                ", finalizadaEm=" + finalizadaEm +
                ", atendidaEm=" + atendidaEm +
                ", atendente='" + atendente + '\'' +
                '}';
    }
}