package br.tec.fivedti.queueserviceapi.dto.mapper;

import br.tec.fivedti.queueserviceapi.dto.request.QueueDto;
import br.tec.fivedti.queueserviceapi.entities.QueueRow;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface QueueMapper {
    QueueMapper INSTANCE = Mappers.getMapper(QueueMapper.class);

    QueueRow toModel(QueueDto queueDto);

    QueueDto toDTO(QueueRow queue);
}
