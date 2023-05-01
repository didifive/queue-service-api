package br.tec.didiproject.queueserviceapi.enums.constants;

public final class OpenApiTypes {

    /**
     * Constants for @Schema annotation types
     */
    public static final String SCHEMA_TYPE_INTEGER = "integer";
    public static final String SCHEMA_TYPE_NUMBER = "number";
    public static final String SCHEMA_TYPE_STRING = "string";
    public static final String SCHEMA_TYPE_BOOLEAN = "boolean";

    private OpenApiTypes() {
        throw new IllegalAccessError("Utility Class");
    }

}
