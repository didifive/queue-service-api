package br.tec.fivedti.queueserviceapi.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "queue", schema = "public")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Queue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "lastNumber", nullable = false)
    private int lastNumber;

    @OneToOne
    private Company company;

    @Override
    public String toString() {
        return "Queue [id=" + id
                + ", name=" + name
                + ", abbreviation=" + name
                + ", lastNumber=" + lastNumber
                + ", company=" + company
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Queue queue = (Queue) o;
        return id == queue.id && lastNumber == queue.lastNumber && name.equals(queue.name) && abbreviation.equals(queue.abbreviation) && company.equals(queue.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, abbreviation, lastNumber, company);
    }
}