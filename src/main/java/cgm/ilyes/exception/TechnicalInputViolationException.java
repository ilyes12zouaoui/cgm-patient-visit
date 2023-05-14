package cgm.ilyes.exception;

import jakarta.ws.rs.core.Response;

public class TechnicalInputViolationException extends RuntimeException {
    private String errorMessage = "Technical Input Violation Exception Thrown";
    private int errorStatusCode = Response.Status.BAD_REQUEST.getStatusCode();

    public TechnicalInputViolationException(String errorMessage, int errorStatusCode) {
        this.errorMessage = errorMessage;
        this.errorStatusCode = errorStatusCode;
    }

    public TechnicalInputViolationException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public TechnicalInputViolationException() {
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
        return "TechnicalInputViolationException{" +
                "errorMessage=" + errorMessage +
                ", errorStatusCode=" + errorStatusCode +
                '}';
    }
}
