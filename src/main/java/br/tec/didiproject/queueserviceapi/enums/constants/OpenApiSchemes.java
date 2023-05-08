package br.tec.didiproject.queueserviceapi.enums.constants;

import static br.tec.didiproject.queueserviceapi.enums.constants.Constants.UTILITY_CLASS;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_AUTH;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_ROOT;

public final class OpenApiSchemes {
    /**
     * Constants for @SecurityScheme annotation in QueueServiceApiApplication
     */
    public static final String SECURITY_SCHEME_NAME = "bearerAuth";
    public static final String SECURITY_SCHEME_DESCRIPTION = "JWT Authorization header using the Bearer scheme";
    public static final String SECURITY_SCHEME = "bearer";
    public static final String SECURITY_SCHEME_BEARER_FORMAT = "JWT";

    /**
     * Constants for @Tag annotation by HTTP method in MeuAmigauApiApplication
     */
    public static final String TAG_POST = "Método HTTP POST";
    public static final String TAG_GET = "Método HTTP GET";
    public static final String TAG_PUT = "Método HTTP PUT";
    public static final String TAG_PATCH = "Método HTTP PATCH";
    public static final String TAG_DELETE = "Método HTTP DELETE";

    /**
     * Constants for @Schema annotation in ApiErrorDTO
     */
    public static final String SCHEMA_API_ERROR_TIMESTAMP_TITLE = "Timestamp";
    public static final String SCHEMA_API_ERROR_TIMESTAMP_DESCRIPTION = "Instante da mensagem";
    public static final String SCHEMA_API_ERROR_TIMESTAMP_EXAMPLE = "2022-07-25T12:30:26.876372900Z";
    public static final String SCHEMA_API_ERROR_STATUS_TITLE = "Status";
    public static final String SCHEMA_API_ERROR_STATUS_DESCRIPTION = "Código de Status HTTP";
    public static final String SCHEMA_API_ERROR_STATUS_EXAMPLE = "404";
    public static final String SCHEMA_API_ERROR_ERROR_TITLE = "Erro";
    public static final String SCHEMA_API_ERROR_ERROR_DESCRIPTION = "Tipo do erro";
    public static final String SCHEMA_API_ERROR_ERROR_EXAMPLE = "Recurso não encontrado";
    public static final String SCHEMA_API_ERROR_MESSAGE_TITLE = "Mensagem";
    public static final String SCHEMA_API_ERROR_MESSAGE_DESCRIPTION = "Mensagem do erro";
    public static final String SCHEMA_API_ERROR_MESSAGE_EXAMPLE = "Pessoa com id 10 não foi encontrada";
    public static final String SCHEMA_API_ERROR_PATH_TITLE = "Path";
    public static final String SCHEMA_API_ERROR_PATH_DESCRIPTION = "Caminho da soliticação";
    public static final String SCHEMA_API_ERROR_PATH_EXAMPLE = PATH_ROOT + PATH_AUTH;

    /**
     * Constants for @Schema annotation in Auth and Token DTOs
     */
    public static final String SCHEMA_AUTH_USERNAME_TITLE = "Nome de usuário";
    public static final String SCHEMA_AUTH_USERNAME_DESCRIPTION = "Nome de usuário para autenticação";
    public static final String SCHEMA_AUTH_USERNAME_EXAMPLE = "username@email.com";
    public static final String SCHEMA_AUTH_PASSWORD_TITLE = "Senha";
    public static final String SCHEMA_AUTH_PASSWORD_DESCRIPTION = "Senha para autenticação";
    public static final String SCHEMA_AUTH_PASSWORD_EXAMPLE = "abcd1234";
    public static final String SCHEMA_AUTH_TOKEN_TITLE = "JWT Token";
    public static final String SCHEMA_AUTH_TOKEN_DESCRIPTION = "JWT Token to authorize in endpoints with Bearer format";
    public static final String SCHEMA_AUTH_TOKEN_EXAMPLE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI"
            + "6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

    public static final String SCHEMA_AUTH_REFRESH_TOKEN_TITLE = "Refresh Token";
    public static final String SCHEMA_AUTH_REFRESH_TOKEN_DESCRIPTION = "Refresh Token";
    public static final String SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE = "123e4567-e89b-12d3-a456-426655440000";
    public static final String SCHEMA_AUTH_USER_ID_TITLE = "Id do usuário";
    public static final String SCHEMA_AUTH_USER_ID_DESCRIPTION = "Id de usuário autenticado";
    public static final String SCHEMA_AUTH_USER_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;

