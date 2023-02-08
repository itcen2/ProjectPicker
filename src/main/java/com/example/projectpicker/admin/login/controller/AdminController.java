package com.example.projectpicker.admin.login.controller;

import com.example.projectpicker.admin.login.dto.AdminPostDTO;
import com.example.projectpicker.admin.login.dto.AdminResponseLoginDTO;
import com.example.projectpicker.admin.login.dto.AdminSignUpDTO;
import com.example.projectpicker.admin.login.entity.AdminPostEntity;
import com.example.projectpicker.admin.login.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private final AdminService adminService;

    // admin 로그인
    @PostMapping
    public ResponseEntity<?> AdminLogin(@Validated @RequestBody AdminPostEntity adminPostEntity,
                                        BindingResult result
    ){
        AdminPostDTO adminPostDTO = new AdminPostDTO(adminPostEntity);
        AdminResponseLoginDTO flag = null;
        if(adminPostDTO == null){
            return ResponseEntity.badRequest().body("로그인 정보를 입력 해 주세요.");
        }
        if(result.hasErrors()){
            List<FieldError> fieldErrors = result.getFieldErrors();
            return ResponseEntity
                    .internalServerError().body(fieldErrors.get(0).getDefaultMessage());
        }
        try {
            flag = adminService.adminLogin(adminPostDTO);
            return ResponseEntity
                    .ok()
                    .body(flag);
        } catch (Exception e) {
            return ResponseEntity
                    .internalServerError()
                    .body(e.getMessage());
        }
    }

    // 사용자 등록시에만 활성화
    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Validated @RequestBody AdminSignUpDTO adminSignUpDTO){
        AdminPostDTO adminPostDTO = adminService.signUp(adminSignUpDTO);
        return ResponseEntity.ok()
                .body(adminPostDTO);
    }
}
