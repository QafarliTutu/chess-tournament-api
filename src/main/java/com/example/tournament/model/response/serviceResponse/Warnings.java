package com.example.tournament.model.response.serviceResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Warnings {

    private String code;
    private String source;
    private String message;

}
