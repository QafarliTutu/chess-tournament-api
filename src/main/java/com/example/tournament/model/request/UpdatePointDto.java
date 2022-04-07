package com.example.tournament.model.request;

import com.example.tournament.model.enums.Result;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePointDto {

    private Long roundId;
    private Result result;

}