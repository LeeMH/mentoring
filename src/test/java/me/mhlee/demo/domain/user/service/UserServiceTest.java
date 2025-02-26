package me.mhlee.demo.domain.user.service;

import me.mhlee.demo.BaseTest;
import me.mhlee.demo.common.exceptions.ApiException;
import me.mhlee.demo.common.exceptions.ErrorCode;
import me.mhlee.demo.domain.user.IUser;
import me.mhlee.demo.domain.user.IUserQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class UserServiceTest extends BaseTest {

    @Autowired
    private IUser sut;

    @Autowired
    private IUserQuery userQuery;

    String duplicatedLoginId = "HELLO";

    @BeforeEach
    void setUp() {
        log.info("여기서 테스트 마다 실행할 스크립트 추가");
        sut.create(duplicatedLoginId, "test1234", "홍길동", 40);
    }

    @Test
    void _중복된_로그인ID가_존재한다면_예외가_발생해야_한다() {

        var ex = assertThrows(ApiException.class, () -> {
            sut.create(duplicatedLoginId, "test1234", "임꺽정", 30);
        });

        log.info("ex => {}", ex);

        assertThat(ex).isNotNull();
        assertThat(ex.getMessage()).startsWith(ErrorCode.DUPLICATED_LOGIN_ID.getCode());
    }

    @Test
    void _19세_이하라면_가입이_불가능하다() {

        var ex = assertThrows(ApiException.class, () -> {
            sut.create("newId", "test1234", "임꺽정", 18);
        });

        log.info("ex => {}", ex);

        assertThat(ex).isNotNull();
        assertThat(ex.getMessage()).startsWith(ErrorCode.OUT_OF_AGE_RANGE.getCode());
    }

    @Test
    void _70세_이상이라면_가입이_불가능하다() {

        var ex = assertThrows(ApiException.class, () -> {
            sut.create("newId", "test1234", "임꺽정", 71);
        });

        log.info("ex => {}", ex);

        assertThat(ex).isNotNull();
        assertThat(ex.getMessage()).startsWith(ErrorCode.OUT_OF_AGE_RANGE.getCode());
    }
}