package me.mhlee.demo.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private String message;
}
