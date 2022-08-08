package com.sns.dongore.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

@Getter
@AllArgsConstructor
public class ErrorResponse {

    public final int errorCode;
    public final String errorCodeDetail;
    public final String message;

    public static ResponseEntity<?> errorResponse(ErrorCode error){
        return ResponseEntity.status(error.getStatusCode()).body(
                new ErrorResponse(
                        error.getStatusCode().value(),
                        error.getStatusCode().toString(),
                        error.getDetail())
        );
    }
}
