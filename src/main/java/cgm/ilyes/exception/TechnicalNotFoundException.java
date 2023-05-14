package cgm.ilyes.exception;

import jakarta.ws.rs.core.Response;

public class TechnicalNotFoundException extends TechnicalException {

    public TechnicalNotFoundException(String errorMessage, int errorStatusCode) {
        super(errorMessage, errorStatusCode);
    }

    public TechnicalNotFoundException(String errorMessage) {
        super(errorMessage, Response.Status.NOT_FOUND.getStatusCode());
    }

    public TechnicalNotFoundException() {
        super("Resource Not Found Exception Thrown", Response.Status.NOT_FOUND.getStatusCode());
    }
}
