package br.tec.didiproject.queueserviceapi.exceptions;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;

import java.text.MessageFormat;
import java.util.ResourceBundle;

@RequiredArgsConstructor
public class BaseErrorMessage {
    public static final BaseErrorMessage GENERIC_EXCEPTION = new BaseErrorMessage("generic");
    public static final BaseErrorMessage GENERIC_NOT_FOUND = new BaseErrorMessage("generic.notFound");
    public static final BaseErrorMessage GENERIC_BAD_REQUEST = new BaseErrorMessage("generic.badRequest");
    public static final BaseErrorMessage GENERIC_DATA_INTEGRITY_VIOLATION = new BaseErrorMessage("generic.dataIntegrityViolation");
    public static final BaseErrorMessage GENERIC_FORBIDDEN = new BaseErrorMessage("generic.forbidden");
    public static final BaseErrorMessage REFRESH_TOKEN_NOT_FOUND = new BaseErrorMessage("refreshToken.notFound");
    public static final BaseErrorMessage REFRESH_TOKEN_EXPIRED = new BaseErrorMessage("refreshToken.expired");
    public static final BaseErrorMessage ROLE_NOT_FOUND = new BaseErrorMessage("role.notFound");
    public static final BaseErrorMessage ROLE_NOT_FOUND_BY_NAME = new BaseErrorMessage("role.notFound.byName");
    public static final BaseErrorMessage ROLE_ALREADY_EXISTS = new BaseErrorMessage("role.alreadyExists");
    public static final BaseErrorMessage USER_NOT_FOUND = new BaseErrorMessage("user.notFound");
    public static final BaseErrorMessage USER_BY_USERNAME_NOT_FOUND = new BaseErrorMessage("user.notFound.byUsername");
    public static final BaseErrorMessage USER_BY_ATTENDANT_NOT_FOUND = new BaseErrorMessage("user.notFound.byAttendant");
    public static final BaseErrorMessage USER_ALREADY_EXISTS = new BaseErrorMessage("user.alreadyExists");
    public static final BaseErrorMessage USER_WRONG_PASSWORD = new BaseErrorMessage("user.wrongPassword");
    public static final BaseErrorMessage COMPANY_NOT_FOUND = new BaseErrorMessage("company.notFound");
    public static final BaseErrorMessage COMPANY_WITH_ASSOCIATED_DEPARTMENT = new BaseErrorMessage("company.withAssociatedDepartment");
    public static final BaseErrorMessage DEPARTMENT_NOT_FOUND = new BaseErrorMessage("department.notFound");
    public static final BaseErrorMessage DEPARTMENT_WITH_ASSOCIATED_ATTENDANT = new BaseErrorMessage("department.withAssociatedAttendant");
    public static final BaseErrorMessage ATTENDANT_NOT_FOUND = new BaseErrorMessage("attendant.notFound");
    public static final BaseErrorMessage ATTENDANT_WITH_ASSOCIATED_SERVICE = new BaseErrorMessage("attendant.withAssociatedService");

    private static final String ERROR_MESSAGES_RESOURCE = "errorMessages";
    private final String key;
    private String[] params;

    public BaseErrorMessage params(final String... params) {
        this.params = ArrayUtils.clone(params);
        return this;
    }

    public String getMessage() {
        var message = tryGetMessageFromBundle();
        if (ArrayUtils.isNotEmpty(params)) {
            final var fmt = new MessageFormat(message);
            message = fmt.format(params);
        }
        return message;
    }

    private String tryGetMessageFromBundle() {
        return getResource().getString(key);
    }

    public ResourceBundle getResource() {
        return ResourceBundle.getBundle(ERROR_MESSAGES_RESOURCE);
    }

}
