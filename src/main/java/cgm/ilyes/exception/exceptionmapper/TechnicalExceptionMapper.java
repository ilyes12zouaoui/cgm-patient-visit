package cgm.ilyes.exception.exceptionmapper;

import cgm.ilyes.exception.TechnicalException;
import cgm.ilyes.exception.to.ErrorResponseTo;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.MDC;

import static cgm.ilyes.config.CorrelationIdRequestFilter.CORRELATION_ID_MDC_KEY;

@Provider
public class TechnicalExceptionMapper implements ExceptionMapper<TechnicalException> {

    @Override
    public Response toResponse(TechnicalException e) {
        return Response.status(e.getErrorStatusCode()).
                entity(new ErrorResponseTo(e.getMessage(), e.getErrorStatusCode(), MDC.get(CORRELATION_ID_MDC_KEY))).build();

    }
}