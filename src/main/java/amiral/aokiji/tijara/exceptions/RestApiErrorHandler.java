package amiral.aokiji.tijara.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;


@ControllerAdvice
public class RestApiErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestApiErrorHandler.class);
    private MessageSource messageSource;

    @Autowired
    public RestApiErrorHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request, Exception exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.GENERIC_ERROR.getErrCode(),
                ErrorCode.GENERIC_ERROR.getErrKeyMsg(),
                HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request ,HttpMediaTypeNotSupportedException exception, Locale locale){
        Error error = ErrorUtils.createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrCode(),
                ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrKeyMsg(), HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        logger.info("HttpMediaTypeNotSupported :: request.getMethod() "+request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }
}