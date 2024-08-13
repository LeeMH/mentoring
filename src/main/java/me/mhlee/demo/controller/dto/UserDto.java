package me.mhlee.demo.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import me.mhlee.demo.common.Paging;

public class UserDto {
    public static record UserSearchReq(
            @Schema(title = "검색 유저명", example = "홍길동", description = "우측 like 검색",  requiredMode = Schema.RequiredMode.NOT_REQUIRED)
            String name,

            @Schema(title = "검색 나이", example = "21", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
            Integer age,

            @Schema(title = "페이징", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
            Paging paging
    ) {}


    public static record UserSearchRes(
            @Schema(title = "로그인 ID", example = "user1234", requiredMode = Schema.RequiredMode.REQUIRED)
            String loginId,

            @Schema(title = "유저명", example = "홍길동", requiredMode = Schema.RequiredMode.REQUIRED)
            String name,

            @Schema(title = "나이", example = "21", description = "19~70세까지만 가능 가능", requiredMode = Schema.RequiredMode.REQUIRED)
            Integer age
    ) {}
}
