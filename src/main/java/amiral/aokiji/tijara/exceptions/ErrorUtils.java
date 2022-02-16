package amiral.aokiji.tijara.exceptions;

public class ErrorUtils {

    public static Error createError(final String errorCode, final String message, final Integer statusCode){
        Error error = new Error();
        error.setErrorCode(errorCode);
        error.setMessage(message);
        error.setStatusCode(statusCode);
        return error;
    }
}
