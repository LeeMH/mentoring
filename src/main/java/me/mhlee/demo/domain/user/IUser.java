package me.mhlee.demo.domain.user;

import lombok.NonNull;

public interface IUser {
    Users.Vo create(String loginId, String encodedPassword, String name, int age);
    Users.Vo updateName(Long userId, String name);
    Users.Vo updateAge(Long userId, int age);
}
