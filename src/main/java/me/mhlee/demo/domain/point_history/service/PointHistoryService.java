package me.mhlee.demo.domain.point_history.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.mhlee.demo.domain.point_history.IPointHistory;
import me.mhlee.demo.domain.point_history.PointHistory;
import me.mhlee.demo.domain.point_history.PointHistoryRepository;
import org.springframework.stereotype.Component;

@Component
@Transactional
@AllArgsConstructor
public class PointHistoryService implements IPointHistory {
    private final PointHistoryRepository repository;

    @Override
    public PointHistory.Vo write(Long userId, PointHistory.Type type, Long amount, Long afterBalance) {
        var history = PointHistory.of(userId, type, amount, afterBalance);
        return repository.save(history).toVo();
    }
}
