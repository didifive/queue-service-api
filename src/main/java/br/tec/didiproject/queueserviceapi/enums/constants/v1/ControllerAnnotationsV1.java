package br.tec.didiproject.queueserviceapi.enums.constants.v1;

public final class ControllerAnnotationsV1 {

    public static final String CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = "Quantidade de objetos por página";
    public static final String CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = "10";
    public static final String CONTROLLER_FIND_ALL_PARAMETER_PAGE = "O número da página que quer retornar";
    public static final String CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = "0";
    public static final String CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = "Nome do campo que deseja ordenar. "
            + "Seguido da direção de ordenação desejada \',asc|desc\' (ascendente/descendente). "
            + "A direção de ordenação padrão é ascendent. "
            + "Aceita ordenação múltipla. ";
    public static final String CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = "id,desc";

    public static final String CONTROLLER_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
    public static final String CONTROLLER_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
    public static final String CONTROLLER_404_DESCRIPTION = "Entidade não foi encontrada";
    public static final String CONTROLLER_409_DESCRIPTION = "Ocorreu erro com integridade de dado";

    /**
     * Constants for AuthControllerDocs
     */
    public static final String AUTH_CONTROLER_TAG = "1. Autenticação";
    public static final String AUTH_CONTROLER_AUTHENTICATE_OPERATION_SUMMARY = "Gerar Token de acesso";
    public static final String AUTH_CONTROLER_AUTHENTICATE_OPERATION_DESCRIPTION = "Operação para gerar token de acesso";
    public static final String AUTH_CONTROLER_AUTHENTICATE_200_DESCRIPTION = "Token criado com sucesso";
    public static final String AUTH_CONTROLER_AUTHENTICATE_401_DESCRIPTION = "Usuário ou senha inválido(s)";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_OPERATION_SUMMARY = "Atualizar Token de acesso";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_OPERATION_DESCRIPTION = "Operação para atualizar token de acesso";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_DESCRIPTION = "Id do usuário";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_EXAMPLE = "123e4567-e89b-12d3-a456-426655440000";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_REFRESH_TOKEN_DESCRIPTION = "Refresh token";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_REFRESH_TOKEN_EXAMPLE = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_EXAMPLE;
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_200_DESCRIPTION = AUTH_CONTROLER_AUTHENTICATE_200_DESCRIPTION;
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_403_DESCRIPTION = "Não foi possível concluir, operação proibida";
    public static final String AUTH_CONTROLER_REFRESH_TOKEN_404_DESCRIPTION = "Usuário ou refresh token não foi localizado";
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_OPERATION_SUMMARY = "Invalida refresh token do usuário";
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_OPERATION_DESCRIPTION = "Operação para invalidar refresh token do usuário";
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_PARAMETER_USUARIO_ID_DESCRIPTION = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_DESCRIPTION;
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_PARAMETER_USUARIO_ID_EXAMPLE = AUTH_CONTROLER_REFRESH_TOKEN_PARAMETER_USUARIO_ID_EXAMPLE;
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_204_DESCRIPTION = "Token invalidado com sucesso";
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_403_DESCRIPTION = AUTH_CONTROLER_REFRESH_TOKEN_403_DESCRIPTION;
    public static final String AUTH_CONTROLER_INVALIDA_REFRESH_TOKEN_404_DESCRIPTION = "Usuário não foi localizado";

    public static final String EMPRESA_CONTROLER_TAG = "2. Empresa";
    public static final String EMPRESA_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar empresa";
    public static final String EMPRESA_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar nova empresa";
    public static final String EMPRESA_CONTROLLER_CREATE_201_DESCRIPTION = "Nova empresa cadastrada com sucesso";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Empresas";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todas as Empresas cadastradas";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de empresas cadastradas";
    public static final String EMPRESA_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Empresa";
    public static final String EMPRESA_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar empresa conforme id informado";
    public static final String EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id da empresa";
    public static final String EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = "123e4567-e89b-12d3-a456-426655440000";
    public static final String EMPRESA_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe da empresa buscada";
    public static final String EMPRESA_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Empresa";
    public static final String EMPRESA_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização da empresa";
    public static final String EMPRESA_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String EMPRESA_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String EMPRESA_CONTROLLER_UPDATE_200_DESCRIPTION = "Empresa atualizada com sucesso";
    public static final String EMPRESA_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Empresa";
    public static final String EMPRESA_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão da empresa";
    public static final String EMPRESA_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String EMPRESA_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String EMPRESA_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Empresa excluída com sucesso";

