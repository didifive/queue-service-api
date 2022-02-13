package br.tec.fivedti.queueserviceapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyDto implements Serializable {
    private long id;

    @NotNull(message = "The company name is required!")
    private String name;

    @NotNull(message = "The company CNPJ is required!")
    @CNPJ(message = "Please insert a valid CNPJ!")
    private String cnpj;
}
