package br.tec.didiproject.queueserviceapi.enums.constants.v1;

public final class MappingRoutesV1 {

    public static final String URI_BASE = "http://localhost:8080";
    public static final String PATH_ROOT = "/api/v1";
    public static final String PATH_AUTH = PATH_ROOT+"/auth";
    public static final String PATH_EMPRESA = PATH_ROOT+"/empresa";
    public static final String PATH_DEPARTAMENTO = PATH_ROOT+"/departamento";
    public static final String PATH_ATENDENTE = PATH_ROOT+"/atendente";
    public static final String PATH_USUARIO = PATH_ROOT+"/usuario";

    private MappingRoutesV1() {
        throw new IllegalAccessError("Utility Class");
    }

}
