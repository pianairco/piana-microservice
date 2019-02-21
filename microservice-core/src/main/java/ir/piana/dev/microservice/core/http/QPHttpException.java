package ir.piana.dev.microservice.core.http;

import ir.piana.dev.microservice.core.QPException;

/**
 * @author Mohammad Rahmati, 2/17/2019
 */
public class QPHttpException extends QPException {
    private QPHttpStatus status;
    private String description;

    public QPHttpException(QPHttpStatus status) {
        this(status, "");
    }

    public QPHttpException(
            QPHttpStatus status, String description) {
        this.status = status;
        this.description = description != null ? description : "";
    }

    public void applyResponse(QPHttpResponse response) {
        response.setHttpStatus(status);
        response.setEntity(description);
        response.setMediaType(QPHttpMediaType.TEXT_PLAIN);
        response.apply();
    }
}
