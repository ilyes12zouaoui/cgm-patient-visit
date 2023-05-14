package cgm.ilyes.exception;

import jakarta.ws.rs.core.Response;

public class TechnicalException extends RuntimeException {
    private String errorMessage = "Technical Exception Thrown";
    private int errorStatusCode = Response.Status.BAD_REQUEST.getStatusCode();

    public TechnicalException(String errorMessage, int errorStatusCode) {
        super(errorMessage);
        this.errorMessage = errorMessage;
        this.errorStatusCode = errorStatusCode;
    }

    public TechnicalException(String errorMessage) {
        super(errorMessage);
        this.errorMessage = errorMessage;
    }

    public TechnicalException() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorStatusCode() {
        return errorStatusCode;
    }

    public void setErrorStatusCode(int errorStatusCode) {
        this.errorStatusCode = errorStatusCode;
    }

    @Override
    public String toString() {
        return "TechnicalException{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorStatusCode=" + errorStatusCode +
                '}';
    }
}
