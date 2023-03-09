package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoEmpresaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaDepartamentoDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaEmpresaDTO;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Empresa;
import br.tec.didiproject.queueserviceapi.services.EmpresaService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartamentoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "empresa"
            , expression = "java(new Empresa(java.util.UUID.fromString(requisicaoDepartamentoDTO.getEmpresaId()),null,null,null))")
    Departamento toEntity(RequisicaoDepartamentoDTO requisicaoDepartamentoDTO);

    @Mapping(target = "id", expression = "java(departamento.getId().toString())")
    @Mapping(target = "empresaId", source = "empresa.id")
    RespostaDepartamentoDTO toResponseDTO(Departamento departamento);

    @Mapping(target = "id", expression = "java(departamento.getId().toString())")
    @Mapping(target = "empresaId", source = "empresa.id")
    List<RespostaDepartamentoDTO> toResponseDTOList(List<Departamento> departamentos);
}
