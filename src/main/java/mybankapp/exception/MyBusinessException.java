package mybankapp.exception;

public class MyBusinessException extends Exception{
    private final String errorCode;

    public MyBusinessException(String message, Throwable cause, String errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
