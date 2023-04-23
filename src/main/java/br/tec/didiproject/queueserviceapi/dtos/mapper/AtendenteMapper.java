package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AtendenteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "departamentosId"
            , target = "departamentos"
            , qualifiedByName = "idsToDepartamentos")
    Departamento toEntity(RequisicaoDepartamentoDTO requisicaoDepartamentoDTO);

    @Mapping(target = "id", expression = "java(departamento.getId().toString())")
    @Mapping(source = "departamentos"
            , target = "departamentosId"
            , qualifiedByName = "departamentosToIds")
    RespostaDepartamentoDTO toResponseDTO(Departamento departamento);

    @Mapping(target = "id", expression = "java(departamento.getId().toString())")
    @Mapping(source = "departamentos"
            , target = "departamentosId"
            , qualifiedByName = "departamentosToIds")
    List<RespostaDepartamentoDTO> toResponseDTOList(List<Departamento> departamentos);

    @Named("idsToDepartamentos")
    public static List<Departamento> idsToDepartamentos(List<String> departamentosId) {
        List<Departamento> departamentos = new ArrayList<>();
        for (String idDepartamento : departamentosId)
            departamentos.add(new Departamento(UUID.fromString(idDepartamento),null,null));
        return departamentos;
    }

    @Named("departamentosToIds")
    public static List<String> departamentosToIds(List<Departamento> departamentos) {
        List<String> departamentosString = new ArrayList<>();
        for (Departamento departamento : departamentos)
            departamentosString.add(departamento.getId().toString());
        return departamentosString;
    }
}
