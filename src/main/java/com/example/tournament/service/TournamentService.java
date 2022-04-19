package com.example.tournament.service;

import com.example.tournament.exception.NoDataFoundException;
import com.example.tournament.model.entity.Player;
import com.example.tournament.model.entity.Round;
import com.example.tournament.model.entity.Tournament;
import com.example.tournament.model.enums.Result;
import com.example.tournament.model.request.CreateTournamentDto;
import com.example.tournament.repository.TournamentRepository;
import com.example.tournament.util.TournamentCalendar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class TournamentService {

    private final PlayerService playerService;
    private final TournamentRepository tournamentRepository;

    public Tournament createTournament(CreateTournamentDto createTournamentDto) {

        Tournament tournament = Tournament.builder()
                .name(createTournamentDto.getTournamentName())
                .passcode(createTournamentDto.getPasscode())
                .build();
        List<Player> players = playerService.createPlayers(createTournamentDto.getNames(), tournament);
        tournament.setPlayers(players);
        TournamentCalendar tournamentCalendar = new TournamentCalendar(tournament.getPlayers());
        List<Round> rounds = tournamentCalendar.getSchedule();
        rounds.forEach(r -> {
            r.setTournament(tournament);
            r.setResult(Result.NULL);
        } );
        tournament.setRounds(rounds);
        return tournamentRepository.save(tournament);
    }

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament findById(Long tournamentId) {
        log.info("tournamentId : {}", tournamentId);
        return tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoDataFoundException("no tournament found with id" + tournamentId));
    }
}
