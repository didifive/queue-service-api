package br.tec.fivedti.queueserviceapi.dto.mapper;

import br.tec.fivedti.queueserviceapi.dto.CompanyDto;
import br.tec.fivedti.queueserviceapi.entities.Company;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CompanyMapper {
    CompanyMapper INSTANCE = Mappers.getMapper(CompanyMapper.class);

    Company toModel(CompanyDto companyDto);

    CompanyDto toDTO(Company beer);
}
