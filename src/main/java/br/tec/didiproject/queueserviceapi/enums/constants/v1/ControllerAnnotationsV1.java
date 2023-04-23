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

    public static final String EMPRESA_CONTROLER_TAG = "3. Empresa";
    public static final String EMPRESA_CONTROLLER_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
    public static final String EMPRESA_CONTROLLER_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
    public static final String EMPRESA_CONTROLLER_404_DESCRIPTION = "Empresa não foi encontrada";
    public static final String EMPRESA_CONTROLLER_409_DESCRIPTION = "Ocorreu erro com integridade de dado";
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

    public static final String DEPARTAMENTO_CONTROLER_TAG = "4. Departamento";
    public static final String DEPARTAMENTO_CONTROLLER_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
    public static final String DEPARTAMENTO_CONTROLLER_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
    public static final String DEPARTAMENTO_CONTROLLER_404_DESCRIPTION = "Departamento não foi encontrado";
    public static final String DEPARTAMENTO_CONTROLLER_409_DESCRIPTION = "Ocorreu erro com integridade de dado";
    public static final String DEPARTAMENTO_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar departamento";
    public static final String DEPARTAMENTO_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar novo departamento";
    public static final String DEPARTAMENTO_CONTROLLER_CREATE_201_DESCRIPTION = "Novo departamento cadastrado com sucesso";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Departamentos";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todas os Departamentos cadastradas";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de departamentos cadastrados";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_OPERATION_SUMMARY = "Detalhar Departamento";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_OPERATION_DESCRIPTION = "Detalhar departamento conforme id informado";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_DESCRIPTION = "Id do departamento";
    public static final String DEPARTAMENTO_CONTROLLER_FIND_BY_ID_PARAMETER_ID_EXAMPLE = "123e4567-e89b-12d3-a456-426655440000";
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


    private ControllerAnnotationsV1() {
        throw new IllegalAccessError("Utility Class");
    }

}
