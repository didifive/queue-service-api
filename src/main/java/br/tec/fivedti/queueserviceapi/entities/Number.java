package br.tec.fivedti.queueserviceapi.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "number", schema = "public")
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @CreationTimestamp
    @Column(name = "createTimestamp", nullable = false)
    private Timestamp createTimestamp;

    @Column(name = "attendedTimestamp")
    private Timestamp attendedTimestamp;

    @ManyToOne
    private Queue queue;

    @Override
    public String toString() {
        return "Number [id=" + id
                + ", number=" + number
                + ", createTimestamp=" + createTimestamp
                + ", attendedTimestamp=" + attendedTimestamp
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