    /**
     * Constants for @Schema annotation in RequisicaoEmpresaDTO and RespostaEmpresaDTO
     */
    public static final String SCHEMA_EMPRESA = " da empresa";
    public static final String SCHEMA_EMPRESA_ID_TITLE = "Id";
    public static final String SCHEMA_EMPRESA_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_EMPRESA_ID_DESCRIPTION = SCHEMA_EMPRESA_ID_TITLE + SCHEMA_EMPRESA;
    public static final String SCHEMA_EMPRESA_NOME_TITLE = "Nome";
    public static final String SCHEMA_EMPRESA_NOME_DESCRIPTION = SCHEMA_EMPRESA_NOME_TITLE + SCHEMA_EMPRESA;
    public static final String SCHEMA_EMPRESA_NOME_EXAMPLE = "Empresa LTDA";
    public static final String SCHEMA_EMPRESA_CPF_CNPJ_TITLE = "CPF/CNPJ";
    public static final String SCHEMA_EMPRESA_CPF_CNPJ_DESCRIPTION = SCHEMA_EMPRESA_CPF_CNPJ_TITLE + SCHEMA_EMPRESA;
    public static final String SCHEMA_EMPRESA_CPF_CNPJ_EXAMPLE = "12.345.678/0001-98";
    public static final String SCHEMA_EMPRESA_ENDERECO_TITLE = "CPF/CNPJ";
    public static final String SCHEMA_EMPRESA_ENDERECO_DESCRIPTION = SCHEMA_EMPRESA_ENDERECO_TITLE + SCHEMA_EMPRESA;
    public static final String SCHEMA_EMPRESA_ENDERECO_EXAMPLE = "Avenida Primeiro de Janeiro, n 1000, São Paulo-SP";

    /**
     * Constants for @Schema annotation in RequisicaoDepartamentoDTO and RespostaDepartamentoDTO
     */
    public static final String SCHEMA_DEPARTAMENTO = " do departamento";
    public static final String SCHEMA_DEPARTAMENTO_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_DEPARTAMENTO_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_DEPARTAMENTO_ID_DESCRIPTION = SCHEMA_DEPARTAMENTO_ID_TITLE + SCHEMA_DEPARTAMENTO;
    public static final String SCHEMA_DEPARTAMENTO_NOME_TITLE = SCHEMA_EMPRESA_NOME_TITLE;
    public static final String SCHEMA_DEPARTAMENTO_NOME_DESCRIPTION = SCHEMA_DEPARTAMENTO_NOME_TITLE + SCHEMA_DEPARTAMENTO;
    public static final String SCHEMA_DEPARTAMENTO_NOME_EXAMPLE = "Caixa";
    public static final String SCHEMA_DEPARTAMENTO_EMPRESA_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_DEPARTAMENTO_EMPRESA_ID_DESCRIPTION = SCHEMA_EMPRESA_ID_DESCRIPTION;
    public static final String SCHEMA_DEPARTAMENTO_EMPRESA_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;

    /**
     * Constants for @Schema annotation in RequisicaoAtendenteDTO and RespostaAtendenteDTO
     */
    public static final String SCHEMA_ATENDENTE = " do atendente";
    public static final String SCHEMA_ATENDENTE_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_ATENDENTE_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_ATENDENTE_ID_DESCRIPTION = SCHEMA_ATENDENTE_ID_TITLE + SCHEMA_ATENDENTE;
    public static final String SCHEMA_ATENDENTE_NOME_TITLE = SCHEMA_EMPRESA_NOME_TITLE;
    public static final String SCHEMA_ATENDENTE_NOME_DESCRIPTION = SCHEMA_ATENDENTE_NOME_TITLE + SCHEMA_ATENDENTE;
    public static final String SCHEMA_ATENDENTE_NOME_EXAMPLE = "José da Silva";
    public static final String SCHEMA_ATENDENTE_EMAIL_TITLE = "E-mail";
    public static final String SCHEMA_ATENDENTE_EMAIL_DESCRIPTION = SCHEMA_ATENDENTE_EMAIL_TITLE + SCHEMA_ATENDENTE;
    public static final String SCHEMA_ATENDENTE_EMAIL_EXAMPLE = "José da Silva";
    public static final String SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_TITLE = SCHEMA_DEPARTAMENTO_ID_TITLE;
    public static final String SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_DESCRIPTION = "Id de departamento vinculado ao atendente";
    public static final String SCHEMA_ATENDENTE_LISTA_DEPARTAMENTO_ID_EXAMPLE = SCHEMA_DEPARTAMENTO_ID_EXAMPLE;
    public static final String SCHEMA_ATENDENTE_USUARIO_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_ATENDENTE_USUARIO_ID_DESCRIPTION = SCHEMA_ATENDENTE_USUARIO_ID_TITLE + "do usuário vinculado";
    public static final String SCHEMA_ATENDENTE_USUARIO_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;

