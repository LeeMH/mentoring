package me.mhlee.demo.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import me.mhlee.demo.controller.dto.UserCreateReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @PostMapping("/create")
    public String create(@Valid @RequestBody UserCreateReq req) {
        log.info("req :: {}", req);

        return "ok";
    }
}
