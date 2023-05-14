package cgm.ilyes.exception.exceptionmapper;

import cgm.ilyes.exception.to.ErrorResponseTo;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.MDC;

import java.util.stream.Collectors;

import static cgm.ilyes.config.CorrelationIdRequestFilter.CORRELATION_ID_MDC_KEY;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {


    @Override
    public Response toResponse(ConstraintViolationException e) {
        var errorMessage = e.getConstraintViolations().stream().limit(1).map(constraintViolation -> {
            String propertyPath = constraintViolation.getPropertyPath().toString();
            String propertyName = propertyPath.substring(propertyPath.lastIndexOf(".") + 1);
            return propertyName + " field " + constraintViolation.getMessage();
        }).collect(Collectors.joining());

        return Response.status(Response.Status.BAD_REQUEST).
                entity(new ErrorResponseTo(errorMessage, Response.Status.BAD_REQUEST.getStatusCode(), MDC.get(CORRELATION_ID_MDC_KEY))).build();

    }
}