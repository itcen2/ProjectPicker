package com.example.projectpicker.user.controller;

import com.example.projectpicker.post.dto.response.PostDetailResponseDTO;
import com.example.projectpicker.user.dto.*;
import com.example.projectpicker.user.exception.DuplicatedEmailException;
import com.example.projectpicker.user.exception.NoRegisteredArgumentsException;
import com.example.projectpicker.user.service.UserService;
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
    // Postman (localhost:8080/auth/signup) --POST
    @PostMapping("/signup")
    public ResponseEntity<?> signup(
            @Validated @RequestBody UserSignUpDTO signUpDTO, BindingResult result
            ){
        log.info("/auth/signup POST - {}", signUpDTO);

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

    // 비밀번호 수정 요청 처리
    @RequestMapping(
            value = "updateUser/{userId}"
            , method = {RequestMethod.PUT, RequestMethod.PATCH}
    )
    public String updateUserInfo(
            @PathVariable("userId") String userId
            , @RequestBody UserInfoUpdateRequestDTO requestDTO
    ) {
        try {
            String s = userService.updateUserInfo(userId, requestDTO);
            return s;
        } catch (RuntimeException e) {
            log.error("update fail : caused by - {}", e.getMessage());
            String s = userService.updateUserInfo(userId, requestDTO);
            return s;
        }
    }

    // 이메일 중복 확인 요청 처리
    // Postman (localhost:8080/auth/check?email=test@naver.com )--GET
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
    // Postman (localhost:8080/auth/signin )--POST
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
