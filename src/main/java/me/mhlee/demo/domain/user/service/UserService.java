package me.mhlee.demo.domain.user.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import me.mhlee.demo.common.exceptions.ApiException;
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
            throw new ApiException("이미 존재하는 ID 입니다.");
        }

        if (StringUtils.length(name) > Users.MAX_NAME_LENGTH) {
            throw new ApiException("이름은 최대 16자 까지 입력 가능 합니다.");
        }

        if (age < Users.MIN_AGE || age > Users.MAX_AGE) {
            throw new ApiException(String.format("%s ~ %s 사이만 가입 가능 합니다.", Users.MIN_AGE, Users.MAX_AGE));
        }

        var user = Users.of(loginId, encodedPassword, name, age);
        return repository.save(user).toVo();
    }

    @Override
    public Users.Vo updateName(Long userId, String name) {
        if (StringUtils.length(name) > Users.MAX_NAME_LENGTH) {
            throw new ApiException("이름은 최대 16자 까지 입력 가능 합니다.");
        }

        var user = getById(userId);
        user.setName(name);
        return repository.save(user).toVo();
    }

    @Override
    public Users.Vo updateAge(Long userId, int age) {
        if (age < Users.MIN_AGE || age > Users.MAX_AGE) {
            throw new ApiException(String.format("%s ~ %s 사이만 가입 가능 합니다.", Users.MIN_AGE, Users.MAX_AGE));
        }

        var user = getById(userId);
        user.setAge(age);
        return repository.save(user).toVo();
    }

    private Users getById(Long userId) {
        return repository.findById(userId)
                .orElseThrow(() -> new ApiException("존재하지 않는 #ID 입니다."));
    }
}
