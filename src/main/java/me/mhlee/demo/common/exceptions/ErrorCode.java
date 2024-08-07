package me.mhlee.demo.common.exceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {

    DUPLICATED_LOGIN_ID("[U0001]", "중복된 아이디가 존재 합니다. [ID=%s]"),
    OUT_OF_AGE_RANGE("[U0002]", "%s ~ %s 사이 나이만 가입이 가능 합니다."),
    EXCEEDED_NAME_LENGTH("[U0003]", "이름은 최대 %s자 까지만 가능 합니다."),

    NOT_FOUND_POINT("[P0001]", "존재하지 않는 포인트 원장 입니다. [#ID=%s]"),
    NOT_ENOUGH_POINT("[P0002]", "포인트가 부족 합니다. [잔고=%s, 차감요청=%s]"),
    ;

    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String toMessage(Object... args) {
        return String.format(code + " " + message, args);
    }
}
