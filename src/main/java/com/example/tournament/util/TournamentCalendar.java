package com.example.tournament.util;



import com.example.tournament.model.entity.Player;
import com.example.tournament.model.entity.Round;

import java.util.*;
import java.util.stream.Collectors;

public class TournamentCalendar {
    private int nTeam;
    private int colSize;
    private int rowSize;
    private List<Round> schedule;

    private TournamentCalendar(int nTeam) {
        this.nTeam = nTeam;
        colSize = nTeam % 2 == 0 ? (nTeam / 2) : (nTeam / 2) + 1;
        rowSize = nTeam % 2 == 0 ? nTeam - 1 : nTeam;
        int[][] tableLocals = generateTableLocals();
        int[][] tableVisitors = generateTableVisitors();
        schedule = generateSchedule(tableLocals, tableVisitors);
    }

    public TournamentCalendar(List<Player> teamNames) {
        this(teamNames.size());
        this.preparedRoundTables(teamNames);
        RoundDates roundDates = new RoundDates(schedule);
        roundDates.generateRoundsDates();
    }

    public List<Round> getDoubleRoundSchedule() {
        return schedule.stream().filter(s -> s.getLocalPlayer() != -1 && s.getVisitorPlayer() != -1).sorted().collect(Collectors.toList());
    }

    public List<Round> getSchedule() {
        List<Round> collect = schedule.stream().filter(s -> s.getLocalPlayer() != -1 && s.getVisitorPlayer() != -1).sorted()
                .collect(Collectors.toList());
        int middleIndex = collect.size() / 2;
        return collect.stream().filter(r -> collect.indexOf(r) < middleIndex).collect(Collectors.toList());
    }

    private void preparedRoundTables(List<Player>teamNames){
        Collections.shuffle(teamNames);
        Map<Integer, Player> teamIdentification = teamIdentificationAssignment(teamNames);
        replaceIdByNames(teamIdentification);
    }

    private List<Round> generateSchedule(int[][] tableLocals, int[][] tableVisitors) {
        List<Round> leagueSchedule = new ArrayList<>();

        for (int i = 0; i < rowSize; i++) {
            if (i % 2 == 0) {
                leagueSchedule.add(new Round(i + 1, tableLocals[i][0], tableVisitors[i][0]));
                leagueSchedule.add(new Round(rowSize + i + 1, tableVisitors[i][0], tableLocals[i][0]));
            } else {
                leagueSchedule.add(new Round(i + 1, tableVisitors[i][0], tableLocals[i][0]));
                leagueSchedule.add(new Round(rowSize + i + 1, tableVisitors[i][0], tableLocals[i][0]));
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                leagueSchedule.add(new Round(i + 1, tableLocals[i][j], tableVisitors[i][j]));
                leagueSchedule.add(new Round(rowSize + i + 1, tableVisitors[i][j], tableLocals[i][j]));
            }
        }
        return leagueSchedule;
    }

    private int[][] generateTableVisitors() {

        int it = rowSize - 1;

        int[][] tableResult = new int[rowSize][colSize];

        generateFirstCol(tableResult);

        for (int i = 0; i < rowSize; i++) {
            for (int j = 1; j < colSize; j++) {
                tableResult[i][j] = it;
                it = it == 0 ? rowSize - 1 : --it;
            }
        }
        return tableResult;
    }

    private Map<Integer, Player> teamIdentificationAssignment(List<Player> teamNames) {
        Map<Integer, Player> mapIds = new HashMap<>();

        int it = 0;
        for (Player player : teamNames) {
            mapIds.put(it, player);
            it++;
        }

        return mapIds;
    }

    private int[][] generateTableLocals() {
        int it = 0;

        int[][] tableResult = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                tableResult[i][j] = it;
                it = it == rowSize - 1 ? 0 : ++it;

            }
        }
        return tableResult;
    }

    private void generateFirstCol(int[][] tableRounds) {

        int valueCol = nTeam % 2 == 0 ? nTeam - 1 : -1;

        for (int i = 0; i < tableRounds.length; i++) {
            tableRounds[i][0] = valueCol;
        }

    }

    private void replaceIdByNames(Map<Integer, Player> teamIds) {
        for (Round round : schedule) {
            round.setTeamNames(teamIds.get(round.getLocalPlayer()), teamIds.get(round.getVisitorPlayer()));
        }
    }

}