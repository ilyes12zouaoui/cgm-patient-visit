package cgm.ilyes.exception.exceptionmapper;

import cgm.ilyes.exception.TechnicalInputViolationException;
import cgm.ilyes.exception.to.ErrorResponseTo;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.MDC;

import static cgm.ilyes.config.CorrelationIdRequestFilter.CORRELATION_ID_MDC_KEY;

@Provider
public class TechnicalInputViolationExceptionMapper implements ExceptionMapper<TechnicalInputViolationException> {


    @Override
    public Response toResponse(TechnicalInputViolationException e) {

        return Response.status(e.getErrorStatusCode()).
                entity(new ErrorResponseTo(e.getErrorMessage(), e.getErrorStatusCode(), MDC.get(CORRELATION_ID_MDC_KEY))).build();

    }
}