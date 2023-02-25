package br.tec.didiproject.queueserviceapi.enums.constants;

public class OpenApiAnnotations {

    /**
     * Constants for @Schema annotation types
     */
    public static final String SCHEMA_TYPE_ARRAY = "array";
    public static final String SCHEMA_TYPE_INTEGER = "integer";
    public static final String SCHEMA_TYPE_NUMBER = "number";
    public static final String SCHEMA_TYPE_STRING = "string";

    /**
     * Constants for @SecurityScheme annotation in QueueServiceApiApplication
     */
    public static final String SECURITY_SCHEME_BEARER_AUTH = "bearerAuth";
    public static final String SECURITY_SCHEME_DESCRIPTION = "JWT Authorization header using the Bearer scheme";

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
    public static final String SCHEMA_API_ERROR_PATH_EXAMPLE = "/api/v1/people/10";

}
