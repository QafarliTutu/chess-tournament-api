package com.example.tournament.model.entity;

import com.example.tournament.model.enums.Result;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Round implements Comparable<Round> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROUND_ID")
    private Long id;

    private int roundNum;

    @Transient
    @JsonIgnore
    private int localPlayer;

    @Transient
    @JsonIgnore
    private int visitorPlayer;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Player player1;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Player player2;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "TOURNAMENT_ID", nullable = false)
    private Tournament tournament;

    @Enumerated(EnumType.STRING)
    private Result result;

    @JsonIgnore
    private boolean isPointsCalculated;

    public Round(int roundNum, int localTeam, int visitorTeam) {
        super();
        this.roundNum = roundNum;
        this.localPlayer = localTeam;
        this.visitorPlayer = visitorTeam;
    }

    public Round(int roundNum, int visitorTeam) {
        super();
        this.roundNum = roundNum;
        this.visitorPlayer = visitorTeam;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public int getRoundNum() {
        return roundNum;
    }

    public void setRoundNum(int roundNum) {
        this.roundNum = roundNum;
    }

    public int getLocalPlayer() {
        return localPlayer;
    }

    public int getVisitorPlayer() {
        return visitorPlayer;
    }

    public void setTeamNames(Player localId, Player visitorId) {
        this.player1 = localId;
        this.player2 = visitorId;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public Boolean getPointsCalculated() {
        return isPointsCalculated;
    }

    @JsonIgnore
    public Boolean isPointsCalculated() {
        return isPointsCalculated;
    }

    public void setPointsCalculated(Boolean pointsCalculated) {
        isPointsCalculated = pointsCalculated;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    @Override
    public String toString() {
        return String.format("Round{id=%d, roundNum=%d, player1=%s, player2=%s, isPointsCalculated=%s, result=%s}", id, roundNum, player1, player2, isPointsCalculated, result);
    }

    @Override
    public int compareTo(Round round) {
        return Integer.compare(this.roundNum, round.roundNum);
    }


}