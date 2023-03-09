package br.tec.didiproject.queueserviceapi.enums.constants.v1;

public final class MappingRoutesV1 {

    public static final String URI_BASE = "http://localhost";

    public static final String PATH_ROOT = "/api/v1";


    public static final String PATH_AUTH = PATH_ROOT+"/auth";

    public static final String PATH_EMPRESA = PATH_ROOT+"/empresa";

    public static final String PATH_DEPARTAMENTO = PATH_ROOT+"/departamento";


    public static final String PATH_USERS = PATH_ROOT+"/users";
    public static final String PATH_USERS_ID = "/{id}";
    public static final String PATH_USERS_PASSWORD = PATH_USERS_ID + "/password";

    private MappingRoutesV1() {
        throw new IllegalAccessError("Utility Class");
    }

}
