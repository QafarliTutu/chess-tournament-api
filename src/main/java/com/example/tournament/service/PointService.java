package com.example.tournament.service;

import com.example.tournament.exception.NoDataFoundException;
import com.example.tournament.model.entity.Player;
import com.example.tournament.model.entity.Point;
import com.example.tournament.model.entity.Round;
import com.example.tournament.model.entity.Tournament;
import com.example.tournament.model.enums.Result;
import com.example.tournament.model.request.UpdatePointDto;
import com.example.tournament.repository.PointRepository;
import com.example.tournament.repository.TournamentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.tournament.model.enums.Result.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PointService {

    private final RoundService roundService;
    private final TournamentRepository tournamentRepository;

    public Round updateResult(UpdatePointDto updatePointDto) {
        Round round = roundService.findRoundById(updatePointDto.getRoundId());
        updatePoints(round, updatePointDto.getResult());
        round.setResult(updatePointDto.getResult());
        round.setPointsCalculated(true);
        Round updatedRound = roundService.save(round);
        log.info(updatedRound.toString());
        return updatedRound;
    }

    public List<Player> getAllPoints(Long tournamentId) {
        Tournament tournament = tournamentRepository.findById(tournamentId)
                .orElseThrow(() -> new NoDataFoundException("no tournament found with id : " + tournamentId));
        return tournament.getPlayers().stream().sorted().collect(Collectors.toList());
    }

    private void updatePoints(Round round, Result result) {
        Player player1 = round.getPlayer1();
        Player player2 = round.getPlayer2();
        if (round.isPointsCalculated()) {
            switch (round.getResult()) {
                case WIN:
                    updatePlayerPoint(player1, WIN, -1);
                    updatePlayerPoint(player2, LOSE, -1);
                    break;
                case DRAW:
                    updatePlayerPoint(player1, DRAW, -1);
                    updatePlayerPoint(player2, DRAW, -1);
                    break;
                case LOSE:
                    updatePlayerPoint(player1, LOSE, -1);
                    updatePlayerPoint(player2, WIN, -1);
                    break;
            }
        }
        switch (result) {
            case WIN:
                updatePlayerPoint(player1, WIN, 1);
                updatePlayerPoint(player2, LOSE, 1);
                break;
            case DRAW:
                updatePlayerPoint(player1, DRAW, 1);
                updatePlayerPoint(player2, DRAW, 1);
                break;
            case LOSE:
                updatePlayerPoint(player1, LOSE, 1);
                updatePlayerPoint(player2, WIN, 1);
                break;
        }
    }

    private void updatePlayerPoint(Player player, Result result, int k) {
        Integer played = player.getPoint().getPlayed();
        Integer won = player.getPoint().getWon();
        Integer draw = player.getPoint().getDraw();
        Integer lost = player.getPoint().getLost();
        Integer points = player.getPoint().getPoints();
        switch (result) {
            case WIN:
                player.getPoint().setPoints(points + k + k);
                player.getPoint().setWon(won + k);
                break;
            case DRAW:
                player.getPoint().setPoints(points + k);
                player.getPoint().setDraw(draw + k);
                break;
            case LOSE:
                player.getPoint().setLost(lost + k);
                break;
        }
        player.getPoint().setPlayed(played + k);
    }

}
