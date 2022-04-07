package com.example.tournament.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "PLAYER")
@EqualsAndHashCode(exclude = "tournament")
@ToString(exclude = "tournament")
public class Player implements Comparable<Player> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_ID")
    private Long id;

    private String name;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Point point;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="TOURNAMENT_ID", nullable=false)
    private Tournament tournament;

    @Override
    public int compareTo(Player o) {
        return Comparator.comparing(Player::getPoint)
                .compare(this, o);
    }
}
