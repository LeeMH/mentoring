package me.mhlee.demo.common.exceptions;

public enum ErrorCode {

    DUPLICATED_LOGIN_ID("[U0001]", "중복된 아이디가 존재 합니다. [ID=%s]"),
    OUT_OF_AGE_RANGE("[U0002]", "%s ~ %s 사이 나이만 가입이 가능 합니다."),
    EXCEEDED_NAME_LENGTH("[U0003]", "이름은 최대 %s자 까지만 가능 합니다."),
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
