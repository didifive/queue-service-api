package br.tec.fivedti.queueserviceapi.entities;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "company"
        , schema = "public"
        , uniqueConstraints=@UniqueConstraint(columnNames={"cnpj"})
        , indexes = @Index(name = "cnpjIndex", columnList = "cnpj")
)
public class Company {

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

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Column(name= "deactivated")
    private boolean deactivated;

    @Override
    public String toString() {
        return "Company [id=" + id
                + ", name=" + name
                + ", cnpj=" + cnpj
                + ", deactivated=" + deactivated
                + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return id == company.id && name.equals(company.name) && cnpj.equals(company.cnpj);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, cnpj);
    }
}
