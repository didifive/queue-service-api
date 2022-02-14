package br.tec.fivedti.queueserviceapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "number", schema = "public")

public class Number implements Serializable {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "number", nullable = false)
    private int number;

    @CreationTimestamp
    @Column(name = "createTimestamp", nullable = false)
    private Timestamp createTimestamp;

    @Column(name = "attendedTimestamp")
    private Timestamp attendedTimestamp;

    @Column(name= "deactivated", nullable = false)
    private boolean deactivated;

    @JoinColumn(name = "queue_id", nullable = false)
    @ManyToOne(optional = false)
    private Queue queue;

    @Override
    public String toString() {
        return "Number [id=" + id
                + ", number=" + number
                + ", createTimestamp=" + createTimestamp
                + ", attendedTimestamp=" + attendedTimestamp
                + ", deactivated=" + deactivated
                + ", queue=" + queue
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Number number1 = (Number) o;
        return id == number1.id && number == number1.number && createTimestamp.equals(number1.createTimestamp) && attendedTimestamp.equals(number1.attendedTimestamp) && queue.equals(number1.queue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, createTimestamp, attendedTimestamp, queue);
    }
}