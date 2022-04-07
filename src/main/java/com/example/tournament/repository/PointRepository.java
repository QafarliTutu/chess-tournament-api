package com.example.tournament.repository;

import com.example.tournament.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository  extends JpaRepository<Point, Long> {
}
