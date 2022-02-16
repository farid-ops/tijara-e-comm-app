package amiral.aokiji.tijara.exceptions;

import com.fasterxml.jackson.core.JsonParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@ControllerAdvice
public class RestApiErrorHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestApiErrorHandler.class);
    private final MessageSource messageSource;

    @Autowired
    public RestApiErrorHandler(MessageSource messageSource){
        this.messageSource = messageSource;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException(HttpServletRequest request,
                                                 Exception exception,
                                                 Locale locale){
        Error error = ErrorUtils
                .createError(ErrorCode.GENERIC_ERROR.getErrorCode(),
                        ErrorCode.GENERIC_ERROR.getMessage(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public ResponseEntity<Error> handleHttpMediaTypeNotSupportedException(HttpServletRequest request,
                                                                          HttpMediaTypeNotSupportedException exception,
                                                                          Locale locale){
        Error error = ErrorUtils
                .createError(ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getErrorCode(),
                        ErrorCode.HTTP_MEDIATYPE_NOT_SUPPORTED.getMessage(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        logger.info("HttpMediaTypeNotSupportedException :: request.getMethod() : "+request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResponseEntity<Error> handleMessageNotWritableException(HttpServletRequest request,
                                                                   HttpMessageNotWritableException exception,
                                                                   Locale locale){
        Error error = ErrorUtils
                .createError(ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getErrorCode(),
                        ErrorCode.HTTP_MESSAGE_NOT_WRITABLE.getMessage(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        logger.info("HttpMessageNotWritableException :: request.getMethod() : "+request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public ResponseEntity<Error> handleMediaTypeNotAcceptableException(HttpServletRequest request,
                                                                       HttpMediaTypeNotAcceptableException exception,
                                                                       Locale locale){
        Error error = ErrorUtils
                .createError(ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getErrorCode(),
                        ErrorCode.HTTP_MEDIA_TYPE_NOT_ACCEPTABLE.getMessage(),
                        HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        logger.info("HttpMediaNotAcceptableException :: request.getMethod() : "+request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handlerMessageNotReadableException(HttpServletRequest request,
                                                                    HttpMessageNotReadableException exception,
                                                                    Locale locale){
        Error error = ErrorUtils
                .createError(ErrorCode.HTTP_MESSAGE_NOT_READABLE.getErrorCode(),
                        ErrorCode.HTTP_MESSAGE_NOT_READABLE.getMessage(),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        logger.info("HttpMessageNotReadableException :: request.getMethod() : "+request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(JsonParseException.class)
    public ResponseEntity<Error> handlerJsonParserException(HttpServletRequest request,
                                                            JsonParseException exception,
                                                            Locale locale){
        Error error = ErrorUtils
                .createError(ErrorCode.JSON_PARSE_ERROR.getErrorCode(),
                        ErrorCode.JSON_PARSE_ERROR.getMessage(),
                        HttpStatus.NOT_ACCEPTABLE.value())
                .setUrl(request.getRequestURL().toString())
                .setReqMethod(request.getMethod());
        logger.info("JsonParserException :: request.getMethod() : "+request.getMethod());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
