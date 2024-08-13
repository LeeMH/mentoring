package me.mhlee.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

public record UserCreateReq(
        @Schema(title = "로그인 ID", example = "user1234", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "로그인 ID는 필수 입니다.")
        @Length(min = 6, message = "로그인 ID는 최소 6자 이상입니다.")
        String loginId,

        @Schema(title = "패스워드", example = "test1234!", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "패스워드는 필수 입니다.")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?])(?=\\S+$).{8,}$",
                message = "비밀번호는 최소 8자 이상이며, 1개 이상의 숫자와 특수문자를 포함해야 합니다."
        )
        String password,

        @Schema(title = "유저명", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotEmpty(message = "이름은 필수 입니다.")
        String name,

        @Schema(title = "나이", example = "21", description = "19~70세까지만 가능 가능", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "나이는 필수 입니다.")
        @Range(min = 19, max = 70, message = "19~70세까지만 가능합니다.")
        Integer age
) {}

