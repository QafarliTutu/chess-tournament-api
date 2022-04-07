package com.example.tournament.controller;

import com.example.tournament.model.entity.Player;
import com.example.tournament.model.entity.Round;
import com.example.tournament.model.request.UpdatePointDto;
import com.example.tournament.model.response.serviceResponse.ServiceResponse;
import com.example.tournament.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/points")
@RequiredArgsConstructor
@Slf4j
public class PointController {

    private final PointService pointService;

    @PostMapping
    public ServiceResponse updateResult(@RequestBody UpdatePointDto updatePointDto) {
        Round round = pointService.updateResult(updatePointDto);
        log.info(round.toString());
        return ServiceResponse.createSuccessResponse(round);
    }

    @GetMapping
    public ServiceResponse getAllPoints(@RequestParam Long tournamentId) {
        List<Player> list = pointService.getAllPoints(tournamentId);
        return ServiceResponse.createSuccessResponse(list);
    }
}
