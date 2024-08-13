package me.mhlee.demo.domain.user.service;

import me.mhlee.demo.BaseTest;
import me.mhlee.demo.domain.user.IUser;
import me.mhlee.demo.domain.user.IUserSearch;
import me.mhlee.demo.domain.user.UserParam;
import me.mhlee.demo.domain.user.UserRepository;
import org.antlr.v4.runtime.misc.Triple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


class UserSearchServiceTest extends BaseTest {

    @Autowired
    private IUserSearch sut;

    @Autowired
    private IUser userService;

    @Autowired
    private UserRepository repository;

    private List<Triple<String, String, Integer>> users;

    @BeforeEach
    void setUp() {
        var list = new ArrayList<Triple<String, String, Integer>>();
        list.add(new Triple<>("hello", "헬로", 19));
        list.add(new Triple<>("world", "월드", 20));
        list.add(new Triple<>("helloworld", "헬로월드", 20));

        // 테스트 유저 생성
        list.forEach(
                it -> {
                    log.info("user => {}", it);
                    userService.create(it.a, "password", it.b, it.c);
            }
        );

        users = list;
    }

    @Test
    void _나이로_검색이_되어야_한다() {
        var param = new UserParam.UserSearchParam()
                .setAge(20);

        var count = sut.count(param);
        assertThat(count).isEqualTo(2L);

        var rows = sut.search(param);
        // user의 2번째(world)와 3번째(helloworld)가 검색되어야 한다.
        assertThat(rows.stream().anyMatch(it -> it.getLoginId().equals(users.get(1).a))).isTrue();
        assertThat(rows.stream().anyMatch(it -> it.getLoginId().equals(users.get(2).a))).isTrue();
    }

    @Test
    void _이름은_오른쪽_와일드_카드로_검색되어야_한다() {
        var param = new UserParam.UserSearchParam()
                .setName("헬로");

        var count = sut.count(param);
        assertThat(count).isEqualTo(2L);

        var rows = sut.search(param);
        // user의 0번째(hello)와 3번째(helloworld)가 검색되어야 한다.
        assertThat(rows.stream().anyMatch(it -> it.getLoginId().equals(users.get(0).a))).isTrue();
        assertThat(rows.stream().anyMatch(it -> it.getLoginId().equals(users.get(2).a))).isTrue();
    }

}