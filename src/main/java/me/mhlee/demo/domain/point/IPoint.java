package me.mhlee.demo.domain.point;

public interface IPoint {
    Points.Vo create(Long userId);
    Points.Vo plus(Long userId, Long amount);
    Points.Vo minus(Long userId, Long amount);
}
