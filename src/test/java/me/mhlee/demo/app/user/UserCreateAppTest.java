package me.mhlee.demo.app.user;

import me.mhlee.demo.BaseTest;
import me.mhlee.demo.common.exceptions.ApiException;
import me.mhlee.demo.common.exceptions.ErrorCode;
import me.mhlee.demo.domain.point.PointsRepository;
import me.mhlee.demo.domain.point_history.PointHistory;
import me.mhlee.demo.domain.point_history.PointHistoryRepository;
import me.mhlee.demo.domain.user.IUserQuery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class UserCreateAppTest extends BaseTest {

    @Autowired
    private UserCreateUseCase sut;

    @Autowired
    private IUserQuery userQueryService;

    @Autowired
    private PointsRepository pointsRepository;

    @Autowired
    private PointHistoryRepository pointHistoryRepository;

    @Test
    void _유저생성시_포인트_처리도_되어야_한다() {
        var loginId = "helloworld";
        var name = "홈길동";
        var age = 25;
        var password = "test12434!";

        var user = sut.create(loginId, name, password, age);

        // user 검증
        assertThat(user.loginId()).isEqualTo(loginId);
        assertThat(user.name()).isEqualTo(name);
        assertThat(user.age()).isEqualTo(age);

        // 포인트 원장 생성 확인
        var point = pointsRepository.getReferenceById(user.id());
        assertThat(point).isNotNull();
        assertThat(point.getBalance()).isZero();
        assertThat(point.getBeforeBalance()).isZero();

        // 포인트 히스토리 검증
        var historyList = pointHistoryRepository.findAll().stream()
                .filter(it -> it.getUserId().equals(user.id()))
                .toList();
        assertThat(historyList.size()).isEqualTo(1);
        assertThat(historyList.get(0).getType()).isEqualTo(PointHistory.Type.CREATED);
        assertThat(historyList.get(0).getAmount()).isZero();
        assertThat(historyList.get(0).getBalance()).isZero();
    }

    @Test
    void _동일_loginId_사용시_에러가_발생해야_한다() {
        var loginId = "helloworld";
        var name = "홈길동";
        var age = 25;
        var password = "test12434!";

        // 계정 생성
        sut.create(loginId, name, password, age);

        // 동일 ID로 다시 생성시 exception이 터져야 한다.
        var ex = Assertions.assertThrows(ApiException.class, () -> {
            sut.create(loginId, name, password, age);
        });

        // 에러 메세지는 DUPLICATED_LOGIN_ID여야 한다.
        Assertions.assertTrue(ErrorCode.DUPLICATED_LOGIN_ID.startWithCode(ex.getMessage()));
    }

}