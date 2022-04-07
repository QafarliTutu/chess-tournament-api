package com.example.tournament.service;

import com.example.tournament.exception.NoDataFoundException;
import com.example.tournament.model.entity.Round;
import com.example.tournament.repository.RoundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoundService {

    private final RoundRepository roundRepository;

    public Round findRoundById(Long id) {
        return roundRepository.findById(id)
                .orElseThrow(() -> new NoDataFoundException("no round found with id : " + id));
    }

    public Round save(Round round) {
        return roundRepository.save(round);
    }
}
