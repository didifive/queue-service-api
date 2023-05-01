package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.response.RespostaSenhaDTO;
import br.tec.didiproject.queueserviceapi.entities.Senha;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SenhaMapper {

    @Mapping(target = "id", expression = "java(senha.getId().toString())")
    @Mapping(target = "filaId", source = "fila.id")
    @Mapping(target = "tipoAtendimentoId", source = "tipoAtendimento.id")
    @Mapping(target = "atendenteId", source = "atendente.id")
    @Mapping(target = "foiAtendida", expression = "java(senha.foiAtendida())")
    @Mapping(target = "foiChamada", expression = "java(senha.foiChamada())")
    @Mapping(target = "foiFinalizada", expression = "java(senha.foiFinalizada())")
    RespostaSenhaDTO toResponseDTO(Senha senha);

    @Mapping(target = "id", expression = "java(senha.getId().toString())")
    @Mapping(target = "filaId", source = "fila.id")
    @Mapping(target = "tipoAtendimentoId", source = "tipoAtendimento.id")
    @Mapping(target = "atendenteId", source = "atendente.id")
    @Mapping(target = "foiAtendida", expression = "java(senha.foiAtendida())")
    @Mapping(target = "foiChamada", expression = "java(senha.foiChamada())")
    @Mapping(target = "foiFinalizada", expression = "java(senha.foiFinalizada())")
    List<RespostaSenhaDTO> toResponseDTOList(List<Senha> senha);
}
