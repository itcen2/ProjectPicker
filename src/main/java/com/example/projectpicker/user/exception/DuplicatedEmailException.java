package com.example.projectpicker.user.exception;

import lombok.NoArgsConstructor;

// 회원가입시 이미 존재 하는 이메일인 경우 예외처리
@NoArgsConstructor
public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String message) {
        super(message);
    }
}
