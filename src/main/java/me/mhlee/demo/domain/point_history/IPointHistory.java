package me.mhlee.demo.domain.point_history;

public interface IPointHistory {
    PointHistory.Vo write(Long userId, PointHistory.Type type, Long amount, Long afterBalance);
}
