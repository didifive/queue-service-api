package br.tec.fivedti.queueserviceapi.services;

import br.tec.fivedti.queueserviceapi.dto.request.CompanyDto;
import br.tec.fivedti.queueserviceapi.dto.mapper.CompanyMapper;
import br.tec.fivedti.queueserviceapi.dto.response.MessageResponseDto;
import br.tec.fivedti.queueserviceapi.entities.Company;
import br.tec.fivedti.queueserviceapi.excepitions.CompanyNotFoundException;
import br.tec.fivedti.queueserviceapi.repositories.CompanyRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    public MessageResponseDto createCompany(CompanyDto companyDto) {
        Company newCompany = companyMapper.toModel(companyDto);
        Company extantCompany = companyRepository.findByCnpj(newCompany.getCnpj());

        if (!Objects.isNull(extantCompany))
            return createMessageResponse("Company already extant.", extantCompany);

        if (!newCompany.isDeactivated())
            newCompany.setDeactivated(false);

        Company savedCompany = companyRepository.save(newCompany);

        return createMessageResponse("Company successfully created.", savedCompany);
    }

    public List<CompanyDto> listAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDto findById(UUID id) throws CompanyNotFoundException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        return companyMapper.toDTO(company);
    }


    public MessageResponseDto update(UUID id, CompanyDto companyDto) throws CompanyNotFoundException {
        companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        Company updatedCompany = companyMapper.toModel(companyDto);
        Company savedCompany = companyRepository.save(updatedCompany);

        return createMessageResponse("Company successfully updated.", savedCompany);
    }

    public MessageResponseDto delete(UUID id) throws CompanyNotFoundException {
        Company deletedCompany = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        companyRepository.delete(deletedCompany);

        return createMessageResponse("Company successfully deleted.", deletedCompany);
    }

    private MessageResponseDto createMessageResponse(String s, Object o) {
        return MessageResponseDto.builder()
                .message(s)
                .payload(o)
                .build();
    }
}

