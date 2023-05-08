package br.tec.didiproject.queueserviceapi.enums.constants;

import static br.tec.didiproject.queueserviceapi.enums.constants.Constants.UTILITY_CLASS;

public final class OpenApiTypes {

    /**
     * Constants for @Schema annotation types
     */
    public static final String SCHEMA_TYPE_INTEGER = "integer";
    public static final String SCHEMA_TYPE_NUMBER = "number";
    public static final String SCHEMA_TYPE_STRING = "string";
    public static final String SCHEMA_TYPE_BOOLEAN = "boolean";

    private OpenApiTypes() {
        throw new IllegalAccessError(UTILITY_CLASS);
    }

}
