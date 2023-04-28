package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaUsuarioDTO;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Mapping(target = "id", ignore = true)
    Usuario toEntity(RequisicaoUsuarioDTO requisicaoUsuarioDTO);

    @Mapping(target = "id", expression = "java(usuario.getId().toString())")
    @Mapping(target = "atendenteId", source = "atendente.id")
    RespostaUsuarioDTO toResponseDTO(Usuario usuario);

    @Mapping(target = "id", expression = "java(usuario.getId().toString())")
    @Mapping(target = "atendenteId", source = "atendente.id")
    List<RespostaUsuarioDTO> toResponseDTOList(List<Usuario> usuario);
}
