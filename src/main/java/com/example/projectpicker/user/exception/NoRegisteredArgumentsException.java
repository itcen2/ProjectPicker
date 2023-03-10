package com.example.projectpicker.user.exception;

import lombok.NoArgsConstructor;
@NoArgsConstructor
//회원가입시 가입 정보가 없을 경우 예외처리

public class NoRegisteredArgumentsException extends RuntimeException{

    // 에러 메시지를 처리하는 생성자

    public NoRegisteredArgumentsException(String message) {
        super(message); //super() : super() 는 상속받은 부모 클래스의 변수명이나 클래스 변수를 참조하고, super() 는 부모 클래스 생성자를 호출합니다.
    }
}
