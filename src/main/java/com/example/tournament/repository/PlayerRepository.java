package com.example.tournament.repository;

import com.example.tournament.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository  extends JpaRepository<Player, Long> {

}
