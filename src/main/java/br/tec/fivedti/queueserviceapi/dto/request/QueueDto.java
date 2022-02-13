package br.tec.fivedti.queueserviceapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QueueDto implements Serializable {

    private UUID id;

    @NotEmpty(message = "The queue name is required!")
    @Size(min = 2, max = 60)
    private String name;

    @NotEmpty(message = "The queue abbreviation is required!")
    @Size(min = 1, max = 5)
    private String abbreviation;

    @NotNull(message = "The queue lastNumber is required!")
    private int lastNumber;

    private boolean deactivated;

    private CompanyDto company;
}
