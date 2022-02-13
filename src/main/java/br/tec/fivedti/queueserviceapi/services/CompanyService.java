package br.tec.fivedti.queueserviceapi.services;

import br.tec.fivedti.queueserviceapi.dto.CompanyDto;
import br.tec.fivedti.queueserviceapi.dto.mapper.CompanyMapper;
import br.tec.fivedti.queueserviceapi.entities.Company;
import br.tec.fivedti.queueserviceapi.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper = CompanyMapper.INSTANCE;

    public CompanyDto createCompany(CompanyDto companyDto) {
        Company company = companyMapper.toModel(companyDto);
        Company savedCompany = companyRepository.save(company);
        return companyMapper.toDTO(savedCompany);
    }
}

