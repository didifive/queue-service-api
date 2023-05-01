package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoAtendenteDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaAtendenteDTO;
import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.*;

@Mapper(componentModel = "spring")
public interface AtendenteMapper {

    @Named("idsToDepartamentos")
    static Set<Departamento> idsToDepartamentos(List<String> departamentosId) {
        Set<Departamento> departamentos = new HashSet<>();
        for (String idDepartamento : departamentosId)
            departamentos.add(new Departamento(UUID.fromString(idDepartamento), null, null));
        return departamentos;
    }

    @Named("departamentosToIds")
    static List<String> departamentosToIds(Set<Departamento> departamentos) {
        List<String> departamentosString = new ArrayList<>();
        for (Departamento departamento : departamentos)
            departamentosString.add(departamento.getId().toString());
        return departamentosString;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "departamentosId"
            , target = "departamentos"
            , qualifiedByName = "idsToDepartamentos")
    Atendente toEntity(RequisicaoAtendenteDTO requisicaoDepartamentoDTO);

    @Mapping(target = "id", expression = "java(atendente.getId().toString())")
    @Mapping(source = "atendente.departamentos"
            , target = "departamentosId"
            , qualifiedByName = "departamentosToIds")
    @Mapping(target = "usuarioId", expression = "java(usuario.getId().toString())")
    RespostaAtendenteDTO toResponseDTO(Atendente atendente, Usuario usuario);

    @Mapping(target = "id", expression = "java(atendente.getId().toString())")
    @Mapping(source = "departamentos"
            , target = "departamentosId"
            , qualifiedByName = "departamentosToIds")
    List<RespostaAtendenteDTO> toResponseDTOList(List<Atendente> atendentes);
}
