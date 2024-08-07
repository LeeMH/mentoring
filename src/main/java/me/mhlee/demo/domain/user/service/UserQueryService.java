package me.mhlee.demo.domain.user.service;


import lombok.AllArgsConstructor;
import me.mhlee.demo.common.exceptions.ApiException;
import me.mhlee.demo.domain.QueryService;
import me.mhlee.demo.domain.user.IUserQuery;
import me.mhlee.demo.domain.user.QUsers;
import me.mhlee.demo.domain.user.UserRepository;
import me.mhlee.demo.domain.user.Users;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
@AllArgsConstructor
public class UserQueryService extends QueryService implements IUserQuery  {
    private final UserRepository repository;

    @Override
    public Users.Vo findById(Long userId) {
        return repository.findById(userId)
                .map(it -> it.toVo())
                .orElse(null);
    }

    @Override
    public Users.Vo getById(Long userId) {
        var user = findById(userId);
        if (Objects.isNull(user)) {
            throw new ApiException("존재하지 않는 유저 입니다.");
        }

        return user;
    }

    @Override
    public Users.Vo findByLoginId(String loginId) {
        var qUser = QUsers.users;
        var where = where()
                .and(qUser.loginId.eq(loginId));

        var row = query().selectFrom(qUser)
                .where(where)
                .fetchOne();

        return (row != null)
                ? row.toVo()
                : null;
    }

    @Override
    public Users.Vo getByLoginId(String loginId) {
        var user = findByLoginId(loginId);
        if (user == null) {
            throw new ApiException("존재하지 않는 로그인 ID 입니다.");
        }

        return user;
    }
}
