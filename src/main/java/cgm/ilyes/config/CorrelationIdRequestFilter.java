package cgm.ilyes.config;

import io.vertx.core.http.HttpServerRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.MDC;

import java.io.IOException;
import java.util.UUID;

@Provider
public class CorrelationIdRequestFilter implements ContainerRequestFilter, ContainerResponseFilter {

    public final static String CORRELATION_ID_MDC_KEY = "correlationId";
    public final static String CORRELATION_ID_HEADER_KEY = "X-Correlation-ID";

    @Context
    HttpServerRequest request;

    @Override
    public void filter(ContainerRequestContext context) {
        String correlationId = request.getHeader(CORRELATION_ID_HEADER_KEY);
        if (correlationId == null) {
            correlationId = UUID.randomUUID().toString();
        }
        MDC.put(CORRELATION_ID_MDC_KEY, correlationId);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        MDC.remove(CORRELATION_ID_MDC_KEY);
    }
}