    /**
     * Constants for @Schema annotation in RequisicaoUsuarioDTO, RequisicaoUsuarioAtualizarSenhaDTO,
     * RequisicaoUsuarioNovoNomeUsuarioDTO, RequisicaoUsuarioPerfilDTO and RespostaUsuarioDTO
     */
    public static final String SCHEMA_USUARIO = " do usuário";
    public static final String SCHEMA_USUARIO_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_USUARIO_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_USUARIO_ID_DESCRIPTION = SCHEMA_USUARIO_ID_TITLE + SCHEMA_USUARIO;
    public static final String SCHEMA_USUARIO_NOME_TITLE = "Nome de Usuário";
    public static final String SCHEMA_USUARIO_NOME_DESCRIPTION = SCHEMA_USUARIO_NOME_TITLE + SCHEMA_USUARIO;
    public static final String SCHEMA_USUARIO_NOME_EXAMPLE = "jose@email.com";
    public static final String SCHEMA_USUARIO_SENHA_TITLE = "Senha";
    public static final String SCHEMA_USUARIO_SENHA_DESCRIPTION = SCHEMA_ATENDENTE_EMAIL_TITLE + SCHEMA_USUARIO;
    public static final String SCHEMA_USUARIO_SENHA_EXAMPLE = "S3nhaJ0seS1lv@";
    public static final String SCHEMA_USUARIO_PERFIL_TITLE = "Perfil";
    public static final String SCHEMA_USUARIO_PERFIL_DESCRIPTION = SCHEMA_USUARIO_PERFIL_TITLE + SCHEMA_USUARIO;
    public static final String SCHEMA_USUARIO_PERFIL_EXAMPLE = "USUARIO";
    public static final String SCHEMA_USUARIO_ATENDENTE_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_USUARIO_ATENDENTE_ID_DESCRIPTION = SCHEMA_USUARIO_ATENDENTE_ID_TITLE + "do atendente vinculado";
    public static final String SCHEMA_USUARIO_ATENDENTE_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_USUARIO_ATIVO_TITLE = "Status";
    public static final String SCHEMA_USUARIO_ATIVO_DESCRIPTION = SCHEMA_USUARIO_ATIVO_TITLE + SCHEMA_USUARIO;
    public static final String SCHEMA_USUARIO_ATIVO_EXAMPLE = "true";

    /**
     * Constants for @Schema annotation in RequisicaoTipoAtendimentoDTO and RespostaTipoAtendimentoDTO
     */
    public static final String SCHEMA_TIPO_ATENDIMENTO = " do tipo de atendimento";
    public static final String SCHEMA_TIPO_ATENDIMENTO_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_TIPO_ATENDIMENTO_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_TIPO_ATENDIMENTO_ID_DESCRIPTION = SCHEMA_TIPO_ATENDIMENTO_ID_TITLE + SCHEMA_TIPO_ATENDIMENTO;
    public static final String SCHEMA_TIPO_ATENDIMENTO_NOME_TITLE = SCHEMA_EMPRESA_NOME_TITLE;
    public static final String SCHEMA_TIPO_ATENDIMENTO_NOME_DESCRIPTION = SCHEMA_TIPO_ATENDIMENTO_NOME_TITLE + SCHEMA_TIPO_ATENDIMENTO;
    public static final String SCHEMA_TIPO_ATENDIMENTO_NOME_EXAMPLE = "Normal";
    public static final String SCHEMA_TIPO_ATENDIMENTO_SIGLA_TITLE = "Sigla";
    public static final String SCHEMA_TIPO_ATENDIMENTO_SIGLA_DESCRIPTION = SCHEMA_TIPO_ATENDIMENTO_SIGLA_TITLE + SCHEMA_TIPO_ATENDIMENTO;
    public static final String SCHEMA_TIPO_ATENDIMENTO_SIGLA_EXAMPLE = "N";
    public static final String SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_TITLE = "Prioridade";
    public static final String SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_DESCRIPTION = SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_TITLE + SCHEMA_TIPO_ATENDIMENTO;
    public static final String SCHEMA_TIPO_ATENDIMENTO_PRIORIDADE_EXAMPLE = "10";

