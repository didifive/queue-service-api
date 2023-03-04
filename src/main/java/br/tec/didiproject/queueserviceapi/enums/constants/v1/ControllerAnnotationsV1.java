package br.tec.didiproject.queueserviceapi.enums.constants.v1;

public final class ControllerAnnotationsV1 {

    /**
     * Constants for AuthControllerDocs
     */
    public static final String AUTH_CONTROLER_TAG = "1. Autenticação";
    public static final String AUTH_CONTROLER_AUTHENTICATE_OPERATION_SUMMARY = "Gerar Token de acesso";
    public static final String AUTH_CONTROLER_AUTHENTICATE_OPERATION_DESCRIPTION = "Operação para gerar token de acesso";
    public static final String AUTH_CONTROLER_AUTHENTICATE_200_DESCRIPTION = "Token criado com sucesso";
    public static final String AUTH_CONTROLER_AUTHENTICATE_401_DESCRIPTION = "Usuário ou senha inválido(s)";

    public static final String EMPRESA_CONTROLER_TAG = "2. Empresa";
    public static final String EMPRESA_CONTROLLER_CREATE_OPERATION_SUMMARY = "Cadastrar empresa";
    public static final String EMPRESA_CONTROLLER_CREATE_OPERATION_DESCRIPTION = "Operação para cadastrar nova empresa";
    public static final String EMPRESA_CONTROLLER_CREATE_201_DESCRIPTION = "Nova empresa cadastrada com sucesso";
    public static final String EMPRESA_CONTROLLER_CREATE_400_DESCRIPTION = "Campo requerido vazio ou inválido, verificar mensagem de retorno";
    public static final String EMPRESA_CONTROLLER_CREATE_403_DESCRIPTION = "Acesso Negado. Não está autorizado";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_OPERATION_SUMMARY = "Listar Empresas";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_OPERATION_DESCRIPTION = "Listar todas as Empresas cadastradas";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SIZE_DESCRIPTION = "Quantidade de objetos por página";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SIZE_EXAMPLE = "10";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_PAGE = "O número da página que quer retornar";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_PAGE_SIZE = "0";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SORT_DESCRIPTION = "Nome do campo que deseja ordenar. "
            + "Seguido da direção de ordenação desejada \',asc|desc\' (ascendente/descendente). "
            + "A direção de ordenação padrão é ascendent. "
            + "Aceita ordenação múltipla. ";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_PARAMETER_SORT_EXAMPLE = "id,desc";
    public static final String EMPRESA_CONTROLLER_FIND_ALL_200_DESCRIPTION = "Retorna lista paginada de empresas cadastradas";


    private ControllerAnnotationsV1() {
        throw new IllegalAccessError("Utility Class");
    }

}
