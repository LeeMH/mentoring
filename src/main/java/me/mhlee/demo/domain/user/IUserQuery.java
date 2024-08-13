package me.mhlee.demo.domain.user;

import org.springframework.lang.Nullable;

public interface IUserQuery {
    @Nullable Users.Vo findById(Long userId);
    Users.Vo getById(Long userId);

    @Nullable Users.Vo findByLoginId(String loginId);
    Users.Vo getByLoginId(String loginId);
    UserParam.UserAndPoint getByLoginIdWithPoint(Long userId);
}
