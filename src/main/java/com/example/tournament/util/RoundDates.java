package com.example.tournament.util;

import com.example.tournament.model.entity.Round;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoundDates {
    private List<Round> rounds;

    public RoundDates(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void generateRoundsDates() {
        int roundDuration = 2;
        Map<Integer, List<Round>> roundsByNumber = rounds.stream()
                .collect(Collectors.groupingBy(Round::getRoundNum));
        int numMatches;
        int it;
        List<Round> listMatches;

        for (int i = 0; i < roundsByNumber.keySet().size(); i++) {
            listMatches = roundsByNumber.get(i + 1);
            numMatches = listMatches.size();
            it = numMatches / roundDuration;
            if (numMatches % roundDuration == 0) {
                for (Round round : listMatches) {
                    it--;
                    if (it == 0) {
                        it = numMatches / roundDuration;
                    }
                }
            } else {
                for (int j = 0; j < listMatches.size(); j++) {
                    it--;
                    if (it == 0) {
                        it = numMatches / roundDuration;
                    }
                }
            }
        }
    }
}