package cft.focusstart.finalprogect.authservice.exceprion;

public class ServiceException extends RuntimeException {

    public static final String DEFAULT_REASON_EXCEPTION_MESSAGE = "no reason exception";

    private String reasonExceptionName = DEFAULT_REASON_EXCEPTION_MESSAGE;

    public ServiceException(String reasonExceptionName, String msg) {
        super(msg);
        if(reasonExceptionName != null)
            this.reasonExceptionName = reasonExceptionName;
    }

    public String getReasonExceptionName() {
        return reasonExceptionName;
    }
}
