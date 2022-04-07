package com.example.tournament.controller;

import com.example.tournament.model.entity.Tournament;
import com.example.tournament.model.request.CreateTournamentDto;
import com.example.tournament.model.response.serviceResponse.ServiceResponse;
import com.example.tournament.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournaments")
@Slf4j
@RequiredArgsConstructor
public class TournamentController {

    private final TournamentService tournamentService;

    @PostMapping
    public ServiceResponse create(@RequestBody CreateTournamentDto createTournamentDto) {
        Tournament tournament = tournamentService.createTournament(createTournamentDto);
        return ServiceResponse.createSuccessResponse(tournament);
    }

    @GetMapping
    public ServiceResponse getAllTournaments() {
        List<Tournament> allTournaments = tournamentService.getAllTournaments();
        return ServiceResponse.createSuccessResponse(allTournaments);
    }

    @GetMapping("/{tournamentId}")
    public ServiceResponse getTournament(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.findById(tournamentId);
        return ServiceResponse.createSuccessResponse(tournament);
    }

}
