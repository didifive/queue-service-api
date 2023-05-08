package br.tec.didiproject.queueserviceapi.enums.constants;

import static br.tec.didiproject.queueserviceapi.enums.constants.Constants.UTILITY_CLASS;

public class SecurityAuthority {
    public static final String OWN_USER =
            "(principal.getId.toString == #id OR principal.getId.toString == #usuarioId)";
    public static final String NOT_OWN_USER =
            "principal.getId.toString != #id";
    public static final String OWN_USER_ATTENDANT =
            "principal.getAtendente.getId.toString == #id";
    public static final String HAS_AUTHORITY_ADMIN = "hasAuthority('ADMIN')";
    public static final String HAS_AUTHORITY_ADMIN_OR_USUARIO = "hasAnyAuthority ('ADMIN', 'USUARIO')";
    public static final String HAS_AUTHORITY_ADMIN_OR_ATENDENTE = "hasAnyAuthority ('ADMIN', 'ATENDENTE')";
    public static final String HAS_AUTHORITY_ADMIN_AND_NOT_OWN_USER =
            HAS_AUTHORITY_ADMIN
                    + " AND "
                    + NOT_OWN_USER;
    public static final String HAS_AUTHORITY_ADMIN_OR_OWN_USER =
            HAS_AUTHORITY_ADMIN
                    + "OR "
                    + OWN_USER;
    public static final String HAS_AUTHORITY_ADMIN_OR_USUARIO_OR_OWN_USER_ATTENDANT =
            HAS_AUTHORITY_ADMIN_OR_USUARIO
                    + " OR "
                    + OWN_USER_ATTENDANT;

    private SecurityAuthority() {
        throw new IllegalAccessError(UTILITY_CLASS);
    }
}
