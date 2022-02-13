package br.tec.fivedti.queueserviceapi.dto;

import br.tec.fivedti.queueserviceapi.entities.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueDto implements Serializable {

    private long id;

    @NotNull(message = "The queue name is required!")
    private String name;

    @NotNull(message = "The queue abbreviation is required!")
    private String abbreviation;

    @NotNull(message = "The queue lastNumber is required!")
    private int lastNumber;

    @NotNull(message = "The queue company is required!")
    private CompanyDto company;
}
