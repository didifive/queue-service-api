package br.tec.didiproject.queueserviceapi.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
    @LazyCollection(LazyCollectionOption.FALSE)
    private Fila fila;

    @JoinColumn(name = "tipoAtendimentoId")
    @ManyToOne
    @LazyCollection(LazyCollectionOption.FALSE)
    private TipoAtendimento tipoAtendimento;

    @CreationTimestamp
    @Column(name = "geradaEm", nullable = false)
    private Timestamp geradaEm;

    @Column(name = "chamadaEm")
    private Timestamp chamadaEm;

    @Column(name = "finalizadaEm")
    private Timestamp finalizadaEm;

    @Column(name = "motivoFinalizada")
    private String motivoFinalizada;

    @Column(name = "atendidaEm")
    private Timestamp atendidaEm;

    @ManyToOne
    @JoinColumn(name = "atendenteId")
    @LazyCollection(LazyCollectionOption.FALSE)
    private Atendente atendente;

    public boolean foiChamada() {
        if (this.getChamadaEm() != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public boolean foiAtendida() {
        if (this.getAtendidaEm() != null || this.getAtendente() != null)
            return Boolean.TRUE;
        return Boolean.FALSE;
    }

    public boolean foiFinalizada() {
        if (this.getFinalizadaEm() != null || !this.getMotivoFinalizada().isBlank())
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}