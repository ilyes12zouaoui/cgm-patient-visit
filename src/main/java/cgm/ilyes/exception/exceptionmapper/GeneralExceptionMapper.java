package cgm.ilyes.exception.exceptionmapper;

import cgm.ilyes.exception.to.ErrorResponseTo;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.MDC;

import static cgm.ilyes.config.CorrelationIdRequestFilter.CORRELATION_ID_MDC_KEY;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<RuntimeException> {

    @Override
    public Response toResponse(RuntimeException e) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(new ErrorResponseTo(e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), MDC.get(CORRELATION_ID_MDC_KEY))).build();

    }
}