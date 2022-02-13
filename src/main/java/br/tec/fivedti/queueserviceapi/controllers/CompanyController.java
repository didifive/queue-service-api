package br.tec.fivedti.queueserviceapi.controllers;

import br.tec.fivedti.queueserviceapi.controllers.docs.CompanyControllerDocs;
import br.tec.fivedti.queueserviceapi.dto.request.CompanyDto;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyNotFoundException;
import br.tec.fivedti.queueserviceapi.services.CompanyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/company")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyController implements CompanyControllerDocs {

    private final CompanyService companyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDto createCompany(@RequestBody @Valid CompanyDto companyDto) {
        return companyService.createCompany(companyDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CompanyDto> listCompanies() {
        return companyService.listAllCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CompanyDto findById(@PathVariable Long id) throws CompanyNotFoundException {
        return companyService.findById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDto update(@PathVariable Long id
            , @RequestBody @Valid CompanyDto companyDto
    ) throws CompanyNotFoundException {
        return companyService.update(id, companyDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) throws CompanyNotFoundException {
        companyService.delete(id);
    }
}
