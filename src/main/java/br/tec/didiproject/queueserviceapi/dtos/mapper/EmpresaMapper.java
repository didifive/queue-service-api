package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoEmpresaDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaEmpresaDTO;
import br.tec.didiproject.queueserviceapi.entities.Empresa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {

    @Mapping(target = "id", ignore = true)
    Empresa toEntity(RequisicaoEmpresaDTO requisicaoEmpresaDTO);

    @Mapping(target = "id", expression = "java(empresa.getId().toString())")
    RespostaEmpresaDTO toResponseDTO(Empresa empresa);

    @Mapping(target = "id", expression = "java(empresa.getId().toString())")
    List<RespostaEmpresaDTO> toResponseDTOList(List<Empresa> empresas);
}
