package com.example.tournament.model.response.serviceResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceResponse<T> {

    private boolean successful;
    private Errors errors;
    private List<Warnings> warnings;
    private T payload;


    public static <T> ServiceResponse createSuccessResponse(T payload) {
        return ServiceResponse.builder()
                .successful(true)
                .payload(payload)
                .errors(null)
                .build();
    }

    public static ServiceResponse createErrorResponse(String code, String source, String message) {
        Errors errors = Errors.builder()
                .code(code)
                .source(source)
                .message(message)
                .build();
        return ServiceResponse.builder()
                .successful(false)
                .errors(errors)
                .payload(null)
                .warnings(null)
                .build();
    }

    public static ServiceResponse createErrorResponse(long code, String source, String message) {
        return createErrorResponse(String.valueOf(code) , source, message);
    }

}
