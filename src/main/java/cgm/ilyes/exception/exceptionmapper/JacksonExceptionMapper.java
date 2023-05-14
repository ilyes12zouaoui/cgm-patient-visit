package cgm.ilyes.exception.exceptionmapper;

import cgm.ilyes.exception.to.ErrorResponseTo;
import cgm.ilyes.mapper.to.DateFormatPatternConstant;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.MDC;

import java.time.LocalDate;
import java.time.ZonedDateTime;

import static cgm.ilyes.config.CorrelationIdRequestFilter.CORRELATION_ID_MDC_KEY;

@Provider
public class JacksonExceptionMapper implements ExceptionMapper<MismatchedInputException> {

    @Override
    public Response toResponse(MismatchedInputException e) {
        String errorMessage;
        if (e.getTargetType().equals(LocalDate.class)) {
            errorMessage = "Date input format should be " + DateFormatPatternConstant.LOCAL_DATE_FORMAT + ". Example '1990-01-01'";
        } else if (e.getTargetType().equals(ZonedDateTime.class)) {
            errorMessage = "Date time input format should be " + DateFormatPatternConstant.ZONED_DATE_TIME_FORMAT + ". Example '2023-05-14T01:47:48.688+0200'";
        } else {
            errorMessage = e.getMessage();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                entity(new ErrorResponseTo(errorMessage, Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), MDC.get(CORRELATION_ID_MDC_KEY))).build();

    }
}