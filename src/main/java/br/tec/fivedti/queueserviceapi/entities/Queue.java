package br.tec.fivedti.queueserviceapi.entities;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "queue"
        , schema = "public"
        , uniqueConstraints=@UniqueConstraint(columnNames={"abbreviation"})
        , indexes = @Index(name = "abbreviationIndex", columnList = "abbreviation")
)
public class Queue {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "abbreviation", nullable = false)
    private String abbreviation;

    @Column(name = "lastNumber", nullable = false)
    private int lastNumber;

    @Column(name= "deactivated")
    private boolean deactivated;

    @JoinColumn(name = "company_id", nullable = false)
    @ManyToOne(optional = false)
    private Company company;

    @Override
    public String toString() {
        return "Queue [id=" + id
                + ", name=" + name
                + ", abbreviation=" + name
                + ", lastNumber=" + lastNumber
                + ", deactivated=" + deactivated
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