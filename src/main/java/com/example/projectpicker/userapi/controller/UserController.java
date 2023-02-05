package com.example.projectpicker.userapi.controller;

import com.example.projectpicker.userapi.dto.LoginRequestDTO;
import com.example.projectpicker.userapi.dto.LoginResponseDTO;
import com.example.projectpicker.userapi.dto.UserSignUpDTO;
import com.example.projectpicker.userapi.dto.UserSignUpResponseDTO;
import com.example.projectpicker.userapi.exception.DuplicatedEmailException;
import com.example.projectpicker.userapi.exception.NoRegisteredArgumentsException;
import com.example.projectpicker.userapi.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController // JSON/XML 형태로 객체 데이터 반환을 목적(= JSON으로 데이터를 주고 받음을 선언)
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin // 보안 취약
public class UserController {
    private final UserService userService;

    // 회원 가입 요청 처리

    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Validated @RequestBody UserSignUpDTO signUpDTO, BindingResult result
            ){
        log.info("/api/auth/signup POST - {}", signUpDTO);

        if (result.hasErrors()){
            log.warn(result.toString());
            return ResponseEntity
                    .badRequest()
                    .body(result.toString());
        }
        try {
            UserSignUpResponseDTO responseDTO
                    = userService.create(signUpDTO);
            return ResponseEntity
                    .ok()
                    .body(responseDTO);
        }catch (NoRegisteredArgumentsException e){
            log.warn("필수 가입 정보를 다시 확인하세요");
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        } catch (DuplicatedEmailException e){
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }
    // 이메일 중복 확인 요청 처리
    // GET : /api/auth/check?email=abc@bbb.com

    @GetMapping("/check")
    public ResponseEntity<?> checkEmail(String email){
        if(email == null || email.trim().equals("")){
            return ResponseEntity
                    .badRequest()
                    .body("이메일을 전달해 주세요");
        }
        boolean flag = userService.isDuplicate(email);
        log.info("{} 중복 여부?? - {}", email, flag);
        return ResponseEntity.ok().body(flag);
    }
    // 로그인 요청 처리
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(
            @Validated @RequestBody LoginRequestDTO requestDTO) {

        try {
            LoginResponseDTO userInfo = userService.getByCredentials(
                    requestDTO.getEmail(),
                    requestDTO.getPassword()
            );
            return ResponseEntity
                    .ok()
                    .body(userInfo);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(LoginResponseDTO.builder()
                            .message(e.getMessage())
                            .build()
                    );
        }
    }

}