    public static final String DEPARTAMENTO_CONTROLER_TAG = "3. Departamento";
    public static final String DEPARTAMENTO_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar departamento";
    public static final String DEPARTAMENTO_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar novo departamento";
    public static final String DEPARTAMENTO_CONTROLLER_CREATE_201_DESCRIPTION = "Novo departamento cadastrado com sucesso";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Departamentos";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os Departamentos cadastrados";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de departamentos cadastrados";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Departamento";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar departamento conforme id informado";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do departamento";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do departamento buscado";
    public static final String DEPARTAMENTO_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Departamento";
    public static final String DEPARTAMENTO_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização do departamento";
    public static final String DEPARTAMENTO_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String DEPARTAMENTO_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String DEPARTAMENTO_CONTROLLER_UPDATE_200_DESCRIPTION = "Departamento atualizado com sucesso";
    public static final String DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Departamento";
    public static final String DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão do departamento";
    public static final String DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String DEPARTAMENTO_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Departamento excluído com sucesso";

    public static final String ATENDENTE_CONTROLER_TAG = "4. Atendente";
    public static final String ATENDENTE_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar atendente";
    public static final String ATENDENTE_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar novo atendente";
    public static final String ATENDENTE_CONTROLLER_CREATE_201_DESCRIPTION = "Novo atendente cadastrado com sucesso";
    public static final String ATENDENTE_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Atendentes";
    public static final String ATENDENTE_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os Atendentes cadastrados";
    public static final String ATENDENTE_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de atendentes cadastrados";
    public static final String ATENDENTE_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Atendente";
    public static final String ATENDENTE_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar atendente conforme id informado";
    public static final String ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do atendente";
    public static final String ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String ATENDENTE_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do atendente buscado";
    public static final String ATENDENTE_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Atendente";
    public static final String ATENDENTE_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização do atendente";
    public static final String ATENDENTE_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String ATENDENTE_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String ATENDENTE_CONTROLLER_UPDATE_200_DESCRIPTION = "Atendente atualizado com sucesso";
    public static final String ATENDENTE_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Atendente";
    public static final String ATENDENTE_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão do atendente";
    public static final String ATENDENTE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String ATENDENTE_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = ATENDENTE_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String ATENDENTE_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Atendente excluído com sucesso";

