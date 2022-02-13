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
            return createMessageResponse("Company already extant with ID ", extantCompany.getId());

        if (!newCompany.isDeactivated())
            newCompany.setDeactivated(false);

        Company savedCompany = companyRepository.save(newCompany);

        return createMessageResponse("Company successfully created with ID ", savedCompany.getId());
    }

    public List<CompanyDto> listAllCompanies() {
        List<Company> companies = companyRepository.findAll();
        return companies.stream()
                .map(companyMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CompanyDto findById(Long id) throws CompanyNotFoundException {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
        return companyMapper.toDTO(company);
    }


    public MessageResponseDto update(Long id, CompanyDto companyDto) throws CompanyNotFoundException {
        companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        Company updatedCompany = companyMapper.toModel(companyDto);
        Company savedCompany = companyRepository.save(updatedCompany);

        return createMessageResponse("Company successfully updated with ID ", savedCompany.getId());
    }

    public void delete(Long id) throws CompanyNotFoundException {
        companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));

        companyRepository.deleteById(id);
    }

    private MessageResponseDto createMessageResponse(String s, Long id2) {
        return MessageResponseDto.builder()
                .message(s + id2)
                .build();
    }
}

