package br.tec.fivedti.queueserviceapi.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {
    private UUID id;

    @NotEmpty(message = "The company name is required!")
    private String name;

    @NotEmpty(message = "The company CNPJ is required!")
    @CNPJ(message = "Please insert a valid CNPJ!")
    private String cnpj;

    private boolean deactivated;
}
