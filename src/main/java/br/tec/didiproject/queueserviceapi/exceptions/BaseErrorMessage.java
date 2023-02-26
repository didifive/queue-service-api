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
    public static final BaseErrorMessage ROLE_NOT_FOUND = new BaseErrorMessage("role.notFound");
    public static final BaseErrorMessage ROLE_NOT_FOUND_BY_NAME = new BaseErrorMessage("role.notFound.byName");
    public static final BaseErrorMessage ROLE_ALREADY_EXISTS = new BaseErrorMessage("role.alreadyExists");
    public static final BaseErrorMessage USER_NOT_FOUND = new BaseErrorMessage("user.notFound");
    public static final BaseErrorMessage USER_NOT_FOUND_BY_USERNAME = new BaseErrorMessage("user.notFound.byUsername");
    public static final BaseErrorMessage USER_ALREADY_EXISTS = new BaseErrorMessage("user.alreadyExists");
    public static final BaseErrorMessage USER_WRONG_PASSWORD = new BaseErrorMessage("user.wrongPassword");
    public static final BaseErrorMessage GENERIC_METHOD_NOT_ALLOW = new BaseErrorMessage("generic.methodNotAllow");
    public static final BaseErrorMessage PLAYER_NOT_FOUND = new BaseErrorMessage("player.notFound");
    public static final BaseErrorMessage EMAIL_ALREADY_USED = new BaseErrorMessage("player.emailAlreadyUsed");
    public static final BaseErrorMessage ROUND_NOT_FOUND = new BaseErrorMessage("round.NotFound");
    public static final BaseErrorMessage PLAYER_IN_ROUND = new BaseErrorMessage("round.PlayerInRound");
    public static final BaseErrorMessage ROUND_STARTED = new BaseErrorMessage("round.RoundStarted");
    public static final BaseErrorMessage CARDS_LIMIT_REACHED = new BaseErrorMessage("round.CardsLimitReached");
    public static final BaseErrorMessage ROUND_COMPLETED = new BaseErrorMessage("round.RoundCompleted");
    public static final BaseErrorMessage ROUND_HAS_NO_DRAWN_NUMBER = new BaseErrorMessage("round.RoundHasNoDrawnNumber");

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
