package br.tec.didiproject.queueserviceapi.dtos.mapper;

import br.tec.didiproject.queueserviceapi.dtos.request.RequisicaoUsuarioDTO;
import br.tec.didiproject.queueserviceapi.dtos.response.RespostaUsuarioDTO;
import br.tec.didiproject.queueserviceapi.entities.Atendente;
import br.tec.didiproject.queueserviceapi.entities.Departamento;
import br.tec.didiproject.queueserviceapi.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    @Named("atendenteIdParaAtendente")
    static Atendente atendenteIdParaAtendente(String atendenteId) {
        if (atendenteId != null)
            return new Atendente(UUID.fromString(atendenteId),null,null,new java.util.HashSet<>());
        return null;
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "atendente"
            , source = "atendenteId"
            , qualifiedByName = "atendenteIdParaAtendente")
    Usuario toEntity(RequisicaoUsuarioDTO requisicaoUsuarioDTO);

    @Mapping(target = "id", expression = "java(usuario.getId().toString())")
    @Mapping(target = "atendenteId", source = "atendente.id")
    RespostaUsuarioDTO toResponseDTO(Usuario usuario);

    @Mapping(target = "id", expression = "java(usuario.getId().toString())")
    @Mapping(target = "atendenteId", source = "atendente.id")
    List<RespostaUsuarioDTO> toResponseDTOList(List<Usuario> usuario);
}
