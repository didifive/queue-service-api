package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoTipoAtendimentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaTipoAtendimentoDTO;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TipoAtendimentoMapper {

    @Mapping(target = "id", ignore = true)
    TipoAtendimento toEntity(RequisicaoTipoAtendimentoDTO requisicaoTipoAtendimentoDTO);

    @Mapping(target = "id", expression = "java(tipoAtendimento.getId().toString())")
    RespostaTipoAtendimentoDTO toResponseDTO(TipoAtendimento tipoAtendimento);

    @Mapping(target = "id", expression = "java(tipoAtendimento.getId().toString())")
    List<RespostaTipoAtendimentoDTO> toResponseDTOList(List<TipoAtendimento> tiposAtendimento);
}
