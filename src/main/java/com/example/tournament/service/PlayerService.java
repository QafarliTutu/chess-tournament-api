package com.example.tournament.service;

import com.example.tournament.model.entity.Player;
import com.example.tournament.model.entity.Point;
import com.example.tournament.model.entity.Tournament;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    public List<Player> createPlayers(List<String> names, Tournament tournament) {
        List<Player> players = new ArrayList<>();
        names.forEach(n -> {
            Player player = Player.
                    builder()
                    .name(n)
                    .tournament(tournament)
                    .build();
            Point point = Point.builder()
                    .player(player)
                    .played(0)
                    .won(0)
                    .draw(0)
                    .lost(0)
                    .points(0)
                    .build();
            player.setPoint(point);
            players.add(player);
        });
        return players;
    }


}
