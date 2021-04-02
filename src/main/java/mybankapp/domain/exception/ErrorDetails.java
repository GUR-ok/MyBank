package mybankapp.domain.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor

public class ErrorDetails {

    private Date timestamp;
    private String uri;
    private String errorCode;
    private String message;
}