    /**
     * Constants for @Schema annotation in RequisicaoFilaDTO and RespostaFilaDTO
     */
    public static final String SCHEMA_FILA = " da fila";
    public static final String SCHEMA_FILA_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_FILA_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_FILA_ID_DESCRIPTION = SCHEMA_FILA_ID_TITLE + SCHEMA_FILA;
    public static final String SCHEMA_FILA_NOME_TITLE = SCHEMA_EMPRESA_NOME_TITLE;
    public static final String SCHEMA_FILA_NOME_DESCRIPTION = SCHEMA_FILA_NOME_TITLE + SCHEMA_FILA;
    public static final String SCHEMA_FILA_NOME_EXAMPLE = "Caixa";
    public static final String SCHEMA_FILA_SIGLA_TITLE = "Sigla";
    public static final String SCHEMA_FILA_SIGLA_DESCRIPTION = SCHEMA_FILA_SIGLA_TITLE + SCHEMA_FILA;
    public static final String SCHEMA_FILA_SIGLA_EXAMPLE = "CX";
    public static final String SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_TITLE = SCHEMA_TIPO_ATENDIMENTO_ID_TITLE;
    public static final String SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_DESCRIPTION = "Id de tipo de atendimento vinculado à fila";
    public static final String SCHEMA_FILA_LISTA_TIPO_ATENDIMENTO_ID_EXAMPLE = SCHEMA_TIPO_ATENDIMENTO_ID_EXAMPLE;
    public static final String SCHEMA_FILA_DEPARTAMENTO_ID_TITLE = SCHEMA_DEPARTAMENTO_ID_TITLE;
    public static final String SCHEMA_FILA_DEPARTAMENTO_ID_DESCRIPTION = "Id de departamento vinculado à fila";
    public static final String SCHEMA_FILA_DEPARTAMENTO_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;

    /**
     * Constants for @Schema annotation in RequisicaoSenhaFinalizaSenhaDTO, RequisicaoSenhaNovaSenhaDTO and RespostaSenhaDTO
     */
    public static final String SCHEMA_SENHA = " da senha";
    public static final String SCHEMA_SENHA_ID_TITLE = SCHEMA_EMPRESA_ID_TITLE;
    public static final String SCHEMA_SENHA_ID_EXAMPLE = SCHEMA_AUTH_REFRESH_TOKEN_EXAMPLE;
    public static final String SCHEMA_SENHA_ID_DESCRIPTION = SCHEMA_SENHA_ID_TITLE + SCHEMA_SENHA;
    public static final String SCHEMA_SENHA_NUMERO_TITLE = "Número";
    public static final String SCHEMA_SENHA_NUMERO_DESCRIPTION = SCHEMA_SENHA_NUMERO_TITLE + SCHEMA_SENHA;
    public static final String SCHEMA_SENHA_NUMERO_EXAMPLE = "500";
    public static final String SCHEMA_SENHA_FILA_ID_TITLE = SCHEMA_FILA_ID_TITLE;
    public static final String SCHEMA_SENHA_FILA_ID_DESCRIPTION = "Id da fila vinculada à senha";
    public static final String SCHEMA_SENHA_FILA_ID_EXAMPLE = SCHEMA_FILA_ID_EXAMPLE;
    public static final String SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_TITLE = SCHEMA_TIPO_ATENDIMENTO_ID_TITLE;
    public static final String SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_DESCRIPTION = "Id de tipo de atendimento vinculado à senha";
    public static final String SCHEMA_SENHA_TIPO_ATENDIMENTO_ID_EXAMPLE = SCHEMA_TIPO_ATENDIMENTO_ID_EXAMPLE;
    public static final String SCHEMA_SENHA_RESET_TITLE = "Número para reiniciar";
    public static final String SCHEMA_SENHA_RESET_DESCRIPTION = "Número para reiniciar a sequência número da senha";
    public static final String SCHEMA_SENHA_RESET_EXAMPLE = "1";
    public static final String SCHEMA_SENHA_ATENDENTE_ID_TITLE = SCHEMA_ATENDENTE_ID_TITLE;
    public static final String SCHEMA_SENHA_ATENDENTE_ID_DESCRIPTION = "Id de atendente vinculado à senha";
    public static final String SCHEMA_SENHA_ATENDENTE_ID_EXAMPLE = SCHEMA_TIPO_ATENDIMENTO_ID_EXAMPLE;
    public static final String SCHEMA_SENHA_MOTIVO_FINALIZADA_TITLE = "Motivo para finalizar senha";
    public static final String SCHEMA_SENHA_MOTIVO_FINALIZADA_DESCRIPTION = "Motivo para finalização/encerramento da senha";
    public static final String SCHEMA_SENHA_MOTIVO_FINALIZADA_EXAMPLE = "Senha finalizada sem atendimento, pois foi gerada para teste";

    private OpenApiSchemes() {
        throw new IllegalAccessError(UTILITY_CLASS);
    }

}
