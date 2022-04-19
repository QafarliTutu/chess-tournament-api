package com.example.tournament.controller;

import com.example.tournament.model.entity.Round;
import com.example.tournament.model.entity.Tournament;
import com.example.tournament.model.request.CreateTournamentDto;
import com.example.tournament.model.request.PasscodeDto;
import com.example.tournament.model.response.serviceResponse.Errors;
import com.example.tournament.model.response.serviceResponse.ServiceResponse;
import com.example.tournament.service.TournamentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/{tournamentId}/rounds")
    public ServiceResponse getRounds(@PathVariable Long tournamentId) {
        Tournament tournament = tournamentService.findById(tournamentId);
        List<Round> rounds = tournament.getRounds();
        Map<Integer, List<Round>> roundGrouped = rounds.stream().collect(Collectors.groupingBy(Round::getRoundNum));
        return ServiceResponse.createSuccessResponse(roundGrouped);
    }

    @PostMapping("/{tournamentId}/test-passcode")
    public ServiceResponse testPasscode(@PathVariable Long tournamentId, @RequestBody PasscodeDto passcodeDto) {
        Tournament tournament = tournamentService.findById(tournamentId);
        if (passcodeDto.getPasscode().equals(tournament.getPasscode())) {
            return ServiceResponse.createSuccessResponse(true);
        } else return ServiceResponse.builder()
                .successful(false)
                .payload(false)
                .errors(Errors.builder()
                        .code("401")
                        .message("passcode doesn't match")
                        .build())
                .warnings(null)
                .build();
    }

}
