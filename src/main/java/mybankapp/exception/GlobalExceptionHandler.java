package mybankapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception exception, WebRequest request) {
        ErrorDetails errorDetails;
        if (exception instanceof MyBusinessException) {
            errorDetails = new ErrorDetails(new Date(),
                    ((ServletWebRequest)request).getRequest().getRequestURI(),
                    ((MyBusinessException) exception).getErrorCode(),
                    exception.getMessage());
            log.info("In GEH: MyBusinessException caught, " + errorDetails.toString());
            return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else errorDetails = new ErrorDetails(new Date(),
                ((ServletWebRequest)request).getRequest().getRequestURI(),
                "API ERROR",
                exception.getMessage());
        log.info("In GEH: Exception caught, " + errorDetails.toString());
        return new ResponseEntity(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