    public static final String USUARIO_CONTROLER_TAG = "5. Usuário";
    public static final String USUARIO_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar usuário";
    public static final String USUARIO_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar novo usuário";
    public static final String USUARIO_CONTROLLER_CREATE_201_DESCRIPTION = "Novo usuário cadastrado com sucesso";
    public static final String USUARIO_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Usuários";
    public static final String USUARIO_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os Usuários cadastrados";
    public static final String USUARIO_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de usuários cadastrados";
    public static final String USUARIO_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Usuário por ID";
    public static final String USUARIO_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar usuário conforme id informado";
    public static final String USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do usuário";
    public static final String USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do usuário buscado";
    public static final String USUARIO_CONTROLLER_NOVO_NOME_USUARIO_OPERATION_SUMMARY = "Alterar nome de usuário";
    public static final String USUARIO_CONTROLLER_NOVO_NOME_USUARIO_OPERATION_DESCRIPTION = "Alterar nome de usuário";
    public static final String USUARIO_CONTROLLER_NOVO_NOME_USUARIO_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String USUARIO_CONTROLLER_NOVO_NOME_USUARIO_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_NOVO_NOME_USUARIO_200_DESCRIPTION = "Retorna detalhe do usuário com nome atualizado";
    public static final String USUARIO_CONTROLLER_ATUALIZAR_SENHA_OPERATION_SUMMARY = "Atualizar senha";
    public static final String USUARIO_CONTROLLER_ATUALIZAR_SENHA_OPERATION_DESCRIPTION = "Atualizar senha de usuário para uma nova informada";
    public static final String USUARIO_CONTROLLER_ATUALIZAR_SENHA_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String USUARIO_CONTROLLER_ATUALIZAR_SENHA_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_ATUALIZAR_SENHA_204_DESCRIPTION = "Senha do usuário alterada com sucesso";
    public static final String USUARIO_CONTROLLER_ADICIONAR_PERFIL_OPERATION_SUMMARY = "Adicionar perfil";
    public static final String USUARIO_CONTROLLER_ADICIONAR_PERFIL_OPERATION_DESCRIPTION = "Adicionar perfil ao usuário";
    public static final String USUARIO_CONTROLLER_ADICIONAR_PERFIL_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String USUARIO_CONTROLLER_ADICIONAR_PERFIL_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_ADICIONAR_PERFIL_200_DESCRIPTION = "Retorna detalhe do usuário com perfil adicionado";
    public static final String USUARIO_CONTROLLER_REMOVER_PERFIL_OPERATION_SUMMARY = "Remover perfil";
    public static final String USUARIO_CONTROLLER_REMOVER_PERFIL_OPERATION_DESCRIPTION = "Remover perfil do usuário";
    public static final String USUARIO_CONTROLLER_REMOVER_PERFIL_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String USUARIO_CONTROLLER_REMOVER_PERFIL_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_REMOVER_PERFIL_200_DESCRIPTION = "Retorna detalhe do usuário com perfil removido";
    public static final String USUARIO_CONTROLLER_ATIVAR_USUARIO_OPERATION_SUMMARY = "Ativar Usuário";
    public static final String USUARIO_CONTROLLER_ATIVAR_USUARIO_OPERATION_DESCRIPTION = "Modificar status do usuário para ativo";
    public static final String USUARIO_CONTROLLER_ATIVAR_USUARIO_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String USUARIO_CONTROLLER_ATIVAR_USUARIO_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_ATIVAR_USUARIO_200_DESCRIPTION = "Retorna detalhe do usuário ativado";
    public static final String USUARIO_CONTROLLER_DESATIVAR_USUARIO_OPERATION_SUMMARY = "Desativar Usuário";
    public static final String USUARIO_CONTROLLER_DESATIVAR_USUARIO_OPERATION_DESCRIPTION = "Modificar status do usuário para desativado";
    public static final String USUARIO_CONTROLLER_DESATIVAR_USUARIO_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String USUARIO_CONTROLLER_DESATIVAR_USUARIO_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String USUARIO_CONTROLLER_DESATIVAR_USUARIO_200_DESCRIPTION = "Retorna detalhe do usuário desativado";

    public static final String TIPO_ATENDIMENTO_CONTROLER_TAG = "6. Tipo Atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar novo tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_CREATE_201_DESCRIPTION = "Novo tipo de atendimento cadastrado com sucesso";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Tipos de Atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todos os Tipos de Atendimento cadastrados";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de tipos de atendimentos cadastrados";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Tipo de Atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar tipo de atendimento conforme id informado";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe do tipo de atendimento buscado";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Tipo de Atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização do tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String TIPO_ATENDIMENTO_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String TIPO_ATENDIMENTO_CONTROLLER_UPDATE_200_DESCRIPTION = "Tipo de atendimento atualizado com sucesso";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Tipo de Atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão do tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String TIPO_ATENDIMENTO_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String TIPO_ATENDIMENTO_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Tipo de atendimento excluído com sucesso";

