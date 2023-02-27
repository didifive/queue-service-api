package br.tec.didiproject.queueserviceapi.enums.constants;

import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_AUTH;
import static br.tec.didiproject.queueserviceapi.enums.constants.v1.MappingRoutesV1.PATH_ROOT;

public class OpenApiSchemes {
    /**
     * Constants for @SecurityScheme annotation in QueueServiceApiApplication
     */
    public static final String SECURITY_SCHEME_NAME = "bearerAuth";
    public static final String SECURITY_SCHEME_DESCRIPTION = "JWT Authorization header using the Bearer scheme";
    public static final String SECURITY_SCHEME = "bearer";
    public static final String SECURITY_SCHEME_BEARER_FORMAT = "JWT";

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

    private OpenApiSchemes() {
        throw new IllegalAccessError("Utility Class");
    }

}
