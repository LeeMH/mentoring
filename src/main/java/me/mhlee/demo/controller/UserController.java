package me.mhlee.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.mhlee.demo.controller.dto.UserCreateReq;
import me.mhlee.demo.domain.user.UserRepository;
import me.mhlee.demo.domain.user.Users;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/create")
    public String create(@Valid @RequestBody UserCreateReq req) {
        log.info("req :: {}", req);

        var user = Users.of(req.loginId(), req.password(), req.name(), req.age());
        userRepository.save(user);

        return "ok";
    }
}
