package cgm.ilyes.exception.to;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"error", "statusCode", "correlationId"})
@JsonInclude(Include.NON_NULL)
public class ErrorResponseTo {
    @JsonProperty("error")
    private String errorMessage;
    @JsonProperty("statusCode")
    private int errorHttpStatus;

    private String correlationId;

    public ErrorResponseTo() {
    }

    public ErrorResponseTo(String errorMessage, int errorHttpStatus, String correlationId) {
        this.errorMessage = errorMessage;
        this.errorHttpStatus = errorHttpStatus;
        this.correlationId = correlationId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public int getErrorHttpStatus() {
        return errorHttpStatus;
    }

    public void setErrorHttpStatus(int errorHttpStatus) {
        this.errorHttpStatus = errorHttpStatus;
    }

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    @Override
    public String toString() {
        return "ErrorResponseTo{" +
                "errorMessage='" + errorMessage + '\'' +
                ", errorHttpStatus=" + errorHttpStatus +
                ", correlationId='" + correlationId + '\'' +
                '}';
    }
}