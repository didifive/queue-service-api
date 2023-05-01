package br.tec.didiproject.queueserviceapi.enums.constants.v1;

public class ValidationMessagesV1 {
    /**
     * Constants for RequisicaoEmpresaDTO
     */
    public static final String EMPRESA_NOME_NOT_BLANK = "Informe um nome para a empresa";
    public static final String EMPRESA_NOME_SIZE = "O nome da empresa deve ter entre 3 e 255 caracteres";
    public static final String EMPRESA_CPF_CNPJ_SIZE = "O CPF ou CNPJ da empresa deve ter entre 11 e 18 caracteres";
    public static final String EMPRESA_ENDERECO_SIZE = "O endereco da empresa deve ter entre 3 e 255 caracteres";

    /**
     * Constants for RequisicaoDepartamentoDTO
     */
    public static final String DEPARTAMENTO_NOME_NOT_BLANK = "Informe um nome para o departamento";
    public static final String DEPARTAMENTO_NOME_SIZE = "O nome do departamento deve ter entre 3 e 255 caracteres";
    public static final String DEPARTAMENTO_EMPRESA_ID_NOT_BLANK = "Informe um id de empresa da qual o departamento está relacionado";
    public static final String DEPARTAMENTO_EMPRESA_ID_UUID = "O Id informado para a empresa está inválido";

    /**
     * Constants for RequisicaoAtendenteDTO
     */
    public static final String ATENDENTE_NOME_NOT_BLANK = "Informe um nome para o atendente";
    public static final String ATENDENTE_NOME_SIZE = "O nome do atendente deve ter entre 3 e 255 caracteres";
    public static final String ATENDENTE_EMAIL_NOT_BLANK = "Informe um email para o atendente";
    public static final String ATENDENTE_EMAIL_EMAIL = "Informe um e-mail válido para o atendente";
    public static final String ATENDENTE_EMAIL_SIZE = "O e-mail do atendente deve ter entre 3 e 255 caracteres";
    public static final String ATENDENTE_DEPARTAMENTO_ID_UUID = "O(s) Id(s) informado(s) para o(s) departamento(s) está(ão) inválido(s)";

    /**
     * Constants for RequisicaoUsuarioAtualizarSenhaDTO, RequisicaoUsuarioDTO, RequisicaoUsuarioNovoNomeUsuarioDTO and RequisicaoUsuarioPerfilDTO
     */
    public static final String USUARIO_ATUALIZAR_SENHA_SENHA_ATUAL_NOT_BLANK = "Informe a senha atual";
    public static final String USUARIO_ATUALIZAR_SENHA_SENHA_ATUAL_SIZE = "A senha atual deve ter entre 6 e 60 caracteres";
    public static final String USUARIO_ATUALIZAR_SENHA_NOVA_SENHA_NOT_BLANK = "Informe a nova senha";
    public static final String USUARIO_ATUALIZAR_SENHA_NOVA_SENHA_SIZE = "A nova senha deve ter entre 6 e 60 caracteres";
    public static final String USUARIO_NOME_USUARIO_NOT_BLANK = "Informe um nome de usuário";
    public static final String USUARIO_NOME_USUARIO_SIZE = "O nome de usuário deve ter entre 3 e 255 caracteres";
    public static final String USUARIO_SENHA_NOT_BLANK = "Informe uma senha para o usuário";
    public static final String USUARIO_SENHA_SIZE = "A senha deve ter entre 6 e 60 caracteres";
    public static final String USUARIO_ATENDENTE_ID_UUID = "O Id informado para o atendente está inválido";
    public static final String USUARIO_PERFIL_PERFIL_NOT_BLANK = "Informe no mínimo um perfil";

    /**
     * Constants for RequisicaoTipoAtendimentoDTO
     */
    public static final String TIPO_ATENDIMENTO_NOME_NOT_BLANK = "Informe um nome para o tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_NOME_SIZE = "O nome do tipo de atendimento deve ter entre 3 e 255 caracteres";
    public static final String TIPO_ATENDIMENTO_SIGLA_NOT_BLANK = "Informe uma sigla para o tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_SIGLA_PATTERN = "A sigla aceita somente caractere de texto de A até Z";
    public static final String TIPO_ATENDIMENTO_SIGLA_SIZE = "A sigla do tipo de atendimento deve ter entre 1 e 3 caracteres";
    public static final String TIPO_ATENDIMENTO_PRIORIDADE_NOT_NULL = "Informe uma prioridade para o tipo de atendimento";
    public static final String TIPO_ATENDIMENTO_PRIORIDADE_MIN = "O valor mínimo aceito para prioridade é 1";
    public static final String TIPO_ATENDIMENTO_PRIORIDADE_MAX = "O valor máximo aceito para prioridade é 32767";

    /**
     * Constants for RequisicaoFilaDTO
     */
    public static final String FILA_NOME_NOT_BLANK = "Informe um nome para o atendente";
    public static final String FILA_NOME_SIZE = "O nome do atendente deve ter entre 3 e 255 caracteres";
    public static final String FILA_SIGLA_NOT_BLANK = "Informe uma sigla para o tipo de atendimento";
    public static final String FILA_SIGLA_PATTERN = TIPO_ATENDIMENTO_SIGLA_PATTERN;
    public static final String FILA_SIGLA_SIZE = "A sigla do tipo de atendimento deve ter entre 1 e 3 caracteres";
    public static final String FILA_DEPARTAMENTO_ID_NOT_BLANK = "Informe um id de empresa da qual o departamento está relacionado";
    public static final String FILA_DEPARTAMENTO_ID_UUID = "O Id informado para a empresa está inválido";
    public static final String FILA_TIPOS_ATENDIMENTO_UUID = "O(s) Id(s) informado(s) para o(s) tipo(s) de atendimento está(ão) inválido(s)";


    private ValidationMessagesV1() {
        throw new IllegalAccessError("Utility Class");
    }
}
