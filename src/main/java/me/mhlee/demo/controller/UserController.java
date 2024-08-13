package me.mhlee.demo.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.mhlee.demo.app.user.UserSearchUseCase;
import me.mhlee.demo.common.Paging;
import me.mhlee.demo.controller.dto.UserCreateReq;
import me.mhlee.demo.controller.dto.UserDto;
import me.mhlee.demo.domain.user.UserParam;
import me.mhlee.demo.domain.user.UserRepository;
import me.mhlee.demo.domain.user.Users;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final UserSearchUseCase userSearchApp;

    @PostMapping("/create")
    public String create(@Valid @RequestBody UserCreateReq req) {
        log.info("req :: {}", req);

        var user = Users.of(req.loginId(), req.password(), req.name(), req.age());
        userRepository.save(user);

        return "ok";
    }

    @PostMapping("/search")
    public Page<UserDto.UserSearchRes> search(@RequestBody UserDto.UserSearchReq req) {
        log.info("req :: {}", req);

        var param = new UserParam.UserSearchParam()
                .setName(req.name())
                .setAge(req.age())
                .setPaging((req.paging() != null) ? req.paging() : new Paging());

        return userSearchApp.search(param);
    }

}
