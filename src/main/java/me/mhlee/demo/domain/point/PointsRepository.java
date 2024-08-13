package me.mhlee.demo.domain.point;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PointsRepository extends JpaRepository<Points, Long> {
}