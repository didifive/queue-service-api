package br.tec.fivedti.queueserviceapi.controllers;

import br.tec.fivedti.queueserviceapi.dto.CompanyDto;
import br.tec.fivedti.queueserviceapi.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/company")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController implements CompanyControllerDocs {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyDto createCompany(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }
}
