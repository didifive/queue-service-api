package br.tec.didiproject.queueserviceapi.enums.constants.v1;

public class ValidationMessagesV1 {

    /**
     * Constants for RequisicaoFilaDTO
     */
    public static final String FILA_NOME_NOT_BLANK = "Informe um nome para o atendente";
    public static final String FILA_NOME_SIZE = "O nome do atendente deve ter entre 3 e 255 caracteres";
    public static final String FILA_SIGLA_NOT_BLANK = "Informe uma sigla para o tipo de atendimento";
    public static final String FILA_SIGLA_PATTERN = "A sigla aceita somente caractere de texto de A até Z";
    public static final String FILA_SIGLA_SIZE = "A sigla do tipo de atendimento deve ter entre 1 e 3 caracteres";
    public static final String FILA_DEPARTAMENTO_ID_NOT_BLANK = "Informe um id de empresa da qual o departamento está relacionado";
    public static final String FILA_DEPARTAMENTO_ID_UUID = "O Id informado para a empresa está inválido";
    public static final String FILA_TIPOS_ATENDIMENTO_UUID = "O(s) Id(s) informado(s) para o(s) tipo(s) de atendimento está(ão) inválido(s)";


    private ValidationMessagesV1() {
        throw new IllegalAccessError("Utility Class");
    }
}
