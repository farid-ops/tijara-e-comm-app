package amiral.aokiji.tijara.exceptions;

public class ErrorUtils {

    private ErrorUtils(){
        super();
    }

    public static Error createError(String errorCode, String message, Integer status){
        Error error = new Error();
        error.setErrorCode(errorCode);
        error.setMessage(message);
        error.setStatusCode(status);
        return error;
    }
}
