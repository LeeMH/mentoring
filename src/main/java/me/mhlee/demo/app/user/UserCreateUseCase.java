package me.mhlee.demo.app.user;

import me.mhlee.demo.domain.user.Users;

public interface UserCreateUseCase {
    Users.Vo create(String loginId, String name, String password, int age);
}
