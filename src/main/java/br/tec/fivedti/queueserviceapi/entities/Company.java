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
@Table(name = "company", schema = "public")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cnpj", nullable = false)
    private String cnpj;

    @Override
    public String toString() {
        return "Company [id=" + id
                + ", name=" + name
                + ", cnpj=" + cnpj
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
