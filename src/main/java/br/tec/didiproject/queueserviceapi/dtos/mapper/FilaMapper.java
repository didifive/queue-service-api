package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoFilaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaFilaDTO;
import br.tec.didiproject.queueserviceapi.entities.Fila;
import br.tec.didiproject.queueserviceapi.entities.TipoAtendimento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;

@Mapper(componentModel = "spring")
public interface FilaMapper {

    @Named("idsToTiposAtendimento")
    static Set<TipoAtendimento> idsToTiposAtendimento(List<String> tiposAtendimentoId) {
        Set<TipoAtendimento> tiposAtendimento = new HashSet<>();
        for (String tipoAtendimentoId : tiposAtendimentoId)
            tiposAtendimento.add(new TipoAtendimento(UUID.fromString(tipoAtendimentoId), null, null, null));
        return tiposAtendimento;
    }

    @Named("tiposAtendimentoToIds")
    static List<String> tiposAtendimentoToIds(Set<TipoAtendimento> tiposAtendimento) {
        List<String> tiposAtendimentoIds = new ArrayList<>();
        for (TipoAtendimento tipoAtendimento : tiposAtendimento)
            tiposAtendimentoIds.add(tipoAtendimento.getId().toString());
        return tiposAtendimentoIds;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "tiposAtendimentoId"
            , target = "tiposAtendimento"
            , qualifiedByName = "idsToTiposAtendimento")
    @Mapping(target = "departamento"
            , expression = "java(new Departamento(java.util.UUID.fromString(requisicaoFilaDTO.getDepartamentoId()),null,null))")
    Fila toEntity(RequisicaoFilaDTO requisicaoFilaDTO);

    @Mapping(target = "id", expression = "java(fila.getId().toString())")
    @Mapping(source = "tiposAtendimento"
            , target = "tiposAtendimentoId"
            , qualifiedByName = "tiposAtendimentoToIds")
    @Mapping(target = "departamentoId", source = "departamento.id")
    RespostaFilaDTO toResponseDTO(Fila fila);

    @Mapping(target = "id", expression = "java(fila.getId().toString())")
    @Mapping(source = "tiposAtendimento"
            , target = "tiposAtendimentoId"
            , qualifiedByName = "tiposAtendimentoToIds")
    List<RespostaFilaDTO> toResponseDTOList(List<Fila> filas);
}
