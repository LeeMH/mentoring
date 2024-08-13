package me.mhlee.demo.app.user;

import me.mhlee.demo.controller.dto.UserDto;
import me.mhlee.demo.domain.user.UserParam;
import org.springframework.data.domain.Page;

public interface UserSearchUseCase {
    Page<UserDto.UserSearchRes> search(UserParam.UserSearchParam param);
}
