package me.mhlee.demo.domain.point.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.mhlee.demo.common.exceptions.ApiException;
import me.mhlee.demo.common.exceptions.ErrorCode;
import me.mhlee.demo.domain.point.IPoint;
import me.mhlee.demo.domain.point.Points;
import me.mhlee.demo.domain.point.PointsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Transactional
@AllArgsConstructor
public class PointService implements IPoint {
    private PointsRepository repository;

    @Override
    public Points.Vo create(Long userId) {
        var point = Points.of(userId);

        return repository.save(point).toVo();
    }

    @Override
    public Points.Vo plus(Long userId, Long amount) {
        var point = getById(userId);
        point.plus(amount);

        return repository.save(point).toVo();
    }

    @Override
    public Points.Vo minus(Long userId, Long amount) {
        var point = getById(userId);
        point.minus(amount);

        return repository.save(point).toVo();
    }

    private Points getById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new ApiException(ErrorCode.NOT_FOUND_POINT.toMessage(userId)));
    }
}