    public static final String FILA_CONTROLER_TAG = "7. Fila";
    public static final String FILA_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar fila";
    public static final String FILA_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar nova fila";
    public static final String FILA_CONTROLLER_CREATE_201_DESCRIPTION = "Nova fila cadastrada com sucesso";
    public static final String FILA_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Filas";
    public static final String FILA_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todas as Filas cadastradas";
    public static final String FILA_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de filas cadastradas";
    public static final String FILA_CONTROLLER_FIND_ALL_BY_DEPARTMENT_ID_OPERATION_SUMMARY = "Listar Filas por Departamento";
    public static final String FILA_CONTROLLER_FIND_ALL_BY_DEPARTMENT_ID_OPERATION_DESCRIPTION = "Listar todas as Filas cadastradas por departamento";
    public static final String FILA_CONTROLLER_FIND_ALL_BY_DEPARTMENT_ID_200_DESCRIPTION = "Retorna lista paginada de filas cadastradas por departamento";
    public static final String FILA_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Fila";
    public static final String FILA_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar fila conforme id informado";
    public static final String FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id da fila";
    public static final String FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = EMPRESA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String FILA_CONTROLLER_FIND_BY_ID_200_DESCRIPTION = "Retorna detalhe da fila buscada";
    public static final String FILA_CONTROLLER_UPDATE_OPERATION_SUMMARY = "Atualizar Fila";
    public static final String FILA_CONTROLLER_UPDATE_OPERATION_DESCRIPTION = "Realizar atualização da fila";
    public static final String FILA_CONTROLLER_UPDATE_PARAMETER_ID_DESCRIPTION = FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String FILA_CONTROLLER_UPDATE_PARAMETER_ID_EXAMPLE = FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String FILA_CONTROLLER_UPDATE_200_DESCRIPTION = "Fila atualizada com sucesso";
    public static final String FILA_CONTROLLER_ADICIONAR_TIPO_ATENDIMENTO_OPERATION_SUMMARY = "Adicionar tipo de atendimento";
    public static final String FILA_CONTROLLER_ADICIONAR_TIPO_ATENDIMENTO_OPERATION_DESCRIPTION = "Adicionar tipo de atendimento à fila";
    public static final String FILA_CONTROLLER_ADICIONAR_TIPO_ATENDIMENTO_PARAMETER_ID_DESCRIPTION = FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String FILA_CONTROLLER_ADICIONAR_TIPO_ATENDIMENTO_PARAMETER_ID_EXAMPLE = FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String FILA_CONTROLLER_ADICIONAR_TIPO_ATENDIMENTO_200_DESCRIPTION = "Retorna detalhe da fila com tipo de atendimento adicionado";
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_OPERATION_SUMMARY = "Remover tipo de atendimento";
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_OPERATION_DESCRIPTION = "Remover tipo de atendimento da fila";
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_PARAMETER_ID_DESCRIPTION = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_PARAMETER_ID_EXAMPLE = USUARIO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_PARAMETER_TIPO_ATENDIMENTO_ID_DESCRIPTION = TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_PARAMETER_TIPO_ATENDIMENTO_ID_EXAMPLE = TIPO_ATENDIMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String FILA_CONTROLLER_REMOVER_TIPO_ATENDIMENTO_200_DESCRIPTION = "Retorna detalhe da fila com tipo de atendimento removido";
    public static final String FILA_CONTROLLER_DELETE_BY_ID_OPERATION_SUMMARY = "Excluir Fila";
    public static final String FILA_CONTROLLER_DELETE_BY_ID_OPERATION_DESCRIPTION = "Realizar exclusão da fila";
    public static final String FILA_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_DESCRIPTION = FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION;
    public static final String FILA_CONTROLLER_DELETE_BY_ID_PARAMETER_ID_EXAMPLE = FILA_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE;
    public static final String FILA_CONTROLLER_DELETE_BY_ID_204_DESCRIPTION = "Fila excluída com sucesso";


    private ControllerAnnotationsV1() {
        throw new IllegalAccessError("Utility Class");
    }

}
