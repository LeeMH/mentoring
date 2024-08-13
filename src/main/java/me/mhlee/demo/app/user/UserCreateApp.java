package me.mhlee.demo.app.user;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.mhlee.demo.domain.point.IPoint;
import me.mhlee.demo.domain.point_history.IPointHistory;
import me.mhlee.demo.domain.point_history.PointHistory;
import me.mhlee.demo.domain.user.IUser;
import me.mhlee.demo.domain.user.Users;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class UserCreateApp implements UserCreateUseCase {
    private final IUser userService;
    private final IPoint pointService;
    private final IPointHistory pointHistoryService;

    @Override
    public Users.Vo create(String loginId, String name, String password, int age) {
        // 원래는 password를 해쉬화 해서 저장해야 한다!!
        // security 모듈 import
        var user = userService.create(loginId, password, name, age);
        pointService.create(user.getId());
        pointHistoryService.write(user.getId(), PointHistory.Type.CREATED, 0L, 0L);

        return user;
    }
}
