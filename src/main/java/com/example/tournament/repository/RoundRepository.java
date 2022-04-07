package com.example.tournament.repository;

import com.example.tournament.model.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository  extends JpaRepository<Round, Long> {
}
