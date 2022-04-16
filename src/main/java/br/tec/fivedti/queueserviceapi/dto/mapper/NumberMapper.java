package br.tec.fivedti.queueserviceapi.dto.mapper;

import br.tec.fivedti.queueserviceapi.dto.request.NumberDto;
import br.tec.fivedti.queueserviceapi.entities.NumberSequence;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NumberMapper {
    NumberMapper INSTANCE = Mappers.getMapper(NumberMapper.class);

    NumberSequence toModel(NumberDto numberDto);

    NumberDto toDTO(NumberSequence number);
}
