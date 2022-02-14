package br.tec.fivedti.queueserviceapi.dto.mapper;

import br.tec.fivedti.queueserviceapi.dto.request.NumberDto;
import br.tec.fivedti.queueserviceapi.entities.Number;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NumberMapper {
    NumberMapper INSTANCE = Mappers.getMapper(NumberMapper.class);

    Number toModel(NumberDto numberDto);

    NumberDto toDTO(Number number);
}
