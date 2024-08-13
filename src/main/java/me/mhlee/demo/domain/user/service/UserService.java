package me.mhlee.demo.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.mhlee.demo.common.exceptions.ApiException;
import me.mhlee.demo.common.exceptions.ErrorCode;
import me.mhlee.demo.domain.user.IUser;
import me.mhlee.demo.domain.user.IUserQuery;
import me.mhlee.demo.domain.user.UserRepository;
import me.mhlee.demo.domain.user.Users;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@Transactional
@AllArgsConstructor
public class UserService implements IUser {
    private final UserRepository repository;
    private final IUserQuery queryService;

    @Override
    public Users.Vo create(String loginId, String encodedPassword, String name, int age) {
        var duplicated = queryService.findByLoginId(loginId);
        if (duplicated != null) {
            throw new ApiException(ErrorCode.DUPLICATED_LOGIN_ID.toMessage(loginId));
        }

        var user = Users.of(loginId, encodedPassword, name, age);
        return repository.save(user).toVo();
    }

    @Override
    public Users.Vo updateName(Long userId, String name) {
        var user = getById(userId);
        user.setName(name);
        return repository.save(user).toVo();
    }

    @Override
    public Users.Vo updateAge(Long userId, int age) {
        var user = getById(userId);
        user.setAge(age);
        return repository.save(user).toVo();
    }

    private Users getById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new ApiException("존재하지 않는 #ID 입니다."));
    }
}
