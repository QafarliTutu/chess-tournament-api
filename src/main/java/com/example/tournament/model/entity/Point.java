package com.example.tournament.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Comparator;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "POINT")
@EqualsAndHashCode(exclude = "player")
public class Point implements Comparable<Point>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POINT_ID")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Player player;

    private Integer played;
    private Integer won;
    private Integer draw;
    private Integer lost;
    private Integer points;

    @Override
    public int compareTo(Point o) {
        return Comparator.comparing(Point::getPoints)
                .thenComparing(Point::getWon)
                .compare(o, this);
    }

    @Override
    public String toString() {
        return String.format("Point{id=%d, played=%d, won=%d, draw=%d, lost=%d, points=%d}", id, played, won, draw, lost, points);
    }
}
